package com.api.base.dao;


import com.api.base.model.Power;
import com.api.common.mybatis.ResultMap;
import com.api.core.ApiMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerApiMapper extends ApiMapper<Power> {

    List<Power> getByUser(Long id);

    List<Power> getByRole(@Param("roleId") Long roleId);

    List<ResultMap<String, Object>> listAll();
}