package com.api.dao;

import com.api.core.ApiMapper;
import com.api.model.AppHistory;

import java.util.List;
import java.util.Map;

public interface AppHistoryApiMapper extends ApiMapper<AppHistory> {
    List<AppHistory> selectByAppID(Map<String, Object> params);

    AppHistory findLast(Long appid);
}