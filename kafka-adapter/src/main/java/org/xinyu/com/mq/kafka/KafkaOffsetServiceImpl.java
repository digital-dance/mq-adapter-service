package org.xinyu.com.mq.kafka;

import javax.sql.DataSource;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class KafkaOffsetServiceImpl implements KafkaOffsetService {
    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    @Override
    @Transactional(readOnly = true)
    public Long offset(String topic, Integer partiton) {
        String sql = "select offset from kafka_offset where topic = ? and partition = ?";
        return jdbcTemplate.queryForObject(sql, Long.class, topic, partiton);
    }

    @Override
    @Transactional
    public void process(ConsumerRecord<String, String> record) {
        // 保存消息
        String insertKafkaMsgSql = "insert into kafka_message values (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertKafkaMsgSql, new Object[] { record.topic(), record.partition(), record.offset(), record.key(), record.value() });

        // 保存 offset
        long maxOffset = record.offset() + 1;
        String updateOffsetSql = "update kafka_offset set offset = ? where topic = ? and partition = ?";
        jdbcTemplate.update(updateOffsetSql, maxOffset, record.topic(), record.partition());
    }

    //
    // Setter
    //
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
