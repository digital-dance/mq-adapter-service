package org.xinyu.com.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;

import org.xinyu.com.mq.kafka.KafkaOffsetService;

import java.util.Collection;

public abstract class AbstractConsumerAwareRebalanceListener {

    protected KafkaOffsetService kafkaOffsetService;

    protected String topic;


    public abstract void onPartitionsAssigned(Consumer<?, ?> consumer, Collection<TopicPartition> partitions);

    public KafkaOffsetService getKafkaOffsetService() {
        return kafkaOffsetService;
    }

    public void setKafkaOffsetService(KafkaOffsetService kafkaOffsetService) {
        this.kafkaOffsetService = kafkaOffsetService;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
