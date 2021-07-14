package com.yw.demo.rocketmq.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @description 设置消息监听
 * @data 2021/07/14
 * 监听组：监听topic：监听tag（默认监听topic下所有）
 * 监听消费模式：默认负载均衡：CLUSTERING（每一个消息只发送给一个消费者）、广播模式：BROADCASTING（发送给所有消费者）
 **/
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "${rocketmq.consumer.topic}",
        selectorExpression = "${rocketmq.consumer.tags}", messageModel = MessageModel.BROADCASTING)
public class ConsumerListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String s) {
        System.out.println(s);
    }
}
