package com.api.base.service.impl;

import com.api.base.config.ProjectConstant;
import com.api.base.dao.RoleMapper;
import com.api.base.dao.UserMapper;
import com.api.base.dao.UserRoleMapper;
import com.api.base.model.Role;
import com.api.base.model.User;
import com.api.base.model.UserRole;
import com.api.base.service.UserService;
import com.api.core.response.Result;
import com.api.core.service.AbstractService;
import com.api.core.response.ResultGenerator;
import com.api.core.response.ResultEnum;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by CodeGenerator on 2019/03/25.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Cacheable(cacheNames = "role", key = "#userId")
    public List<Role> getRole(Long userId) {
        return roleMapper.getByUser(userId);
    }

    @Override
    public Result addRole(List<Long> roles, Long userId) {
        User user = findById(userId);
        if (user == null) return ResultGenerator.genFailResult(ResultEnum.NO_CONTENT);

        List<UserRole> userRoles = new ArrayList<>();
        for (Long id : roles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(id);
            userRoles.add(userRole);
        }

        userMapper.deleteRoleById(userId);
        if (userRoles.size() > 0)
            userRoleMapper.insertListNoAuto(userRoles);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public Result updatePassword(String password, String oldpassword, Long id) {
        User user = findById(id);
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
        User user = new User();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setMobileNumber(mobileNumber);
        user.setGender(gender);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setAvatar(avatar);
        save(user);

        Role role = roleMapper.selectByDescription(ProjectConstant.ROLE_USER);
        if (role == null) return ResultGenerator.genResult(ResultEnum.NO_CONTENT);

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        userRoleMapper.insertSelective(userRole);
        return ResultGenerator.genSuccessResult();
    }
}
