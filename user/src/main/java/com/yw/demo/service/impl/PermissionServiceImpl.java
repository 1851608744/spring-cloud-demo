package com.yw.demo.service.impl;

import com.yw.demo.domain.SysPermission;
import com.yw.demo.mapper.SysPermissionMapper;
import com.yw.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @author yangwei
 * @data 2021/06/01
 **/
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    public List<SysPermission> findAll() {
        return null;
    }


}
