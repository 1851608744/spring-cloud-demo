package com.yw.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.yw.demo.domain.User;
import com.yw.demo.mapper.UserMapper;
import com.yw.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author yangwei
 * @description
 * @data 2021/07/19
 **/
public class ServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert(User user) {
        userMapper.insert(user);
        update(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(new User(), new LambdaUpdateWrapper<User>().
                eq(User::getId, 1).set(User::getUserName, "Hunter"));
    }

    /**
     * 声明式事务
     * propagation：事务的传播机制
     * isolation：事务的隔离级别
     * readOnly：是否只读
     * timeout：超时时间
     * rollbackFor：回滚
     * @param user
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = false, timeout = 30, rollbackFor = Exception.class)
    public void updateUser(User user) {

    }

}
