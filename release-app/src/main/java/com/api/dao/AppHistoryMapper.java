package com.api.dao;

import com.api.core.Mapper;
import com.api.model.AppHistory;

import java.util.List;
import java.util.Map;

public interface AppHistoryMapper extends Mapper<AppHistory> {
    List<AppHistory> selectByAppID(Map<String, Object> params);

    AppHistory findLast(Long appid);
}