package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Follow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowMapper extends ApiMapper<Follow> {
    List<Map> doctorFollowList(@Param("userId") Long userId);
    List<Map> commonFollowList(Long userId);
    List<Map> detail(Long followId);
}