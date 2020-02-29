package com.api.core.dao;

import com.api.core.dao.provider.BaseListProvider;
import com.api.core.dao.provider.InsertListProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;
import java.util.Map;
@RegisterMapper
public interface BaseList<T> {

    @SelectProvider(
            type = BaseListProvider.class,
            method = "dynamicSQL"
    )
    List<Map<String, Object>> baseList(Map<String, Object> params, Map<String, Object> order);
}
