package com.yw.demo.rabbitmq.receiver;

import com.yw.demo.common.constant.RabbitConstant;
import com.yw.demo.common.utils.DateTimeUtil;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author yangwei
 * @data 2021/06/08
 **/
@Component
@EnableRabbit
@Configuration
public class XdelayReceiver {

    @RabbitListener(queues = RabbitConstant.X_DELAY_QUEUE_XDELAY)
    public void get(String msg) {
        System.out.println("收到延时消息：" + DateTimeUtil.getCurrDateTimeStr() + " Delay sent.");
        System.out.println("收到延时消息：" + msg);
    }

}
