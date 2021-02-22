package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Hospital;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HospitalMapper extends ApiMapper<Hospital> {
}