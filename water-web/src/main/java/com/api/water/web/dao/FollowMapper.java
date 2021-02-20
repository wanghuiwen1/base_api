package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Follow;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FollowMapper extends ApiMapper<Follow> {
}