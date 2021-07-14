package com.yw.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangwei
 * @data 2021/06/08
 **/
@Configuration
public class XdelayConfig {

    public static final String X_DELAY_QUEUE_XDELAY = "queue.xdelay.immediate";//立即消费的队列名称
    public static final String DELAYED_EXCHANGE_XDELAY = "exchange.xdelay.delayed";//延时的exchange
    public static final String DELAY_ROUTING_KEY_XDELAY = "routingkey.xdelay.delay";//

    @Bean
    public Queue xdelayQueue() {
        return new Queue(X_DELAY_QUEUE_XDELAY, true);
    }

    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_XDELAY, "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding bindingNotify() {
        return BindingBuilder.bind(xdelayQueue())
                .to(delayExchange())
                .with(DELAY_ROUTING_KEY_XDELAY).noargs();
    }
}
