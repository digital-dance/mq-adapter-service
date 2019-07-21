package org.xinyu.com.mq.kafka;

import javax.sql.DataSource;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface KafkaOffsetService {
    Long offset(String topic, Integer partiton);

    void process(ConsumerRecord<String, String> record);

    void setDataSource(DataSource dataSource);
}
