package com.api.base.dao;

import com.api.base.model.User;
import com.api.core.ApiMapper;

public interface UserApiMapper extends ApiMapper<User> {

    void deleteRoleById(Long userId);
}