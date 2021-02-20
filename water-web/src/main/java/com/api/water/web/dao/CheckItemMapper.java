package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.CheckItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CheckItemMapper extends ApiMapper<CheckItem> {
}