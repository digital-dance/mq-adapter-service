package org.xinyu.com.mq.impl;

import org.xinyu.com.mq.LoggerUtils;
import org.xinyu.com.mq.Msg;

import java.io.Serializable;

public class MQConsumer extends ConsumerHandlerImpl implements Serializable {
//    protected final Logger LOG = LoggerFactory.getLogger(MQConsumer.class);
//    protected final Log LOG = new Log(MQConsumer.class);

    java.util.Map consumerProperties;
    String topic;
    AbstractConsumerHandler consumerHandler;
    Boolean isOnece;
    Boolean isMANUAL;

    public MQConsumer(){}

    public void configMQConsumer(java.util.Map consumerProperties, String topic, AbstractConsumerHandler consumerHandler, Boolean isOnece, Boolean isMANUAL) {

        this.isOnece = isOnece;
        this.isMANUAL = isMANUAL;


        this.consumerProperties = consumerProperties;


        this.topic = topic;
        this.consumerHandler = consumerHandler;
    }

    public void doStart() {
        this.start(consumerProperties, this.topic, isMANUAL);
    }

    public void doStop() {
        this.stop(new Runnable() {
            @Override
            public void run() {
                LoggerUtils.info(MQConsumer.class,"doStop()");
            }
        });
    }

    @Override
    public void processMsg(Msg msg) {
        this.consumerHandler.processMsg(msg);
    }
}
