package com.yw.demo.rabbitmq.sender;

import com.yw.demo.common.utils.DateTimeUtil;
import com.yw.demo.config.XdelayConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @data 2021/06/08
 **/
@Component
public class XdelaySender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String msg, int delayTime) {
        System.out.println("msg = " + msg + " delayTime" + delayTime);
        rabbitTemplate.convertAndSend(XdelayConfig.DELAYED_EXCHANGE_XDELAY,
                XdelayConfig.DELAY_ROUTING_KEY_XDELAY, msg, message -> {
                    message.getMessageProperties().setDelay(delayTime);
                    System.out.println(DateTimeUtil.getCurrDateTimeStr() + "Delay sent.");
                    return message;
                });
    }

}
