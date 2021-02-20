package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoMapper extends ApiMapper<UserInfo> {
    UserInfo selectByuserId(@Param("userId") Long id);
}