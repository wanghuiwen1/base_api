package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.PatientCase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PatientCaseMapper extends ApiMapper<PatientCase> {
}