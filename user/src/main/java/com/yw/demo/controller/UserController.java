package com.yw.demo.controller;

import com.yw.demo.domain.User;
import com.yw.demo.rabbitmq.sender.Sender;
import com.yw.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangwei
 * @data 2021/05/31
 **/
@Api(value = "用户操作接口")
@RestController
public class UserController {

    @Autowired
    private Sender sender;

    @Resource
    private UserService userService;


    @ApiOperation(value = "添加用户")
    @GetMapping("/saveUser")
    public String saveUser(@ApiParam(value = "用户对象") @RequestBody User user) {
        userService.saveUser(user);
        return "success";
    }


    //@ApiOperation("用户登录接口")
    //@GetMapping("/login")
    //public User login(@ApiParam(value = "用户登录信息") @RequestBody User user) {
    //    return userService.queryUser(user);
    //}

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/detail")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/send")
    public String sendDirectMessage() {
        sender.send("TestDirectExchange","TestDirectRouting");
        return "ok";
    }

    @GetMapping("/topic-man")
    public String topicMan() {
        sender.sendTopicMessage1();
        return "ok";
    }

    @GetMapping("/topic-woman")
    public String topicWoman() {
        sender.sendTopicMessage2();
        return "ok";
    }

    @GetMapping("/fanout")
    public String fanout() {
        sender.sendFanoutMessage();
        return "ok";
    }

    @GetMapping("/testMsgAck")
    public String testMsgAck() {
        sender.send("no-existent", null);
        return "ok";
    }

    @GetMapping("/noQueue")
    public String noQueue() {
        sender.send("lonelyDirectExchange", "TestDirectRouting");
        return "ok";
    }


}
