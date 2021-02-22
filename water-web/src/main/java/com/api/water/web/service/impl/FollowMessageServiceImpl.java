package com.api.water.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.core.config.AuthUser;
import com.api.core.response.ResultMessage;
import com.api.water.web.dao.*;
import com.api.water.web.model.*;
import com.api.water.web.service.FollowMessageService;
import com.api.core.service.AbstractService;
import com.api.water.web.util.ForCondition;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.api.common.JSONUtils;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import tk.mybatis.mapper.entity.Condition;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
@Service
@Transactional
public class FollowMessageServiceImpl extends AbstractService<FollowMessage> implements FollowMessageService {
    @Resource
    private FollowMessageMapper followMessageMapper;
    @Resource
    private FollowUserMapper followUserMapper;
    @Resource
    private FollowMapper followMapper;
    @Resource
    private FollowOrderMapper followOrderMapper;
    @Resource
    private RefundApplyMapper refundApplyMapper;
    @Resource
    private FollowSettingMapper followSettingMapper;
    @Resource
    private UserInfoMapper userInfoMapper;


    @Override
    public Result send(FollowMessage followMessage, Byte type) {
        Follow follow = followMapper.selectByPrimaryKey(followMessage.getFollowId());
        FollowSetting setting = followSettingMapper.selectByDoctorId(follow.getDoctorId());
        UserInfo info = userInfoMapper.selectByuserId(follow.getUid());
        if (type == 2) {
            //患者发送
            //判断价格
            if (setting.getPrice().compareTo(new BigDecimal("0")) > 0) {
                FollowOrder latest = followOrderMapper.latest(followMessage.getFollowId());
                if (latest.getStatus().equals(FollowOrder.STATUS_PAY)) {//订单已付费
                    if(follow.getStatus()==Follow.STATUS_OVER){
                        return ResultGenerator.genFailResult("咨询已经结束");
                    }
                }else{
                    return ResultGenerator.genFailResult("请付费后聊天！");
                }
            } else {
                if (!follow.getStatus().equals(Follow.STATUS_FOLLOWING)) {//设置咨询中
                    follow.setStatus(Follow.STATUS_FOLLOWING);
                    followMapper.updateByPrimaryKeySelective(follow);
                }
            }
        }else{
            //医生发送
            FollowOrder order = followOrderMapper.latest(followMessage.getFollowId());
            //医生第一次回复设置开始时间
            if (order != null) {
                if (order.getStartTime() == null) {
                    order.setStartTime(new Date());
                }
                followOrderMapper.updateByPrimaryKey(order);
            }
        }
        followMessageMapper.insert(followMessage);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result read(Long followId, Long userId) {
        FollowUser followUser = new FollowUser();
        followUser.setUserId(userId);
        followUser.setFollowId(followId);
        Long time = System.currentTimeMillis() / 1000;
        followUser.setLastRead(time.intValue());
        followUserMapper.read(followUser);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result close(Long followId, Authentication authentication) {

        Follow follow = followMapper.selectByPrimaryKey(followId);
        if (follow == null) return ResultGenerator.genFailResult(ResultMessage.NO_CONTENT);
        if (follow.getStatus().equals(Follow.STATUS_OVER)) return ResultGenerator.genFailResult("咨询已经结束，不能重复结束");
        follow.setStatus(Follow.STATUS_OVER);
        followMapper.updateByPrimaryKeySelective(follow);
        FollowOrder order = followOrderMapper.latest(followId);
        Condition condition = new Condition(RefundApply.class);
        condition.createCriteria().andEqualTo("followOrderId", ForCondition.LongGenerator(order.getId()));
        List<RefundApply> refundApplyList = refundApplyMapper.selectByCondition(condition);
        if (refundApplyList.size() != 0) {
            return ResultGenerator.genFailResult("患者已发起退款，请等待后台处理完毕本次聊天即自动结束");
        }
        if (order != null) {
            order.setEndTime(new Timestamp(System.currentTimeMillis()));
            followOrderMapper.updateByPrimaryKeySelective(order);
        }
        //给患者发送结束的消息
        FollowMessage followMessage = new FollowMessage();
        followMessage.setCreateDate(Integer.valueOf(System.currentTimeMillis() / 1000 + ""));
        followMessage.setType(5);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        followMessage.setSender(authUser.getId());
        followMessage.setFollowId(followId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "咨询结束");
        jsonObject.put("followOrderId", order.getId());
        jsonObject.put("amount", order.getAmount());
        followMessage.setMessage(jsonObject.toJSONString());
        followMessageMapper.insert(followMessage);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public List<Map<String, Object>> getAllDoctorNoReadList() {
        return followMessageMapper.getAllDoctorNoReadList();
    }
}
