package com.yw.demo.auth.service.impl;

import com.yw.demo.auth.service.SysUserService;
import com.yw.demo.common.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/04
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserService sysUserService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 通过账号获取用户信息
        SysUserDto sysUserDto = sysUserService.querySysUserInfo(username);
        // 角色集合
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        if (sysUserDto == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        System.err.println("sysUserDto===============" + sysUserDto.toString());
        for (String permission : sysUserDto.getRoles()) {
            if (permission != null) {
                // 角色必须以‘ROLE_’开头，数据库中没有，则在这里加
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission);
                System.out.println(grantedAuthority);
                grantedAuthorities.add(grantedAuthority);
            }
        }
        System.err.println("grantedAuthorities===============" + grantedAuthorities);
        return new User(sysUserDto.getName(), sysUserDto.getPassword(), grantedAuthorities);

    }
}
