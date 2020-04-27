package com.api.base.service;
import com.api.base.model.SysRole;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
public interface SysRoleService extends Service<SysRole> {
   void addPower(String powers, Integer roleId);
}
