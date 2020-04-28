package com.api.base.service.impl;

import com.api.base.config.ProjectConstant;
import com.api.base.config.RoleEnum;
import com.api.base.dao.SysRoleMapper;
import com.api.base.dao.SysUserMapper;
import com.api.base.dao.SysUserRoleMapper;
import com.api.base.model.SysRole;
import com.api.base.model.SysUser;
import com.api.base.model.SysUserRole;
import com.api.base.service.SysUserService;
import com.api.core.response.ResultEnum;
import com.api.core.service.AbstractService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import com.api.core.response.Result;
import com.api.core.response.ResultGenerator;

/**
 * Created by wanghuiwen on 2020/04/27.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    @Cacheable(cacheNames = "role", key = "#userId")
    public List<SysRole> getRole(Long userId) {
        return sysRoleMapper.getByUser(userId,RoleEnum.API.getType());
    }

    @Override
    @Cacheable(cacheNames = "menu", key = "#userId")
    public List<SysRole> getMenu(Long userId) {
        return sysRoleMapper.getByUser(userId, RoleEnum.MENU.getType());
    }

    @Override
    public Result addRole(List<Integer> roles, Long userId) {
        SysUser user = findById(userId);
        if (user == null) return ResultGenerator.genFailResult(ResultEnum.NO_CONTENT);

        List<SysUserRole> userRoles = new ArrayList<>();
        for (Integer id : roles) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(id);
            userRoles.add(userRole);
        }

        userMapper.deleteRoleById(userId);
        if (userRoles.size() > 0)
            sysUserRoleMapper.insertListNoAuto(userRoles);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result updatePassword(String password, String oldpassword, Long id) {
        SysUser user = findById(id);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if (!bCryptPasswordEncoder.matches(oldpassword, user.getPassword())) {
            return ResultGenerator.genResult(ResultEnum.PASSWORD_ERROR);
        }

        user.setPassword(bCryptPasswordEncoder.encode(password));
        update(user);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result registered(String username, String password, String mobileNumber, Byte gender, String email, String nickname, String avatar) {
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setMobileNumber(mobileNumber);
        user.setGender(gender);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setAvatar(avatar);
        save(user);

        SysRole role = sysRoleMapper.selectByDescription(ProjectConstant.ROLE_USER);
        if (role == null) return ResultGenerator.genResult(ResultEnum.NO_CONTENT);

        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        sysUserRoleMapper.insertSelective(userRole);
        return ResultGenerator.genSuccessResult();
    }
}
