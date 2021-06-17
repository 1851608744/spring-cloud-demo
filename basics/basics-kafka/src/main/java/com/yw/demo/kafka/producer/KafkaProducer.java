package com.yw.demo.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @data 2021/06/10
 **/
@Slf4j
@RestController
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_DEMO = "topic-demo";
    public static final String MY_GROUP = "my_group";

    public void send() {
        log.info("kafka生产者-----start");
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("topic-demo", "kafka生产者");
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info(TOPIC_DEMO + " - 生产者 发送消息失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                log.info(TOPIC_DEMO + " - 生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });

    }

}
