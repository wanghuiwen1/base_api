package com.api.water.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.core.config.AuthUser;
import com.api.water.web.dao.*;
import com.api.water.web.model.*;
import com.api.water.web.service.FollowService;
import com.api.core.service.AbstractService;
import com.api.water.web.service.FollowUserService;
import com.api.water.web.service.PaymentService;
import com.api.water.web.util.ForCondition;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

import com.api.common.JSONUtils;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Condition;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
@Service
@Transactional
public class FollowServiceImpl extends AbstractService<Follow> implements FollowService {
    @Resource
    private FollowMapper followMapper;
    @Resource
    private FollowOrderMapper followOrderMapper;
    @Resource
    private DoctorInfoMapper doctorInfoMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Resource
    private FollowSettingMapper followSettingMapper;
    @Resource
    private PaymentService paymentService;
    @Resource
    private PaymentMapper paymentMapper;
    @Resource
    private FollowUserMapper followUserMapper;
    @Value("${chat.host}")
    private String host;
    @Resource
    private FollowOrderEvaluationMapper followOrderEvaluationMapper;
    @Resource
    private UserAddressMapper userAddressMapper;

    @Override
    public Result list(String search, String order, Integer page, Integer size) {
        Map<String, Object> params = JSONUtils.json2map(search);
        Map<String, Object> orderParams = JSONUtils.json2map(order);
        for (String key : orderParams.keySet()) {
            if (orderParams.get(key) != null && orderParams.get(key).equals("ascending")) orderParams.put(key, "asc");
            if (orderParams.get(key) != null && orderParams.get(key).equals("descending")) orderParams.put(key, "desc");
        }
        PageHelper.startPage(page, size);
        List<Map<String, Object>> res = followMapper.baseList(params, orderParams);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public Result add(HttpServletRequest request, Authentication authentication, Follow follow) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        Byte type = authUser.getType();
        DoctorInfo doctorInfo = doctorInfoMapper.selectByPrimaryKey(follow.getDoctorId());
        follow.setWhoset(doctorInfo.getUserId());
        follow.setStatus(Follow.STATUS_WAITING);
        //检查医生咨询设置
        FollowSetting queryFollowSetting = new FollowSetting();
        queryFollowSetting.setDoctorId(follow.getDoctorId());
        FollowSetting followSetting = followSettingMapper.selectOne(queryFollowSetting);
        if (Objects.isNull(followSetting)) {
            return ResultGenerator.genFailResult("该医生个人咨询尚未设置，请稍后再试！");
        } else {
            Date date = new Date();
            Date startTime = followSetting.getStartTime();
            Date endTime = followSetting.getEndTime();
            int start = date.compareTo(startTime);
            int end = date.compareTo(endTime);
            if (end > 0 || start < 0) {
                return ResultGenerator.genFailResult("该时间段医生未开启咨询！");
            }
        }
        //查询医患会话是否已经存在
        Follow queryfollow = new Follow();
        queryfollow.setUid(follow.getUid());
        queryfollow.setDoctorId(follow.getDoctorId());
        queryfollow = followMapper.selectOne(queryfollow);


        Date date = new Date();
        follow.setCreateDate(date);

        //医生发起会话
        if (type == 0) {
            //会话不存在
            if (Objects.isNull(queryfollow)) {
                String groupId = getChatId(follow.getUid(), follow.getDoctorId());
                follow.setGroupChatId(groupId);
                followMapper.insert(follow);
                //添加followUser
                FollowUser followUser = new FollowUser();
                followUser.setUserId(follow.getUid());
                followUser.setFollowId(follow.getId());
                Long time = System.currentTimeMillis() / 1000;
                followUser.setLastRead(time.intValue());
                followUserMapper.insertSelective(followUser);
                followUser = new FollowUser();
                followUser.setUserId(doctorInfo.getUserId());
                followUser.setFollowId(follow.getId());
                followUser.setLastRead(time.intValue());
                followUserMapper.insertSelective(followUser);

            } else {
                //会话存在
                follow = queryfollow;
            }
            UserInfo info = userInfoMapper.selectByuserId(follow.getUid());
            Map<String, String> res = new HashMap<>();
            res.put("groupid", follow.getGroupChatId());
            res.put("id", follow.getId().toString());
            res.put("name", info.getName());
            res.put("uid", info.getId().toString());
            res.put("userId", info.getUserId().toString());

            //不需要支付，不需要通知医生
            return ResultGenerator.genSuccessResult(res);
        }else{
            //患者发起的会话
            //检查地址
            Condition condition = new Condition(UserAddress.class);
            condition.createCriteria().andEqualTo("userId", ForCondition.LongGenerator(follow.getUid()));
            List<UserAddress> userAddresses = userAddressMapper.selectByCondition(condition);
            if(userAddresses.size()==0){
                return ResultGenerator.genFailResultAddressNull();
            }
            //会话不存在
            if (Objects.isNull(queryfollow)) {
                //判断金额，如果为０则直接开始咨询，若不为０则未等待付款
            }else{
                //会话存在
            }

        }

        return null;
    }
    private String getChatId(Long uid, Long doctorId) {
        ArrayList<Map<String, String>> data = new ArrayList<>();
        Map<String, String> uidmap = new HashMap<>();
        //用户
        UserInfo info = new UserInfo();
        info.setUserId(uid);
        UserInfo userInfo = userInfoMapper.selectOne(info);
        if (Objects.nonNull(userInfo.getPhotoUrl())) {
            uidmap.put("faceImage", userInfo.getPhotoUrl());
        } else {
            uidmap.put("faceImage", "");
        }
        uidmap.put("userid", uid.toString());
        data.add(uidmap);


        DoctorInfo doctorInfo = doctorInfoMapper.selectByPrimaryKey(doctorId);
        if (Objects.nonNull(doctorInfo.getHead())) {
            uidmap.put("faceImage", doctorInfo.getHead());
        } else {
            uidmap.put("faceImage", "");
        }
        uidmap.put("userid", doctorInfo.getUserId().toString());
        data.add(uidmap);



        CloseableHttpClient httpClient
                = HttpClients.custom()
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpComponentsClientHttpRequestFactory requestFactory
                = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("name", uid + "_" + doctorId);
        map.add("project", "ylt");
        map.add("userslist", JSON.toJSONString(data));
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<JSONObject> response = restTemplate.postForEntity(host + "/api/chat/addCrowd", request, JSONObject.class);
        JSONObject body = response.getBody();
        return body.getString("data");
    }
}
