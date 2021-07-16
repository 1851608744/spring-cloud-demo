package com.yw.demo.service;

import com.yw.demo.domain.User;
import com.yw.demo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        System.out.println(7200 + 360 * 2 - 7450);

    }

}