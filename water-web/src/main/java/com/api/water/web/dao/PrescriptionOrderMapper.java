package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.PrescriptionOrder;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrescriptionOrderMapper extends ApiMapper<PrescriptionOrder> {
}