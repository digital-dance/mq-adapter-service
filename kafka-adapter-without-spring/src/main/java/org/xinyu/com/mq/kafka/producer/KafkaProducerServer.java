package org.xinyu.com.mq.kafka.producer;


import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.*;
import org.xinyu.com.mq.kafka.constant.KafkaMesConstant;


/**
 * kafkaProducer模板
 *     使用此模板发送消息
 * @author wangb
 *
 */
//@Component
public class KafkaProducerServer implements Serializable {

    Properties props;

    public KafkaProducerServer(Properties props){
        this.props = props;
    }

    public Map<String,Object> pushMsg( String topic, String msg, String key, int partion ) {
//        props = new Properties();
//        props.put("bootstrap.servers", KafkaConstant.KAFKA_SERVER_ADDRESS);
//        props.put("acks", "0");
//        props.put("retries", 0);
//        props.put("batch.size", 16384);
//        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer producer = new KafkaProducer(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, partion, key, msg);
        List<Map<String,Object>> recordMetadatas = new ArrayList<>();
        Future<RecordMetadata> ret = producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception e) {
                Map<String,Object> m = checkProRecord(metadata);
                if (e != null) {
                    e.printStackTrace();
                    m.put("exception", e);
                }else {
                    m.put("exception", null);
                }
                //log.info("pushMsg of msg: {}, metadata: {}", msg, metadata);
                recordMetadatas.add(m);
            }
        });

        Map<String,Object> m = checkProRecord(ret);
        producer.close();
        if(recordMetadatas.size() > 0){
            return recordMetadatas.get(0);
        }
        return m;
    }

    /**
     * 检查发送返回结果record
     * @param res
     * @return
     */
    @SuppressWarnings("rawtypes")
    private Map<String,Object> checkProRecord(Future<RecordMetadata> res){
        Map<String,Object> m = new HashMap<String,Object>();
        if(res!=null){
            try {
                RecordMetadata r = res.get();//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                return checkProRecord(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            } catch (ExecutionException e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        }else{
            m.put("code", KafkaMesConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMesConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }

    private Map<String,Object> checkProRecord(RecordMetadata res){
        Map<String,Object> m = new HashMap<String,Object>();
        if(res!=null){
            try {
                RecordMetadata r = res;//检查result结果集
                /*检查recordMetadata的offset数据，不检查producerRecord*/
                Long offsetIndex = r.offset();
                m.put( "offset", r.offset() );
                m.put( "topic", r.topic() );
                m.put( "partition", r.partition() );

                if(offsetIndex!=null && offsetIndex>=0){
                    m.put("code", KafkaMesConstant.SUCCESS_CODE);
                    m.put("message", KafkaMesConstant.SUCCESS_MES);
                    return m;
                }else{
                    m.put("code", KafkaMesConstant.KAFKA_NO_OFFSET_CODE);
                    m.put("message", KafkaMesConstant.KAFKA_NO_OFFSET_MES);
                    return m;
                }
            } catch (Exception e) {
                e.printStackTrace();
                m.put("code", KafkaMesConstant.KAFKA_SEND_ERROR_CODE);
                m.put("message", KafkaMesConstant.KAFKA_SEND_ERROR_MES);
                return m;
            }
        }else{
            m.put("code", KafkaMesConstant.KAFKA_NO_RESULT_CODE);
            m.put("message", KafkaMesConstant.KAFKA_NO_RESULT_MES);
            return m;
        }
    }
}
