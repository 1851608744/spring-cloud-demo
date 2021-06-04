package com.yw.demo.auth.service;

import com.yw.demo.common.dto.SysUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author yangwei
 * @data 2021/06/03
 **/
@FeignClient("user")
public interface SysUserService {

    /**
     * 用户权限信息
     * @param username
     * @return
     */
    @GetMapping("/querySysUserInfo")
    SysUserDto querySysUserInfo(@RequestParam("username") String username);

    @GetMapping("/details")
    String getDetails();

    @RequestMapping("/login")
    String login(@RequestParam("user") String user);

}
