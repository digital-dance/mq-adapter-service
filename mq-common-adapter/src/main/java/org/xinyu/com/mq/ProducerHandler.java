package org.xinyu.com.mq;

import java.io.Serializable;
import java.util.Map;

public interface ProducerHandler extends Serializable {
    Map<String,Object> sendMsg(Msg msg);
}
