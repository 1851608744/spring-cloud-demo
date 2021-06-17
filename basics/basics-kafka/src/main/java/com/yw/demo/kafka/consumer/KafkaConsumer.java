package com.yw.demo.kafka.consumer;

import com.yw.demo.kafka.producer.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author yangwei
 * @data 2021/06/10
 **/
@Slf4j
@Component
public class KafkaConsumer {

    /**
     * 消费监听
     * @param record
     */
    @KafkaListener(topics = KafkaProducer.TOPIC_DEMO, groupId = "myGroup")
    public void message(ConsumerRecord<?, ?> record, Acknowledgment ack, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        log.info("消费者----in");
        Optional message = Optional.ofNullable(record.value());
        if (message.isPresent()) {
            Object msg = message.get();
            log.info("topic-demo 消费了： Topic：" + topic + ",Message:" + msg);
            ack.acknowledge();
        }

    }
}
