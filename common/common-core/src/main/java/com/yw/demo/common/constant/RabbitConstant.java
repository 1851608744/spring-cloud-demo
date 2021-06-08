package com.yw.demo.common.constant;

/**
 * @author yangwei
 * @data 2021/06/08
 **/
public class RabbitConstant {

    //立即消费的队列名称
    public static final String IMMEDIATE_QUEUE = "queue.demo.immediate";
    //立即消费的exchange
    public static final String IMMEDIATE_EXCHANGE = "exchange.demo.immediate";
    //立即消费的routing-key 名称
    public static final String IMMEDIATE_ROUTING_KEY = "routingkey.demo.immediate";
    //延时消费的队列名称
    public static final String DELAY_QUEUE= "queue.demo.delay";
    //延时消费的exchange
    public static final String DEAD_LETTER_EXCHANGE = "exchange.demo.delay";
    //延时消费的routing-key名称
    public static final String DELAY_ROUTING_KEY = "routingkey.demo.delay";
    //立即消费的队列名称
    public static final String X_DELAY_QUEUE_XDELAY = "queue.xdelay.immediate";
    //延时的exchange
    public static final String DELAYED_EXCHANGE_XDELAY = "exchange.xdelay.delayed";

    public static final String DELAY_ROUTING_KEY_XDELAY = "routingkey.xdelay.delay";//
}
