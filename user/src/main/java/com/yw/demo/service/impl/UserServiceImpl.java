package com.yw.demo.service.impl;

import com.yw.demo.common.utils.DateTimeUtil;
import com.yw.demo.domain.User;
import com.yw.demo.dto.UserDTO;
import com.yw.demo.mapper.UserMapper;
import com.yw.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author yangwei
 * @data 2021/05/31
 **/
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;


    @Override
    public void saveUser(User user) {
        user.setCreateTime(DateTimeUtil.getCurrDate());
        userMapper.insert(user);
    }

    @Override
    public User queryUser(User user) {
        User userInfo = userMapper.getByAccountAndPassword(user);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("sysUserDto",userInfo);
        return userInfo;
    }

    @Override
    public void updateUser(User user) {

    }
}
