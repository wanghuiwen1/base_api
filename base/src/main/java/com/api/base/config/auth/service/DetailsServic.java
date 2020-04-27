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

        List<SysRole> roles = userService.getRole(user.getId());
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> rolestr = new ArrayList<>();


        List<SysPower> powers = new ArrayList<>();
        for (SysRole r : roles) {
            rolestr.add(r.getDescription());
            authorities.add(new SimpleGrantedAuthority(r.getDescription()));
            List<SysPower> powers1 = powerService.getByRole(r.getId());
            if (powers1 != null) {
                powers.addAll(powers1);
            }
        }

        if (powers.size() > 0) {
            for (SysPower p : powers) {
                authorities.add(new SimpleGrantedAuthority(p.getUrl()));
            }
        }

        return new AuthUser(user.getUsername(), user.getPassword(), authorities, rolestr, user.getId(), user.getType(),user.getNickname(),user.getAvatar());

    }
}
