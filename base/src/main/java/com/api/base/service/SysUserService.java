package com.api.base.service;
import com.api.base.model.SysRole;
import com.api.base.model.SysUser;
import com.api.core.service.Service;
import com.api.core.response.Result;

import java.util.List;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
public interface SysUserService extends Service<SysUser> {

   List<SysRole> getRole(Long userId);

   List<SysRole> getMenu(Long userId);


   Result addRole(List<Integer> roles, Long userId);

   Result updatePassword(String password, String oldpassword, Long id);

   Result registered(String username, String password, String mobileNumber, Byte gender, String email, String nickname, String avatar);

}
