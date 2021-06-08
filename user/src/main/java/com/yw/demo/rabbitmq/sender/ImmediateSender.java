package com.yw.demo.rabbitmq.sender;

import com.yw.demo.common.utils.DateTimeUtil;
import com.yw.demo.config.TTLRabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @author yangwei
 * @data 2021/06/08
 **/
@Component
public class ImmediateSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send(String msg, int delayTime) {
        System.out.println("msg=" +msg + ",delayTime" + delayTime);
        this.rabbitTemplate.convertAndSend(TTLRabbitConfig.DEAD_LETTER_EXCHANGE,
                TTLRabbitConfig.DELAY_ROUTING_KEY, msg, message -> {
            message.getMessageProperties().setExpiration(delayTime + "");
            System.out.println(DateTimeUtil.getCurrDateTimeStr() + "Delay sent.");
            return message;
        });
    }

}
