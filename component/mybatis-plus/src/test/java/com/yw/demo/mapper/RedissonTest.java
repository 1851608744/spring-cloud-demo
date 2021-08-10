package com.yw.demo.mapper;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.yw.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.redisson.RedissonLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangwei
 * @description
 * @data 2021/07/27
 **/
@SpringBootTest
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    public void redissonTest() {

        RLock lock = redissonClient.getLock("Lock");
        //加锁
        lock.lock();
        try {
            //需要锁住的方法或者代码
            //在这里就可以写获取数据库最新数据和插入数据库操作
            //获取时一定要通过IdWorker生成的id进行倒叙，使用时间戳的话会出现问题
            long id = IdWorker.getId();
        }finally {
            //解锁
            lock.unlock();
        }
    }

    @Test
    public void list() {

    }


}
