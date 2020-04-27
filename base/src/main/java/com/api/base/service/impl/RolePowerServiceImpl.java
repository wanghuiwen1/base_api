package com.api.base.service.impl;

import com.api.base.dao.RolePowerApiMapper;
import com.api.base.model.RolePower;
import com.api.base.service.RolePowerService;
import com.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@Service
@Transactional
public class RolePowerServiceImpl extends AbstractService<RolePower> implements RolePowerService {
    @Resource
    private RolePowerApiMapper rolePowerMapper;

}