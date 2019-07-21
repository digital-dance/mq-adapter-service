package org.xinyu.com.mq.impl;

import com.digital.dance.framework.infrastructure.commons.Log;

import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.xinyu.com.mq.kafka.AwareRebalanceListener;

public class MQConsumer {
//    protected final Logger LOG = LoggerFactory.getLogger(MQConsumer.class);
    protected final Log LOG = new Log(MQConsumer.class);
    ContainerProperties containerProperties;

    DefaultKafkaConsumerFactory consumerFactory;

    KafkaMessageListenerContainer kafkaMessageListenerContainer;

    org.xinyu.com.mq.kafka.AwareRebalanceListener consumerAwareRebalanceListener;

    Boolean isOnece;
    Boolean isMANUAL;

    public MQConsumer(){}

    public void configMQConsumer(java.util.Map consumerProperties, String topic, AbstractConsumerHandler consumerHandler, Boolean isOnece, Boolean isMANUAL){

        this.isOnece = isOnece;
        this.isMANUAL = isMANUAL;

        this.consumerFactory = new DefaultKafkaConsumerFactory(consumerProperties);

        if( this.containerProperties == null ) {
            this.containerProperties = new ContainerProperties(topic);
        }

        if(isOnece) consumerHandler.setMQConsumer(this);
        this.containerProperties.setMessageListener(consumerHandler);

        if(isMANUAL) {
            this.containerProperties.setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);

            if (this.consumerAwareRebalanceListener != null) {
                this.containerProperties.setConsumerRebalanceListener(consumerAwareRebalanceListener);
            }
        }

        if(this.kafkaMessageListenerContainer == null)
            this.kafkaMessageListenerContainer = new KafkaMessageListenerContainer(consumerFactory, containerProperties);
    }

    public void doStart() {
        this.kafkaMessageListenerContainer.start();
    }

    public void doStop() {
        this.kafkaMessageListenerContainer.stop(new Runnable() {
            @Override
            public void run() {
                LOG.info("doStop()");
            }
        });
    }

    public KafkaMessageListenerContainer getKafkaMessageListenerContainer() {
        return kafkaMessageListenerContainer;
    }

    public void setKafkaMessageListenerContainer(KafkaMessageListenerContainer kafkaMessageListenerContainer) {
        this.kafkaMessageListenerContainer = kafkaMessageListenerContainer;
    }

    public AwareRebalanceListener getConsumerAwareRebalanceListener() {
        return consumerAwareRebalanceListener;
    }

    public void setConsumerAwareRebalanceListener(AwareRebalanceListener consumerAwareRebalanceListener) {
        this.consumerAwareRebalanceListener = consumerAwareRebalanceListener;
    }
}
