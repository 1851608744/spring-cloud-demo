package com.yw.demo.controller;

import com.yw.demo.service.IpInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @data 2021/06/17
 **/
@RestController
@RequestMapping("/")
public class SentinelController {

    @Autowired
    private IpInfoService ipInfoService;

    @RequestMapping(value = "/ip",method = {RequestMethod.GET})
    public String ipInfo() {
        return ipInfoService.getIpInfo();
    }

}
