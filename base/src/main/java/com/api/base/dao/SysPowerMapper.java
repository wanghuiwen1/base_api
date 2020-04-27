package com.api.base.dao;

import com.api.base.model.SysPower;
import com.api.common.mybatis.ResultMap;
import com.api.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysPowerMapper extends ApiMapper<SysPower> {
    List<SysPower> getByUser(Long id);

    List<SysPower> getByRole(@Param("roleId") Integer roleId);

    List<ResultMap<String, Object>> listAll();
}