package org.xinyu.com.mq;

import org.xinyu.com.mq.Msg;

import java.io.Serializable;

public interface MsgHandler extends Serializable {
    void processMsg(Msg msg);
}
