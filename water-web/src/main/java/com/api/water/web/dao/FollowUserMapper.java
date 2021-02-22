package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.FollowUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowUserMapper extends ApiMapper<FollowUser> {
    List<Map<String,Object>> userList(Long followId);
    void read(FollowUser followUser);
    List<Long> listUserId(@Param("followId") Long id);
}