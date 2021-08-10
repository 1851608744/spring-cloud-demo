package com.yw.demo.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import com.yw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * @author yangwei
 * @description
 * @data 2021/07/16
 **/
@RestController
public class Controller {

    @Autowired
    private UserService userService;

    @GetMapping("/durid/stat")
    public Object druidStat(){
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }


    @GetMapping("/get/redis/zset")
    public Set getRedisZSet(@RequestParam String key) {
        return userService.getRedisZSet(key);
    }

}
