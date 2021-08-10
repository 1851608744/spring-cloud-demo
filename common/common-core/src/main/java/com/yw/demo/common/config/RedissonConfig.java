package com.yw.demo.common.config;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangwei
 * @description
 * @data 2021/07/27
 **/
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson() {
        //单机模式
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379").setDatabase(0);
        ////哨兵
        //config.useSentinelServers();
        ////集群
        //config.useClusterServers();
        ////主从
        //config.useMasterSlaveServers();
        return (Redisson) Redisson.create(config);
    }

}
