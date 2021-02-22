package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.FollowOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FollowOrderMapper extends ApiMapper<FollowOrder> {
    FollowOrder latest(Long followId);
    List<Map<String, Object>> list(Long uid);
}