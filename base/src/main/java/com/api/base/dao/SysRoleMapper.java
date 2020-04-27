package com.api.base.dao;

import com.api.base.model.SysRole;
import com.api.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRoleMapper extends ApiMapper<SysRole> {
    List<SysRole> getByUser(Long userId);

    void deletePower(Integer roleId);

    SysRole selectByDescription(String roleUser);
}