package org.xinyu.com.mq.impl.example;

import org.xinyu.com.mq.Msg;

public class ConsumerHandlerImpl extends org.xinyu.com.mq.impl.ConsumerHandlerImpl {
    @Override
    public void processMsg(Msg msg) {
        LOG.info("=============ConsumerHandlerImpl开始处理消息=============");
        LOG.info("Value:" + msg.getValue());
        LOG.info("Key:" + msg.getKey());
        LOG.info("Topic:" + msg.getTopic());
        LOG.info("=============ConsumerHandlerImpl成功处理消息=============");
    }
}
