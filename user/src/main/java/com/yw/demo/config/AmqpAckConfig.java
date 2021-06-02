//package com.yw.demo.config;
//
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.rabbit.support.CorrelationData;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
///**
// * @Description 消息发送确认
// * ConfirmCallback 只确定消息是否正确到达Exchange 中
// * ReturnCallback  消息没有正确到达队列时触发回调，如果正确到达队列不执行
// * 1. 如果消息没有到exchange，则confirm回调，ack = false
// * 2. 如果消息到达exchange，则confirm回调，ack=ture
// * 3. exchange到queue成功，则不回调return
// * 4. exchange到queue失败，则回调return
// * @author yangwei
// * @data 2021/06/02
// **/
//@Component
//public class AmqpAckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
//
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    @PostConstruct
//    public void init() {
//        rabbitTemplate.setConfirmCallback(this);
//        rabbitTemplate.setReturnCallback(this);
//    }
//
//
//    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
//        if (ack) {
//
//        } else {
//            System.out.println("消息发送确认失败：" + cause);
//        }
//    }
//
//    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
//        System.out.println("return--message:" + new String(message.getBody()) + ",replyCode:" + replyCode
//                + ",replyText:" + replyText + ",exchange:" + exchange + ",routingKey:" + routingKey);
//    }
//}
