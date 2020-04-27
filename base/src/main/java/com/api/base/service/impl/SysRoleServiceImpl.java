package com.api.base.service.impl;

import com.api.base.dao.SysRoleMapper;
import com.api.base.dao.SysRolePowerMapper;
import com.api.base.model.SysRole;
import com.api.base.model.SysRolePower;
import com.api.base.service.SysRoleService;
import com.api.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import com.api.common.JSONUtils;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
@Service
@Transactional
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper roleMapper;
    @Resource
    private SysRolePowerMapper sysRolePowerMapper;

    @Override
    @Transactional
    public void addPower(String powers,Integer roleId) {
        roleMapper.deletePower(roleId);
        List<Integer> powerids = JSONUtils.json2WrapperList(powers,Integer.class);

        List<SysRolePower> rolePowers = new ArrayList<>();
        for (Integer pid: powerids) {
            SysRolePower rolePower = new SysRolePower();
            rolePower.setPowerId(pid);
            rolePower.setRoleId(roleId);
            rolePowers.add(rolePower);
        }
        if(rolePowers.size()>0)
            sysRolePowerMapper.insertListNoAuto(rolePowers);
    }
}
