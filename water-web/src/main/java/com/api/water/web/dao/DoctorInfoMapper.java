package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.DoctorInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DoctorInfoMapper extends ApiMapper<DoctorInfo> {
}