package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper extends ApiMapper<Payment> {
}