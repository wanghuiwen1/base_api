package com.api.base.service;

import com.api.base.model.Role;
import com.api.base.model.User;
import com.api.core.service.Service;
import com.api.core.response.Result;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
public interface UserService extends Service<User> {

    List<Role> getRole(Long userId);


    Result addRole(List<Long> roles, Long userId);

    Result updatePassword(String password, String oldpassword, Long id);

    Result registered(String username, String password, String mobileNumber, Byte gender, String email, String nickname, String avatar);
}
