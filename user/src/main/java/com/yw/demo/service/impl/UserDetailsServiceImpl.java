//package com.yw.demo.service.impl;
//
//import com.yw.demo.domain.SysUser;
//import com.yw.demo.mapper.SysPermissionMapper;
//import com.yw.demo.mapper.SysUserMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author yangwei
// * @data 2021/06/01
// **/
//@Slf4j
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Resource
//    private SysUserMapper sysUserMapper;
//    @Autowired
//    private SysPermissionMapper permissionMapper;
//
//
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        log.info("----------------------" + "in userDetails ");
//        SysUser sysUser = new SysUser();
//        sysUser.setName(username);
//        // 通过账号获取用户信息
//        SysUser user = sysUserMapper.getOneByName(sysUser);
//        log.info("--------------------------------------- SysUser");
//        // 角色集合
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//        if (user == null) {
//            throw new UsernameNotFoundException("用户不存在");
//        }
//        System.err.println("sysUser===============" + user.toString());
//        List<String> permissions = permissionMapper.getNameByUserId(user.getId());
//        log.info("-----------------------Permission");
//        for (String permission : permissions) {
//            if (permission != null) {
//                // 角色必须以‘ROLE_’开头，数据库中没有，则在这里加
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + permission);
//                System.out.println(grantedAuthority);
//                grantedAuthorities.add(grantedAuthority);
//            }
//        }
//        System.err.println("grantedAuthorities===============" + grantedAuthorities);
//        return new User(user.getName(), user.getPassword(), grantedAuthorities);
//
//    }
//}
