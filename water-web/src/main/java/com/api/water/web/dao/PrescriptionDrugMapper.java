package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.PrescriptionDrug;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrescriptionDrugMapper extends ApiMapper<PrescriptionDrug> {
}