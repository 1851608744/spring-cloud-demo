package com.yw.demo.service.impl;

import com.yw.demo.domain.SysPermission;
import com.yw.demo.domain.SysUser;
import com.yw.demo.mapper.SysUserMapper;
import com.yw.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Autowired
    private PermissionService permissionService;


    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = new SysUser();
        user.setName(username);
        System.out.println(user);
        // 通过账号获取用户信息
        SysUser sysUser = sysUserMapper.getOne(user);
        // 角色集合
        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        if (sysUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        System.err.println("sysUser===============" + sysUser.toString());
        List<SysPermission> permissions = permissionService.findByAdminUserId(sysUser.getId());
        for (SysPermission permission : permissions) {
            if (permission != null && permission.getName() != null) {
                // 角色必须以‘ROLE_’开头，数据库中没有，则在这里加
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission.getName());
                System.out.println(grantedAuthority);
                grantedAuthorities.add(grantedAuthority);
            }
        }
        System.err.println("grantedAuthorities===============" + grantedAuthorities);
        return new User(sysUser.getName(), sysUser.getPassword(), grantedAuthorities);

    }
}
