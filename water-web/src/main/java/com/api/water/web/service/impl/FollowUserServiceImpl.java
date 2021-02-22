package com.api.water.web.service.impl;

import com.api.water.web.dao.DoctorInfoMapper;
import com.api.water.web.dao.FollowMapper;
import com.api.water.web.dao.FollowUserMapper;
import com.api.water.web.model.DoctorInfo;
import com.api.water.web.model.Follow;
import com.api.water.web.model.FollowUser;
import com.api.water.web.service.FollowUserService;
import com.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.api.common.JSONUtils;
import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * Created by wanghuiwen on 2021/01/20.
 */
@Service
@Transactional
public class FollowUserServiceImpl extends AbstractService<FollowUser> implements FollowUserService {
    @Resource
    private FollowUserMapper followUserMapper;
    @Resource
    private FollowMapper followMapper;
    @Resource
    private DoctorInfoMapper doctorInfoMapper;

    @Override
    public Result list(String search, String order, Integer page, Integer size) {
        Map<String, Object> params = JSONUtils.json2map(search);
        Map<String, Object> orderParams = JSONUtils.json2map(order);
        for (String key : orderParams.keySet()) {
            if (orderParams.get(key) != null && orderParams.get(key).equals("ascending")) orderParams.put(key, "asc");
            if (orderParams.get(key) != null && orderParams.get(key).equals("descending")) orderParams.put(key, "desc");
        }
        PageHelper.startPage(page, size);
        List<Map<String, Object>> res = followUserMapper.baseList(params, orderParams);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(res);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Override
    public void inspectors(Long doctorId, Long followId) {

        FollowUser followUser = new FollowUser();
        Long time = System.currentTimeMillis() / 1000;
        followUser.setLastRead(time.intValue());
        Follow queryFollow = new Follow();
        queryFollow.setDoctorId(doctorId);
        queryFollow.setId(followId);
        List<Follow> listFollow = followMapper.select(queryFollow);
        DoctorInfo doctorInfo = doctorInfoMapper.selectByPrimaryKey(doctorId);
        for (Follow follow : listFollow) {
            followUser.setFollowId(follow.getId());
            followUser.setUserId(doctorInfo.getUserId());
            FollowUser example = new FollowUser();
            example.setFollowId(followUser.getFollowId());
            example.setUserId(followUser.getUserId());
            example = followUserMapper.selectOne(example);
            if (example == null) {
                followUserMapper.insert(followUser);
            }

        }
    }

    @Override
    public Result getUserList(Long followId) {
        List<Map<String,Object>> users= followUserMapper.userList(followId);
        return ResultGenerator.genSuccessResult(users);
    }
}

