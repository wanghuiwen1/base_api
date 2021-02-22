package com.api.water.web.dao;

import com.api.core.ApiMapper;
import com.api.water.web.model.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper extends ApiMapper<Department> {
}