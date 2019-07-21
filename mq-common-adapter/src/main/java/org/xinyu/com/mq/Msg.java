package org.xinyu.com.mq;

import org.xinyu.com.mq.GsonUtils;

import java.io.Serializable;

public class Msg implements Serializable {
    private String topic;

    private String key;
    private String value;
    private long offset;
    private int partition;

    private long timestamp;

    private Boolean ifPartition;

    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIfPartition() {
        return ifPartition;
    }

    public void setIfPartition(Boolean ifPartition) {
        this.ifPartition = ifPartition;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public int getPartition() {
        return partition;
    }

    public void setPartition(int partition) {
        this.partition = partition;
    }

    public String toString(){
        return GsonUtils.toJsonStr(this);
    }
}
