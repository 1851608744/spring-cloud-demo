package com.yw.demo.controller;

/**
 * @author yangwei
 * @description
 * @data 2021/07/14
 **/
public class Controller {


    //@GetMapping("/send")
    //public String sendDirectMessage() {
    //    sender.send("TestDirectExchange","TestDirectRouting");
    //    return "ok";
    //}
    //
    //@GetMapping("/topic-man")
    //public String topicMan() {
    //    sender.sendTopicMessage1();
    //    return "ok";
    //}
    //
    //@GetMapping("/topic-woman")
    //public String topicWoman() {
    //    sender.sendTopicMessage2();
    //    return "ok";
    //}
    //
    //@GetMapping("/fanout")
    //public String fanout() {
    //    sender.sendFanoutMessage();
    //    return "ok";
    //}
    //
    //@GetMapping("/testMsgAck")
    //public String testMsgAck() {
    //    sender.send("no-existent", null);
    //    return "ok";
    //}
    //
    //@GetMapping("/noQueue")
    //public String noQueue() {
    //    sender.send("lonelyDirectExchange", "TestDirectRouting");
    //    return "ok";
    //}

    //@GetMapping("/tllQueue")
    //public String tllQueue() {
    //    immediateSender.send("延迟消息", 300);
    //    //让服务一直挂起， 不然接收消息是，服务已经停了
    //    while (true) {
    //        try {
    //            TimeUnit.SECONDS.sleep(1);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //}
    //
    //@GetMapping("/xDelayQueue")
    //public String xDelayQueue() {
    //    xdelaySender.send("测试消息，10秒", 10000);
    //    xdelaySender.send("测试消息，5秒", 5000);
    //    xdelaySender.send("测试消息，1秒", 1000);
    //    while (true) {
    //        try {
    //            TimeUnit.SECONDS.sleep(1);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }
    //    }
    //}
}
