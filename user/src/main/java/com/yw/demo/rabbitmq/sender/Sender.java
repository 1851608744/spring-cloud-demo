package com.yw.demo.rabbitmq.sender;

import com.yw.demo.common.utils.DateTimeUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 消息发送者 - Producer
 *
 * @author yangwei
 * @Component Producer类型的对象，必须交由Spring容器管理
 * 使用SpringBoot提供的AMQP启动器，来访问rabbitmq的时候，都是通过AmqpTemplate来实现的
 * 如果全局配置文件中，配置了rabbitmq相关内容，且工程依赖了starter-amqp，则spring容器 自动创建AmqpTemplate对象
 * @data 2021/06/02
 **/
@Component
public class Sender {
    //使用RabbitTemplate,这提供了接收/发送等等方法
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 交换器名称
     */
    @Value("${mq.config.exchange.topic}")
    private String topicExchange;

    @Value("${mq.config.exchange.fanout}")
    private String fanoutExchange;
    /**
     * routingKey 路由键
     */
    @Value("${mq.config.queue.man.routing.key}")
    private String manRoutingKey;

    @Value("${mq.config.queue.woman.routing.key}")
    private String womanRoutingKey;

    /**
     * fanout队列
     */
    @Value("${mq.config.queue.fanoutA}")
    private String fanoutA;
    @Value("${mq.config.queue.fanoutB}")
    private String fanoutB;
    @Value("${mq.config.queue.fanoutC}")
    private String fanoutC;


    /**
     * 直连交换机 1对1
     */
    public void sendDirectMessage() {
        send("TestDirectExchange","TestDirectRouting");
    }

    /**
     * 主题交换机 1对多 （1对多是对多个队列，多个消费者对单个队列是轮询）
     */
    public void sendTopicMessage1(){
        send(topicExchange,manRoutingKey);
        System.out.println(topicExchange + "\t" + manRoutingKey);
        System.out.println("主题交换机：消息投递成功！！！！！！！！！！！");
    }

    public void sendTopicMessage2() {
        send(topicExchange,womanRoutingKey);
        System.out.println(topicExchange + "\t" + womanRoutingKey);
        System.out.println("主题交换机：消息投递成功！！！！！！！！！！！");
    }

    /**
     * 扇形交换机
     */
    public void sendFanoutMessage() {
        send(fanoutExchange, null);
        System.out.println("扇形交换机：消息投递成功");
    }


    public void send(String exchange, String routingKey) {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = routingKey;
        String createTime = DateTimeUtil.getCurrDateTimeStr();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("messageId", messageId);
        map.put("messageData", messageData);
        map.put("createTime", createTime);
        rabbitTemplate.convertAndSend(exchange, routingKey, map);
    }


}
