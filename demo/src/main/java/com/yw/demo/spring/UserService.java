package com.yw.demo.spring;

import com.yw.demo.spring.dto.User;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author yangwei
 * @description
 * @data 2021/07/03
 **/
public class UserService implements InitializingBean { 

    private User user;

    public UserService() {
        System.out.println("构造方法");
    }

    public void setUser(User user) {
        System.out.println("set方法");
        this.user = user;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化后"       );
    }

}
