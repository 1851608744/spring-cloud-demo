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
public class Receiver {

    //@RabbitListener(queues = "fanout.A")
    @RabbitHandler
    public void FanoutAProcess(Map map) {
        System.out.println("FanoutReceiverA消费者收到消息:" + map.toString());
    }

    //@RabbitListener(queues = "fanout.B")
    @RabbitHandler
    public void FanoutBProcess(Map map) {
        System.out.println("FanoutReceiverB消费者收到消息:" + map.toString());
    }

    //@RabbitListener(queues = "fanout.C")
    @RabbitHandler
    public void FanoutCProcess(Map map) {
        System.out.println("FanoutReceiverC消费者收到消息:" + map.toString());
    }


}
