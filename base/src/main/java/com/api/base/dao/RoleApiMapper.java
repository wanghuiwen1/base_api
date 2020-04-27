package com.api.base.dao;

import com.api.base.model.Role;
import com.api.core.ApiMapper;

import java.util.List;

public interface RoleApiMapper extends ApiMapper<Role> {
    List<Role> getByUser(Long userId);

    void deletePower(Long roleId);

    Role selectByDescription(String roleUser);
}