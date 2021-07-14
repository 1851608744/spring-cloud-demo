package com.yw.demo.sender;

import com.alibaba.fastjson.JSONObject;
import com.yw.demo.common.dto.SysUserDto;
import com.yw.demo.dto.ListSplitter;
import com.yw.demo.dto.OrderStep;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangwei
 * @description
 * @data 2021/07/12
 **/
@Slf4j
@Component
public class Sender {

    private static final String USER_TOPIC = "user_topic:";
    private static final String UPDATE_TAG = "update_tag";
    private static final String INSERT_TAG = "insert_tag";
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.sync-tag}")
    private String syncTag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.async-tag}")
    private String asyncTag;

    @Value(value = "${rocketmq.producer.topic}:${rocketmq.producer.oneway-tag}")
    private String onewayTag;

    /**
     * 发送同步消息
     * @param id
     * @return
     */
    public String pushMessage(int id) {
        Message<String> message = message(id);
        //设置发送地和消息信息并发送同步消息
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message);
        log.info("pushMessage finish : " + id + ", sendResult :" + JSONObject.toJSONString(sendResult));
        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
            return sendResult.getMsgId() + " : " + sendResult.getSendStatus();
        }
        return null;
    }

    /**
     * 发送异步消息
     * @param id
     * @return
     */
    public String pushAsyncMessage(int id) {
        Message<String> message = message(id);
        //设置发送地和消息信息并发送异步消息
        rocketMQTemplate.asyncSend(asyncTag, message, new SendCallbackListener(id));
        log.info("pushAsyncMessage finish : {}" + id);
        return null;
    }

    /**
     * 发送单向消息（不关注发送结果：记录日志）
     * @param id
     * @return
     */
    public String pushOneWayMessage(int id) {
        log.info("pushOneWayMessage start : {}" + id);
        Message<String> message = message(id);
        //设置发送地和消息信息并发送单向消息
        rocketMQTemplate.sendOneWay(onewayTag, message);
        log.info("pushOneWayMessage finish : {}" + id);
        return null;
    }

    /**
     * 发送包含顺序的单向消息
     * @param id
     * @return
     */
    public String pushSequeueMessage(int id) {
        log.info("pushSequeueMessage start : {}" + id);
        for (int i = 0; i < 3; i++) {
            //处理当前订单唯一标识
            String myId = id + "" + i;
            //获取当前订单的操作步骤列表
            List<OrderStep> myOrderSteps = OrderStep.buildOrderSteps(myId);
            //依次发消息队列
            for (OrderStep orderStep : myOrderSteps) {
                String messageStr = String.format("order id : %s, desc : %s", orderStep.getId(), orderStep.getDesc());
                Message<String> message = MessageBuilder.withPayload(messageStr)
                        .setHeader(RocketMQHeaders.KEYS, orderStep.getId())
                        .build();
                //设置顺序下发
                rocketMQTemplate.setMessageQueueSelector(new MessageQueueSelector() {
                    /**
                     * 设置放入同一个队列的规则
                     * @param list
                     * @param message
                     * @param o
                     * @return
                     */
                    @Override
                    public MessageQueue select(List<MessageQueue> list, org.apache.rocketmq.common.message.Message message, Object o) {
                        //根据当前消息的id，使用固定算法获取需要下发的队列
                        //（使用当前id和消息队列个数进行取模获取需要下发的队列，id和队列数量一样时，选择的队列坑肯定一样）
                        int queueNum = Integer.valueOf(String.valueOf(o)) % list.size();
                        log.info(String.format("queueNum : %s, message : %s", queueNum, new String(message.getBody())));
                        return list.get(queueNum);
                    }
                });
                //设置发送地和消息信息并发送消息（Orderly）
                rocketMQTemplate.syncSendOrderly(syncTag, message, orderStep.getId());
            }
        }
        log.info("pushSequeueMessage finish : " + id);
        return null;
    }

    /**
     * 延迟消息
     * @param id
     * @return
     */
    public String pushDelayMessage(int id) {
        Message<String> message = message(id);
        // 设置超时和延时推送
        // 超时时针对请求broker然后结果返回给product的耗时
        // 现在RocketMq并不支持任意时间的延时，需要设置几个固定的延时等级，从1s到2h分别对应着等级1到18
        // private String messageDelayLevel = "1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h";
        SendResult sendResult = rocketMQTemplate.syncSend(syncTag, message, 1 * 1000L, 4);
        log.info("pushDelayMessage finish : " + id + ", sendResult : " + JSONObject.toJSONString(sendResult));
        if (sendResult.getSendStatus() == SendStatus.SEND_OK) {
            return sendResult.getMsgId() + " : " + sendResult.getSendStatus();
        }
        return null;
    }

    /**
     * 同时发送10个单向消息（真正的批量）
     * @param id
     * @return
     */
    public String pushBatchMessage(int id) {
        log.info("pushBatchMessage start : " + id);
        // 创建消息集合
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String myId = id + "" + i;
            // 处理当前订单唯一标识
            String messageStr = "order id : " + myId;
            Message<String> message = MessageBuilder.withPayload(messageStr)
                    .setHeader(RocketMQHeaders.KEYS, myId)
                    .build();
            messages.add(message);
        }
        //批量下发消息到broker，不支持消息顺序操作， 并且对消息体有大小限制（不超过4M）
        ListSplitter splitter = new ListSplitter(messages, 1024 * 1924 * 4);
        while (splitter.hasNext()) {
            List<Message> listItem = splitter.next();
            rocketMQTemplate.syncSend(syncTag, listItem);
        }
        log.info("pushBatchMessage finish : " + id);
        return null;
    }

    /**
     * sql过滤消息
     *
     * @param id 消息
     * @return 结果
     */
    public String pushSqlMessage(int id) {
        log.info("pushSqlMessage start : " + id);
        // 创建消息集合
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String myId = id + "" + i;
            // 处理当前订单唯一标识
            String messageStr = "order id : " + myId;
            Message<String> message = MessageBuilder.withPayload(messageStr)
                    .setHeader(RocketMQHeaders.KEYS, myId)
                    .setHeader("money", i)
                    .build();
            messages.add(message);
        }
        rocketMQTemplate.syncSend(syncTag, messages);
        log.info("pushSqlMessage finish : " + id);
        return null;
    }

    /**
     * 事务消息
     *
     * @param id 消息
     * @return 结果
     */
    public String  pushTransactionMessage( int id) {
        log.info("pushTransactionMessage start : " + id);
        // 创建消息
        String messageStr = "order id : " + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .setHeader("money", 10)
                .setHeader(RocketMQHeaders.TRANSACTION_ID, id)
                .build();
        TransactionSendResult transactionSendResult = rocketMQTemplate.sendMessageInTransaction(syncTag, message, null);
        log.info("pushTransactionMessage result : " + JSONObject.toJSONString(transactionSendResult));
        log.info("pushTransactionMessage finish : " + id);
        return null;
    }



    public Message<String> message(int id) {
        log.info("pushMessage start : {}" + id);
        //构建消息
        String messageStr = "order id :" + id;
        Message<String> message = MessageBuilder.withPayload(messageStr)
                .setHeader(RocketMQHeaders.KEYS, id)
                .build();
        return message;
    }

    public Boolean update(SysUserDto user) {
        user.setUpdateDate(new Date());
        rocketMQTemplate.asyncSend(USER_TOPIC + UPDATE_TAG, user, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                String msgId = sendResult.getMsgId();
                log.info(">>> async message success, send status={},message id={} <<<", sendResult.getSendStatus().name(), msgId);
            }

            @Override
            public void onException(Throwable throwable) {
                log.error(">>> async message success, exception message={} <<<", throwable.getMessage());

            }
        });
        return Boolean.TRUE;
    }

    public Boolean insert(SysUserDto user) {
        Date now = new Date();
        user.setCreateDate(now);
        user.setUpdateDate(now);
        SendResult sendResult = rocketMQTemplate.syncSend(USER_TOPIC + INSERT_TAG, user);
        log.info(">>>> send message success, send status={} <<<<",sendResult.getSendStatus().name());
        if (sendResult.getSendStatus().name().equals(SendStatus.SEND_OK.name())) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }
}
