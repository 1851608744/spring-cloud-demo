package com.yw.demo.rocketmq.receiver;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @description
 * @data 2021/07/12
 **/
@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "rocket-group", topic = "user_topic", selectorType = SelectorType.TAG, selectorExpression = "insert_tag")
public class RocketMQReceiver implements RocketMQListener {

    @Override
    public void onMessage(Object o) {
        log.info(">>> user message listener :{}" + o);
    }


}
