package com.yw.demo.service;

import com.yw.demo.domain.User;

import java.util.Set;

/**
 * @author yangwei
 * @description
 * @data 2021/07/19
 **/
public interface UserService {

    /**
     * 添加方法
     * @param user
     * @return
     */
    void insert(User user);

    /**
     * 修改方法
     * @param user
     * @return
     */
    void update(User user);

    Set getRedisZSet(String key);


}
