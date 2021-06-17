package com.yw.demo.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangwei
 * @data 2021/06/10
 **/
@Configuration
public class KafkaConfig {

    /**
     * 创建一个名为topic-test的Topic并设置分区数为8，分区副本数为2
     * @return
     */
    //@Bean
    public NewTopic initialTopic() {
        return new NewTopic("topic-demo", 8, (short) 1);
    }

    /**
     * 如果要修改分区数，只需要修改配置值重启项目即可
     * 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
     * @return
     */
    //@Bean
    public NewTopic updateTopic() {
        return new NewTopic("testtopic", 10, (short) 2);
    }



}
