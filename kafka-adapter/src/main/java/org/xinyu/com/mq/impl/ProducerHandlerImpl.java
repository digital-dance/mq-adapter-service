package org.xinyu.com.mq.impl;

import org.xinyu.com.mq.Msg;

import org.xinyu.com.mq.ProducerHandler;
import org.xinyu.com.mq.kafka.producer.KafkaProducerServer;

import java.util.Map;

public class ProducerHandlerImpl implements ProducerHandler {

    private KafkaProducerServer kafkaProducerServer;
    @Override
    public Map<String,Object> sendMsg(Msg msg) {
        Boolean bl = msg.getIfPartition() == null ? false : msg.getIfPartition();
        return kafkaProducerServer.sndMesForTemplate(msg.getTopic(), msg.getValue()
                , bl, msg.getPartition(), msg.getRole());
    }

    public KafkaProducerServer getKafkaProducerServer() {
        return kafkaProducerServer;
    }

    public void setKafkaProducerServer(KafkaProducerServer kafkaProducerServer) {
        this.kafkaProducerServer = kafkaProducerServer;
    }
}
