package org.xinyu.com.mq.impl;

import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.xinyu.com.mq.Msg;
import org.xinyu.com.mq.MsgHandler;
import org.xinyu.com.mq.kafka.consumer.KafkaConsumerServer;

public abstract class AbstractConsumerHandler extends KafkaConsumerServer implements MsgHandler
        , MessageListener<String, String>
        , AcknowledgingMessageListener<String, String> {

    MQConsumer mQConsumer;

    @Override
    public abstract void processMsg(Msg msg);

    @Override
    public void onMessage(ConsumerRecord<String, String> record) {
        LOG.info("=============kafkaConsumer开始消费=============");
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
            LOG.info("~~~~~~~~~~~~~kafkaConsumer消费失败并结束~~~~~~~~~~~~~, " + msg.toString() + erroInfo);
        }
        if(mQConsumer != null) mQConsumer.doStop();
        LOG.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }

    @Override
    public void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        LOG.info("=============kafkaConsumer开始消费=============");

        Msg msg = new Msg();
        msg.setTopic(record.topic());
        msg.setKey(record.key());
        msg.setValue(record.value());
        msg.setOffset(record.offset());
        msg.setPartition(record.partition());
        msg.setTimestamp(record.timestamp());
        try {
            LOG.info("=============Msg============="+ GsonUtils.toJsonStr(msg));
            this.processMsg(msg);
            if( kafkaOffsetService != null ){
                kafkaOffsetService.process(record);
            }
            if( acknowledgment != null )
                acknowledgment.acknowledge();

        } catch (Exception ex){
            String erroInfo = GsonUtils.toJsonStr(ex);
            LOG.info("~~~~~~~~~~~~~kafkaConsumer消费失败并结束~~~~~~~~~~~~~, " + msg.toString() + erroInfo);
        }
        if(mQConsumer != null) mQConsumer.doStop();
        LOG.info("~~~~~~~~~~~~~kafkaConsumer消费结束~~~~~~~~~~~~~");
    }

    public void setMQConsumer(MQConsumer mQConsumer) {
        this.mQConsumer = mQConsumer;
    }

}
