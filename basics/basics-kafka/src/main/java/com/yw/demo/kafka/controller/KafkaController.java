package com.yw.demo.kafka.controller;

import com.yw.demo.kafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangwei
 * @data 2021/06/17
 **/
@RestController
public class KafkaController {

    @Autowired
    private KafkaProducer kafkaProducer;

    @GetMapping("/kafka/send")
    public void send() {
        kafkaProducer.send();
    }

}
