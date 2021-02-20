package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.UserAddress;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserAddressMapper extends ApiMapper<UserAddress> {
}