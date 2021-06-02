package com.yw.demo.service;

/**
 * @author yangwei
 * @data 2021/06/02
 **/
public interface QueueMessageService {

    /**
     * 发送正常队列消息
     * @param exchangeKey
     * @param routingKey
     * @param message
     */
    void send(String exchangeKey, String routingKey, Object message);

    /**
     * 发送延迟队列消息
     * @param exchangeKey
     * @param routingKey
     * @param message
     * @param msec
     */
    void delayedSend(String exchangeKey, String routingKey, Object message, int msec);

}
