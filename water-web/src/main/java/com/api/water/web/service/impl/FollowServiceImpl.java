package com.api.water.web.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.core.config.AuthUser;
import com.api.core.response.ResultCode;
import com.api.water.web.constant.ConstPayment;
import com.api.water.web.constant.ConstUser;
import com.api.water.web.constant.Constant;
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
import java.math.BigDecimal;
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
    private FollowUserService followUserService;
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
    public Result add(HttpServletRequest request, Authentication authentication, Follow follow,String openid,String tradeType) {
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
                if (followSetting.getPrice().compareTo(new BigDecimal("0")) <= 0) {
                    follow.setStatus(Follow.STATUS_FOLLOWING);
                } else {
                    follow.setStatus(Follow.STATUS_WAITING);
                }
                followMapper.insert(follow);
            }else{
                //会话存在
                follow = queryfollow;
                //已经是咨询中的状态
                if (follow.getStatus().equals(Follow.STATUS_FOLLOWING)) {
                    Result result = new Result();
                    result.setCode(ResultCode.ADVISORY);
                    result.setMessage("咨询尚未结束，请勿再次发起！");
                    result.setData(follow);
                    return result;
                }else if (follow.getStatus().equals(Follow.STATUS_WAITING)||follow.getStatus().equals(Follow.STATUS_RESTART)){
                    //等待支付或者是重新开始的会话，需要支付
                    //查询是否有未支付的订单
                    FollowOrder latest = followOrderMapper.latest(follow.getId());
                    if(latest!=null){
                        //有过订单
                        if (latest.getStatus().equals(FollowOrder.STATUS_WAITPAY)) {
                            Payment payment;
                            //医生在用户支付的过程中修改了价格

                                if (!latest.getAmount().equals(followSetting.getPrice())) {
                                    latest.setAmount(followSetting.getPrice());
                                    followOrderMapper.updateByPrimaryKey(latest);

                                    payment = new Payment();
                                    payment.setOrderId(latest.getId());
                                    payment.setType(ConstPayment.ORDER_TYPE_ZI);
                                    payment = paymentMapper.selectOne(payment);
                                    //删除原有支付订单
                                    paymentService.deleteById(payment.getId());
                                    //创建新的支付订单
                                    Payment newpayment = new Payment();
                                    newpayment.setOrderId(latest.getId());
                                    newpayment.setStatus(ConstPayment.STATUS_WAIT_PAY);
                                    newpayment.setPayer(follow.getUid());
                                    newpayment.setAmount(latest.getAmount());
                                    newpayment.setPayee(follow.getDoctorId());
                                    newpayment.setRemark("患者咨询订单");
                                    newpayment.setCreateDate(date);
                                    newpayment.setType(ConstPayment.ORDER_TYPE_ZI);
                                    newpayment.setPayMethod(tradeType);
                                    paymentService.save(newpayment);
                                    payment = newpayment;
                                } else {
                                    payment = new Payment();
                                    payment.setOrderId(latest.getId());
                                    payment.setType(ConstPayment.ORDER_TYPE_ZI);
                                    payment = paymentMapper.selectOne(payment);
                                }
                                if (payment == null) {
                                    Payment newpayment = new Payment();
                                    newpayment.setOrderId(latest.getId());
                                    newpayment.setStatus(ConstPayment.STATUS_WAIT_PAY);
                                    newpayment.setPayer(follow.getUid());
                                    newpayment.setAmount(latest.getAmount());
                                    newpayment.setPayee(follow.getDoctorId());
                                    newpayment.setRemark("患者咨询订单");
                                    newpayment.setCreateDate(date);
                                    newpayment.setType(ConstPayment.ORDER_TYPE_ZI);
                                    newpayment.setPayMethod(tradeType);
                                    paymentService.save(newpayment);
                                } else {
                                    payment.setPayMethod(tradeType);
                                    paymentMapper.updateByPrimaryKeySelective(payment);
                                }
                                if (followSetting.getPrice().compareTo(new BigDecimal("0")) > 0 && (follow.getStatus().equals(Follow.STATUS_OVER) || follow.getStatus().equals(Follow.STATUS_WAITING))) {
                                    if (tradeType.equals("APP")) {
                                        Result result = paymentService.createOrder(request, latest.getId(), ConstPayment.ORDER_TYPE_ZI, tradeType, openid, null, Constant.WFR_APP_CONFIG, "");
                                        if (result.getCode() != 200) {
                                            return ResultGenerator.genFailResult(result.getMessage());
                                        }
                                        Map<String, String> data = (Map<String, String>) result.getData();
                                        data.put("extData", JSON.toJSONString(follow));
                                        return ResultGenerator.genSuccessResult(data);
                                    }
                                    if (tradeType.equals("PUBLIC")) {
                                        Result result = paymentService.createOrder(request, payment.getOrderId(), ConstPayment.ORDER_TYPE_ZI, tradeType, openid, null, Constant.WFR_WX_PUBLIC, "");
                                        if (result.getCode() != 200) {
                                            return ResultGenerator.genFailResult(result.getMessage());
                                        }
                                        Map<String, String> data = (Map<String, String>) result.getData();
                                        data.put("extData", JSON.toJSONString(follow));
                                        return ResultGenerator.genSuccessResult(data);
                                    } else {
                                        Result result = paymentService.createOrder(request, latest.getId(), ConstPayment.ORDER_TYPE_ZI, tradeType, openid, null, Constant.WFR_WX_CONFIG, "");
                                        if (result.getCode() != 200) {
                                            return ResultGenerator.genFailResult(result.getMessage());
                                        }
                                        Map<String, String> data = (Map<String, String>) result.getData();
                                        data.put("extData", JSON.toJSONString(follow));
                                        return ResultGenerator.genSuccessResult(data);
                                    }
                                } else {
                                    Result result = new Result();
                                    result.setCode(ResultCode.ADVISORY);
                                    result.setMessage("咨询尚未结束");
                                    follow.setStatus(Follow.STATUS_FOLLOWING);
                                    result.setData(follow);
                                    followMapper.updateByPrimaryKeySelective(follow);
                                    return result;
                                }

                        } else if (latest.getStatus().equals(FollowOrder.STATUS_PAY) ) {
                            Follow updateFollow = new Follow();
                            updateFollow.setId(follow.getId());
                            updateFollow.setStatus(Follow.STATUS_FOLLOWING);
                            followMapper.updateByPrimaryKeySelective(updateFollow);
                            Result result = new Result();
                            result.setCode(ResultCode.ADVISORY);
                            result.setMessage("咨询尚未结束，请勿再次发起！");
                            result.setData(follow);
                            return result;
                        }
                    }
                }

            }

        }

        return null;
    }

    @Override
    public Result checkSetting(Long docId) {
        FollowSetting queryFollowSetting = new FollowSetting();
        queryFollowSetting.setDoctorId(docId);
        FollowSetting followSetting = followSettingMapper.selectOne(queryFollowSetting);


        if (Objects.isNull(followSetting)) {
            return ResultGenerator.genSuccessResult(false);
        }
        return ResultGenerator.genSuccessResult(true);
    }

    @Override
    public Result followList(Long userid, Byte type, Integer page, Integer size) {
        List<Map> maps;
        PageInfo pageInfo;
        if (type == ConstUser.TYPE_DOCTOR || type == ConstUser.TYPE_NURSE) {//医生查
            DoctorInfo queryDoctorInfo = new DoctorInfo();
            queryDoctorInfo.setUserId(userid);
            DoctorInfo doctorInfo = doctorInfoMapper.selectOne(queryDoctorInfo);
            PageHelper.startPage(page, size);
            maps = followMapper.doctorFollowList(doctorInfo.getUserId());
            pageInfo = new PageInfo<>(maps);
        } else {//普通查
            PageHelper.startPage(page, size);
            maps = followMapper.commonFollowList(userid);
            pageInfo = new PageInfo<>(maps);
        }
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public List<Map> detail(Long followId) {

        List<Map> detail = followMapper.detail(followId);
        for (int i = 0; i < detail.size(); i++) {
            Map message = detail.get(i);
            if (message.get("type").equals(5) && !"咨询结束".equals(message.get("message"))) {
                JSONObject jsonObject = JSONObject.parseObject((String) message.get("message"));
                Long followOrderId = jsonObject.getLong("followOrderId");
                Condition condition = new Condition(FollowOrderEvaluation.class);
                condition.createCriteria().andEqualTo("followOrder", ForCondition.LongGenerator(followOrderId));
                List evaluation = followOrderEvaluationMapper.selectByCondition(condition);
                if (evaluation.size() == 0) {
                    jsonObject.put("hasEvaluted", 0);
                } else {
                    jsonObject.put("hasEvaluted", 1);
                }
                message.put("message", jsonObject.toJSONString());
            }
        }
        return detail;
    }

    @Override
    public Result getChat(Long followId) {

        Follow query = new Follow();
        query.setId(followId);
        Follow follow = followMapper.selectOne(query);
        FollowOrder followOrder = followOrderMapper.latest(followId);
//        if (followOrder.getStatus().equals(FollowOrder.STATUS_WAITPAY)) {
//            followOrder.setStatus(FollowOrder.STATUS_PAY);
//        }

//        if (followOrder.getIsaccept().equals(FollowOrder.ACCEPT_PENDING)) {
//            followOrder.setIsaccept(FollowOrder.ACCEPT_AGREE);
//        }
        followOrderMapper.updateByPrimaryKeySelective(followOrder);
        followUserService.inspectors(follow.getDoctorId(), follow.getId());
        if (Objects.nonNull(follow.getGroupChatId())) {
            HashMap<String, String> dataMap = new HashMap<String, String>();
            dataMap.put("chatId", follow.getGroupChatId());
            dataMap.put("status", followOrder.getStatus().toString());
            return ResultGenerator.genSuccessResult(dataMap, "获取成功");
        }


        String chatId = getChatId(follow.getUid(), follow.getDoctorId());
        follow.setGroupChatId(chatId);
        followMapper.updateByPrimaryKeySelective(follow);
        HashMap<String, String> dataMap = new HashMap<String, String>();
        dataMap.put("chatId", chatId);
        dataMap.put("status", followOrder.getStatus().toString());
        return ResultGenerator.genSuccessResult(dataMap, "获取成功");
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
