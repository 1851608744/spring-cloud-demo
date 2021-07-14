package com.yw.demo.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yangwei
 * @data 2021/06/02
 **/
@Configuration
public class TopicRabbitConfig {

    @Value("${mq.config.queue.man.routing.key}")
    private String manRoutingKey;

    @Value("${mq.config.queue.woman.routing.key}")
    private String womanRoutingKey;
    @Bean
    public Queue firstQueue() {
        return new Queue(manRoutingKey,true);
    }

    @Bean
    public Queue secondQueue() {
        return new Queue(womanRoutingKey,true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange("topicExchange",true,false);
    }

    /**
     * 将firstQueue和topicExchange绑定,而且绑定的键值为topic.man
     * 这样只要是消息携带的路由键是topic.man,才会分发到该队列
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue())
                .to(exchange())
                .with(manRoutingKey);
    }

    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue())
                .to(exchange())
                .with("topic.#");
    }

}
