package com.yw.demo.service.impl;

import com.yw.demo.common.dto.SysUserDto;
import com.yw.demo.common.utils.GsonUtils;
import com.yw.demo.domain.SysPermission;
import com.yw.demo.domain.SysUser;
import com.yw.demo.mapper.SysPermissionMapper;
import com.yw.demo.mapper.SysUserMapper;
import com.yw.demo.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/03
 **/
@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    public SysUserDto querySysUserInfo(String username) {
        SysUser sysUser = new SysUser();
        sysUser.setName(username);
        log.info("----------------------------------" + sysUser);
        //获取用户信息
        SysUser user = sysUserMapper.getOneByName(sysUser);
        SysUserDto sysUserDto = GsonUtils.toBean(user, SysUserDto.class);
        //设置用户许可信息
        List<String> permissionNames = sysPermissionMapper.getNameByUserId(sysUserDto.getId());
        sysUserDto.setRoles(permissionNames);
        return sysUserDto;
    }
}
