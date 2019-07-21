package org.xinyu.com.mq.kafka.consumer;

import com.digital.dance.framework.infrastructure.commons.Log;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.xinyu.com.mq.kafka.KafkaOffsetService;

/**
 * kafka监听器启动
 * 自动监听是否有消息需要消费
 * @author wangb
 *
 */
public abstract class KafkaConsumerServer implements MessageListener<String, String>
        , AcknowledgingMessageListener<String, String> {

    protected final Log LOG = new Log(KafkaConsumerServer.class);

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
    @Override
    public abstract void onMessage(ConsumerRecord<String, String> record) ;

    @Override
    public abstract void onMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) ;

    public KafkaOffsetService getKafkaOffsetService() {
        return kafkaOffsetService;
    }

    public void setKafkaOffsetService(KafkaOffsetService kafkaOffsetService) {
        this.kafkaOffsetService = kafkaOffsetService;
    }
}
