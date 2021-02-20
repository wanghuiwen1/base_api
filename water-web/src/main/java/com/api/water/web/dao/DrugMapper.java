package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Drug;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DrugMapper extends ApiMapper<Drug> {
}