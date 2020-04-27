package com.api.base.service;
import com.api.base.model.SysRolePower;
import com.api.core.service.Service;
import com.api.core.response.Result;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
public interface SysRolePowerService extends Service<SysRolePower> {
   Result list(String search, String order, Integer page, Integer size);
}
