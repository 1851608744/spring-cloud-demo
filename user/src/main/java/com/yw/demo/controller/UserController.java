package com.yw.demo.controller;

import com.yw.demo.aspect.TimeConsuming;
import com.yw.demo.common.dto.SysUserDto;
import com.yw.demo.domain.User;
//import com.yw.demo.sender.ImmediateSender;
//import com.yw.demo.sender.Sender;
//import com.yw.demo.sender.XdelaySender;
import com.yw.demo.service.SysUserService;
import com.yw.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author yangwei
 * @data 2021/05/31
 **/
@Api(value = "用户操作接口")
@RestController
public class UserController {


    @Resource
    private UserService userService;

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "添加用户")
    @GetMapping("/saveUser")
    public String saveUser(@ApiParam(value = "用户对象") @RequestBody User user) {
        userService.saveUser(user);
        return "success";
    }


    @TimeConsuming
    @ApiOperation("用户登录接口")
    @GetMapping("/queryUser")
    public User login(@ApiParam(value = "用户登录信息") @RequestBody User user ) {
        return userService.queryUser(user);
    }



    @ApiOperation("用户登录授权信息")
    @GetMapping("/querySysUserInfo")
    public SysUserDto querySysUserByName(@ApiParam(value = "用户名称")
                                          @RequestParam("username") String username) {
        return sysUserService.querySysUserInfo(username);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/user/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("user") String user) {
        return "login" + user;
    }





}
