package org.xinyu.com.mq.impl;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.xinyu.com.mq.GsonUtils;
import org.xinyu.com.mq.LoggerUtils;
import org.xinyu.com.mq.Msg;
import org.xinyu.com.mq.MsgHandler;
import org.xinyu.com.mq.kafka.consumer.KafkaConsumerServer;

import java.io.Serializable;

public abstract class ConsumerHandlerImpl extends KafkaConsumerServer implements Serializable {

    public abstract void processMsg(Msg msg);

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        LoggerUtils.info(ConsumerHandlerImpl.class,"=============kafkaConsumer开始消费=============");
        Msg msg = new Msg();
        msg.setTopic(record.topic());
        msg.setKey(record.key());
        msg.setValue(record.value());
        msg.setOffset(record.offset());
        msg.setPartition(record.partition());
        msg.setTimestamp(record.timestamp());
        try {

            this.processMsg(msg);
            if( kafkaOffsetService != null ){
                kafkaOffsetService.process(record);
            }

        } catch (Exception ex){
            String erroInfo = GsonUtils.toJsonStr(ex);
            LoggerUtils.info(ConsumerHandlerImpl.class,"~~~~~~~~~~~~~kafkaConsumer消费失败并结束~~~~~~~~~~~~~, " + msg.toString() + erroInfo);
        }
        LoggerUtils.info(ConsumerHandlerImpl.class,"~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> record, Boolean isAcknowledgment) {
        LoggerUtils.info(ConsumerHandlerImpl.class,"=============kafkaConsumer开始消费=============");

        Msg msg = new Msg();
        msg.setTopic(record.topic());
        msg.setKey(record.key());
        msg.setValue(record.value());
        msg.setOffset(record.offset());
        msg.setPartition(record.partition());
        msg.setTimestamp(record.timestamp());
        try {
            LoggerUtils.info(ConsumerHandlerImpl.class,"=============Msg============="+ GsonUtils.toJsonStr(msg));
            this.processMsg(msg);
            if( kafkaOffsetService != null && isAcknowledgment ){
                kafkaOffsetService.process(record);
            }

        } catch (Exception ex){
            String erroInfo = GsonUtils.toJsonStr(ex);
            LoggerUtils.info(ConsumerHandlerImpl.class,"~~~~~~~~~~~~~kafkaConsumer消费失败并结束~~~~~~~~~~~~~, " + msg.toString() + erroInfo);
        }
        LoggerUtils.info(ConsumerHandlerImpl.class,"~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }
}
