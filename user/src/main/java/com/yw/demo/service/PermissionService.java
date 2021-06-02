package com.yw.demo.service;

import com.yw.demo.domain.SysPermission;

import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
public interface PermissionService {

    List<SysPermission> findAll();

    List<SysPermission> findByAdminUserId(int userId);



}
