package com.api.water.web.service.impl;

import com.api.base.config.ProjectConstant;
import com.api.base.dao.SysRoleMapper;
import com.api.base.dao.SysUserMapper;
import com.api.base.dao.SysUserRoleMapper;
import com.api.base.model.SysRole;
import com.api.base.model.SysUser;
import com.api.base.model.SysUserRole;
import com.api.core.response.Result;
import com.api.core.response.ResultEnum;
import com.api.core.response.ResultGenerator;
import com.api.water.web.dao.UserInfoMapper;
import com.api.water.web.model.UserInfo;
import com.api.water.web.service.UserManagerService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;

@Service
@Transactional
public class UserManagerServiceImpl implements UserManagerService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;
    @Resource
    private UserInfoMapper userInfoMapper;
    @Override
    public Result registered(String username, String password, String mobileNumber, Byte gender, String email, String nickname, String avatar, String photoUrl, String idcard, Integer age, Date birthday,String address) {
        SysUser user = new SysUser();
        user.setUsername(username);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setMobileNumber(mobileNumber);
        user.setGender(gender);
        user.setEmail(email);
        user.setNickname(nickname);
        user.setAvatar(avatar);
        sysUserMapper.insert(user);

        SysRole role = sysRoleMapper.selectByDescription(ProjectConstant.ROLE_USER);
        if (role == null) return ResultGenerator.genResult(ResultEnum.NO_CONTENT);

        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(role.getId());
        sysUserRoleMapper.insertSelective(userRole);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getId());
        userInfo.setMobile(mobileNumber);
        userInfo.setEmail(email);
        userInfo.setPhotoUrl(photoUrl);
        userInfo.setCreateDate(new Timestamp(System.currentTimeMillis()));
        userInfo.setName(nickname);
        userInfo.setSex(gender);
        userInfo.setAge(age);
        userInfo.setBirthday(birthday);
        userInfo.setIdcard(idcard);
        userInfo.setAddress(address);

        userInfoMapper.insert(userInfo);
        return ResultGenerator.genSuccessResult();
    }
}
