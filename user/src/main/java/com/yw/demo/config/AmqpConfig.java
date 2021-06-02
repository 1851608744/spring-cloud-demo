//package com.yw.demo.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * rabbit配置类 （声名交换机、队列以及他们的绑定关系）
// * @author yangwei
// * @data 2021/06/02
// **/
//@Configuration
//public class AmqpConfig {
//
//    // 交换机名称（延迟队列）
//    public static final String DELAYED_EXCHANGE_KEY = "exchange.delayed";
//    // 队列名称 （成团状态更新）
//    public static final String ORDER_GROUP_JOIN_QUEUE_KEY = "order.group.join.delayed";
//    // 队列路线/绑定关系 （成团状态更新）
//    public static final String ORDER_GROUP_JOIN_ROUTK = "order.group.join.delayed";
//
//
//    /**
//     * 成员状态跟同学推送队列
//     * @return
//     */
//    @Bean
//    public CustomExchange customExchange() {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("x-delayed-type", "direct");
//        return new CustomExchange(DELAYED_EXCHANGE_KEY, "x-delayed-message", true, false, map);
//    }
//
//    @Bean
//    public Queue orderGroupJoinQueue(){
//        return new Queue(ORDER_GROUP_JOIN_QUEUE_KEY, true);
//    }
//
//    @Bean
//    public Binding flashSalePushBinding(CustomExchange delayedExchange, Queue orderGroupJoinQueue) {
//        Binding binding=BindingBuilder.bind(orderGroupJoinQueue)
//                .to(delayedExchange)
//                .with(ORDER_GROUP_JOIN_ROUTK)
//                .noargs();
//
//        return binding;
//    }
//}
