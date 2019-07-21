package org.xinyu.com.mq.impl;

import org.xinyu.com.mq.Msg;

import org.xinyu.com.mq.ProducerHandler;
import org.xinyu.com.mq.kafka.producer.KafkaProducerServer;

import java.io.Serializable;
import java.util.Map;

public class ProducerHandlerImpl implements ProducerHandler, Serializable {

    private KafkaProducerServer kafkaProducerServer;
    @Override
    public Map<String,Object> sendMsg(Msg msg) {
        Boolean bl = msg.getIfPartition() == null ? false : msg.getIfPartition();
        return kafkaProducerServer.pushMsg(msg.getTopic(), msg.getValue()
                , msg.getKey(), msg.getPartition());
    }

    public KafkaProducerServer getKafkaProducerServer() {
        return kafkaProducerServer;
    }

    public void setKafkaProducerServer(KafkaProducerServer kafkaProducerServer) {
        this.kafkaProducerServer = kafkaProducerServer;
    }
}
