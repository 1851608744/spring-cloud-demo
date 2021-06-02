package com.yw.demo.service;

import com.yw.demo.domain.User;

/**
 * @author yangwei
 * @data 2021/05/31
 **/

public interface UserService {


    void saveUser(User user);

    User queryUser(User user);

    void updateUser(User user);

}
