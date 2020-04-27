package com.api.base.service;
import com.api.base.model.SysPower;
import com.api.common.mybatis.ResultMap;
import com.api.core.service.Service;

import java.util.List;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
public interface SysPowerService extends Service<SysPower> {
   @Override
   List<SysPower> findAll();

   List<ResultMap<String,Object>> listAll();

   List<SysPower>  getByRole(Integer roleId);
}
