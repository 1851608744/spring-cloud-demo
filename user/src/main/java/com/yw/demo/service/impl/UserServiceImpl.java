package com.yw.demo.service.impl;

import com.yw.demo.common.utils.DateTimeUtil;
import com.yw.demo.domain.User;
import com.yw.demo.dto.UserDTO;
import com.yw.demo.mapper.UserMapper;
import com.yw.demo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yangwei
 * @data 2021/05/31
 **/
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;


    public void saveUser(User user) {
        user.setCreateTime(DateTimeUtil.getCurrDate());
        userMapper.insert(user);
    }

    public User queryUser(User user) {
        return userMapper.getByAccountAndPassword(user);
    }

    public void updateUser(User user) {

    }
}
