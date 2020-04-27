package com.api.base.dao;

import com.api.base.model.SysUser;
import com.api.core.ApiMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends ApiMapper<SysUser> {
    void deleteRoleById(Long userId);
}