package com.yw.demo.controller;

import com.yw.demo.common.dto.SysUserDto;
import com.yw.demo.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @description
 * @data 2021/07/13
 **/
@RestController
public class Controller {

    @Autowired
    private Sender sender;

    @GetMapping("/send/insert")
    public String sendInsert(@RequestBody SysUserDto userDto) {
        sender.insert(userDto);
        return "Insert OK!";
    }

    @GetMapping("/send/update")
    public String sendUpdate(@RequestBody SysUserDto userDto) {
        sender.update(userDto);
        return "Update OK!";
    }

}
