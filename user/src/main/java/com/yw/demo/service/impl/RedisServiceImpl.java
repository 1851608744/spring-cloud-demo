package com.yw.demo.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yangwei
 * @description
 * @data 2021/07/20
 **/
@Service
public class RedisServiceImpl<T> {

    @Resource
    private RedisTemplate redisTemplate;

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public List getList(String key, long val1, long val2) {
        return redisTemplate.opsForList().range(key, val1, val2);
    }

    public Object getHash(String key, Object key2) {
        return redisTemplate.opsForHash().get(key, key2);
    }

    public Object getSet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Object getZset(String key, long val1, long val2) {
        return redisTemplate.opsForZSet().range(key, val1, val2);
    }

}