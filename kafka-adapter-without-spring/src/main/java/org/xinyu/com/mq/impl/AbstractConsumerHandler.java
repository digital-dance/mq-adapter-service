package org.xinyu.com.mq.impl;

import org.xinyu.com.mq.GsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.xinyu.com.mq.LoggerUtils;
import org.xinyu.com.mq.Msg;
import org.xinyu.com.mq.MsgHandler;
import org.xinyu.com.mq.kafka.consumer.KafkaConsumerServer;

import java.io.Serializable;

public abstract class AbstractConsumerHandler extends KafkaConsumerServer implements Serializable {

    MQConsumer mQConsumer;

    public abstract void processMsg(Msg msg);

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        LoggerUtils.info(AbstractConsumerHandler.class,"=============kafkaConsumer开始消费=============");
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
            LoggerUtils.info(AbstractConsumerHandler.class,"~~~~~~~~~~~~~kafkaConsumer消费失败并结束~~~~~~~~~~~~~, " + msg.toString() + erroInfo);
        }
        if(mQConsumer != null) mQConsumer.doStop();
        LoggerUtils.info(AbstractConsumerHandler.class,"~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> record, Boolean isAcknowledgment) {
        LoggerUtils.info(AbstractConsumerHandler.class,"=============kafkaConsumer开始消费=============");

        Msg msg = new Msg();
        msg.setTopic(record.topic());
        msg.setKey(record.key());
        msg.setValue(record.value());
        msg.setOffset(record.offset());
        msg.setPartition(record.partition());
        msg.setTimestamp(record.timestamp());
        try {
            LoggerUtils.info(AbstractConsumerHandler.class,"=============Msg============="+ GsonUtils.toJsonStr(msg));
            this.processMsg(msg);
            if( kafkaOffsetService != null && isAcknowledgment){
                kafkaOffsetService.process(record);
            }

        } catch (Exception ex){
            String erroInfo = GsonUtils.toJsonStr(ex);
            LoggerUtils.info(AbstractConsumerHandler.class,"~~~~~~~~~~~~~kafkaConsumer消费失败并结束~~~~~~~~~~~~~, " + msg.toString() + erroInfo);
        }
        if(mQConsumer != null) mQConsumer.doStop();
        LoggerUtils.info(AbstractConsumerHandler.class,"~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }

    public void setMQConsumer(MQConsumer mQConsumer) {
        this.mQConsumer = mQConsumer;
    }

}
