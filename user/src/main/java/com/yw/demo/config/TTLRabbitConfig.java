package com.yw.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 利用Time to Live（TTL）、Dead Letter Exchanges（DLX）实现延迟队列
 * @author yangwei
 * @data 2021/06/08
 **/
@Configuration
public class TTLRabbitConfig {

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


    @Autowired
    private RabbitTemplate rabbitTemplate;


    //创建一个立即消费的队列
    @Bean
    public Queue immediateQueue() {
        //第一个创建的queue的名字，第二个参数是是否支持持久化
        return new Queue(IMMEDIATE_QUEUE, true);
    }

    @Bean
    public Queue delayQueue() {
        Map<String, Object> params = new HashMap<>();
        //x-dead-letter-exchange 声名了队列里的死信转发到DLX名称
        params.put("x-dead-letter-exchange", IMMEDIATE_EXCHANGE);
        //x-dead-letter-routing-key 声名里这些死信在转发时携带的routing-key名称
        params.put("x-dead-letter-routing-key", IMMEDIATE_ROUTING_KEY);
        return new Queue(DELAY_QUEUE, true, false, false, params);
    }

    @Bean
    public DirectExchange immediateExchange() {
        //一共由三种构造方法，可以只传exchange的名字，第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
        //第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
        return new DirectExchange(IMMEDIATE_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange deadLetterExchanger() {
        //一共由三种构造方法，可以只传exchange的名字，第二种，可以传exchange名字，是否支持持久化，是否可以自动删除，
        //第三种在第二种参数上可以增加Map，Map中可以存放自定义exchange中的参数
        return new DirectExchange(DEAD_LETTER_EXCHANGE, true, false);
    }

    /**
     * 把立即消费的队列和立即消费的exchange绑定在一起
     * @return
     */
    @Bean
    public Binding immediateBinding() {
        return BindingBuilder.bind(immediateQueue()).to(immediateExchange()).with(IMMEDIATE_ROUTING_KEY);
    }

    /**
     * 把延迟消费的队列和延迟消费的exchange绑定在一起
     * @return
     */
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(delayQueue()).to(deadLetterExchanger()).with(DELAY_ROUTING_KEY);
    }




}
