package com.yw.demo.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author yangwei
 * @data 2021/06/02
 **/
@Component
//监听的队列名称 TestDirectQueue
//@RabbitListener(queues = "TestDirectQueue")
public class DirectReceiver {

    @RabbitHandler
    public void process(Map msg) {
        System.out.println("DirectReceiver消费者收到消息：" + msg.toString());
    }


}
