package com.api.core.dao;

import com.api.core.dao.provider.InsertListProvider;
import org.apache.ibatis.annotations.InsertProvider;
import tk.mybatis.mapper.annotation.RegisterMapper;

import java.util.List;

@RegisterMapper
public interface InsertList<T> {

    @InsertProvider(
            type = InsertListProvider.class,
            method = "dynamicSQL"
    )
    int insertListNoAuto(List<? extends T> var1);
}
