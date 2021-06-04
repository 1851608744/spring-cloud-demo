package com.yw.demo.auth.controller;

import com.yw.demo.auth.service.SysUserService;
import com.yw.demo.common.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @data 2021/06/04
 **/
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/getUserDetails")
    public SysUserDto getUserDetails(@RequestParam("username") String username) {
        return sysUserService.querySysUserInfo(username);
    }

    @GetMapping("/details")
    public String details() {
        return sysUserService.getDetails();
    }

    @RequestMapping("/login")
    public String login(@RequestParam("user") String user) {
        return sysUserService.login(user);
    }

}
