package org.xinyu.com.mq.kafka;

import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import com.digital.dance.framework.infrastructure.commons.Log;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.xinyu.com.mq.kafka.consumer.AbstractConsumerAwareRebalanceListener;

import java.util.Collection;

public class AwareRebalanceListener extends AbstractConsumerAwareRebalanceListener {
    Log log = new Log(AwareRebalanceListener.class);
    @Override
    public void onPartitionsAssigned(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
//        for (TopicPartition tp : partitions) {
//            // offset 从 db 中查询
//            long offset = getKafkaOffsetService().offset(getTopic(), tp.partition());
//            consumer.seek(tp, offset);
//        }

        try {
            for (TopicPartition partition : partitions) {
                KafkaOffsetService kafkaOffsetService = getKafkaOffsetService();
                if (kafkaOffsetService != null) {
                  long offset =  kafkaOffsetService.offset( getTopic(), partition.partition() );
                  if (consumer != null && offset >= 0) {
                      //将消费者要消费的消息的位置移动到指定的分区和指定的偏移量所标示的位置
                      consumer.seek(partition, offset);
                  }

                }

            }

            //seekCompleted = true;

        } catch (Exception e) {
            String eO = GsonUtils.toJson(e);
            log.error(eO);
            //logger.error("Error when load offset from custom source",e);

        }

    }
}
