package com.yw.demo.service;

import org.junit.jupiter.api.Test;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yangwei
 * @description
 * @data 2021/07/22
 **/
@SpringBootTest
public class RedisTest {

    @Autowired
    private RedissonClient redissonClient;

    @Resource
    private Redisson redisson;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() {

        redisson.getLock("myLock");

        RLock lock = redissonClient.getLock("myLock");
        //加锁 默认生存时间是30s，启动看门狗（如果原程序还持有锁，自动续期）
        lock.lock();
        //10秒以后自动解锁，不启动看门狗，锁到期不续
        lock.lock(10, TimeUnit.SECONDS);
        //可重入锁
        lock.tryLock();
        try {
            //尝试加锁最多等待100秒，上锁以后10秒自动解锁，不启动看门狗
            //这里的第二个参数leaseTimeout 设置为 10 就会覆盖 看门狗的设置（看门狗无效），在10秒后锁就自动失效，不会去续期；如果是 -1 ，就表示 使用看门狗的默认值。
            lock.tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //解锁
        lock.unlock();
    }



}
