package org.xinyu.com.mq.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import org.apache.kafka.clients.consumer.internals.NoOpConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import org.xinyu.com.mq.kafka.KafkaOffsetService;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

/**
 * kafka监听器启动
 * 自动监听是否有消息需要消费
 * @author wangb
 *
 */
public abstract class KafkaConsumerServer  implements Serializable {

//    protected final Log LOG = new Log(KafkaConsumerServer.class);

    protected KafkaOffsetService kafkaOffsetService;
    /**
     * com.alibaba.rocketmq.client.consumer
     * consumer = new DefaultMQPushConsumer();
     * consumer.registerMessageListener(new MessageListenerOrderly()
     * public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext context)
     * consumer.registerMessageListener(new MessageListenerConcurrently()
     * public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context)
     * 监听器自动执行该方法
     *     消费消息
     *     自动提交offset
     *     执行业务代码
     *     （high level api 不提供offset管理，不能指定offset进行消费）
     */

    public abstract void onMessage(ConsumerRecord<String, String> record) ;


    public abstract void onMessage(ConsumerRecord<String, String> record, Boolean isAcknowledgment) ;

    public KafkaOffsetService getKafkaOffsetService() {
        return kafkaOffsetService;
    }

    public void setKafkaOffsetService(KafkaOffsetService kafkaOffsetService) {
        this.kafkaOffsetService = kafkaOffsetService;
    }

    boolean running = true;
    public void start(java.util.Map props, String topic, Boolean isAcknowledgment) {
        running = true;
//        Properties props = new Properties();

//        props.put("bootstrap.servers", KafkaConstant.KAFKA_SERVER_ADDRESS);
//        props.put("group.id", "1");
//        props.put("enable.auto.commit", "true");
//        props.put("auto.commit.interval.ms", "1000");
//        props.put("session.timeout.ms", "30000");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer consumer = new KafkaConsumer(props);

        ArrayList<String> topics = new ArrayList<String>();
        topics.add(topic);
        consumer.subscribe(topics);

        ArrayList<TopicPartition> list = new ArrayList<TopicPartition>();
        consumer.seekToBeginning(list);

        // ===== 拿到所有的topic ===== //
//        Map<String, List<PartitionInfo>> listTopics = consumer.listTopics();
//        Set<Map.Entry<String, List<PartitionInfo>>> entries = listTopics.entrySet();

        while (running) {
            ConsumerRecords<String, String> records = consumer.poll( java.time.Duration.ofMillis(1000 * 60)  );
//            for(ConsumerRecord<String, String> record : records) {
//                System.out.println("[fetched from partition " + record.partition() + ", offset: " + record.offset() + ", message: " + record.value() + "]");
//            }
            if( records != null && !records.isEmpty() ){
                records.forEach(record -> {
                    if (isAcknowledgment) {
                        onMessage(record, isAcknowledgment);
                    } else {
                        onMessage(record);
                    }

                });

            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(Runnable runnable){
        running = false;
    }
}
