package com.api.base.config.auth.service;

import com.api.base.model.SysPower;
import com.api.base.model.SysRole;
import com.api.base.model.SysUser;
import com.api.base.service.SysPowerService;
import com.api.base.service.SysUserService;
import com.api.core.config.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailsServic implements UserDetailsService {

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysPowerService powerService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = userService.findBy("username",s);
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //菜单控制

        List<String> roles =  userService.getMenu(user.getId()).stream()
                .map(SysRole::getDescription).distinct()
                .collect(Collectors.toList());

        //api 权限控制
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysPower> powers = powerService.getByUser(user.getId());
        if (powers.size() > 0) {
            for (SysPower p : powers) {
                authorities.add(new SimpleGrantedAuthority(p.getUrl()));
            }
        }

        return new AuthUser(user.getUsername(), user.getPassword(), authorities, roles, user.getId(), user.getType(),user.getNickname(),user.getAvatar());

    }
}
