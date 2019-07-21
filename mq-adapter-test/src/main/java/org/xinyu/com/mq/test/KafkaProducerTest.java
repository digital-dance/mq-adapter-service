package org.xinyu.com.mq.test;

import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.digital.dance.framework.infrastructure.commons.GsonUtils;
import org.junit.Test;
import org.xinyu.com.mq.LoggerUtils;
import org.xinyu.com.mq.Msg;
import org.xinyu.com.mq.ProducerHandler;
import org.xinyu.com.mq.impl.AbstractConsumerHandler;
import org.xinyu.com.mq.impl.MQConsumer;

import com.alibaba.fastjson.JSON;
import org.xinyu.com.mq.impl.ProducerHandlerImpl;
import org.xinyu.com.mq.kafka.producer.KafkaProducerServer;

public class KafkaProducerTest extends UnitTestBase {

	private static ProducerHandler producerHandler;

	@Test
    public void producer_main() {
//        context = getInstance("classpath*:/com-mq-test-context.xml");
        Properties properties = new Properties();

        properties.setProperty("bootstrap.servers", "192.168.0.112:9092");
        //        properties.setProperty("zookeeper.connect","localhost:2181")
        properties.setProperty("group.id", "1");
        properties.setProperty("retries", "1");
        properties.setProperty("batch.size", "16384");
        properties.setProperty("linger.ms", "1");
        properties.setProperty("buffer.memory", "33554432");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("session.timeout.ms", "15000");
        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducerServer kafkaProducerServer  = new KafkaProducerServer(properties);
        ProducerHandler producerHandler  = new ProducerHandlerImpl();
        ((ProducerHandlerImpl)producerHandler).setKafkaProducerServer(kafkaProducerServer);

//        MQConsumer mQConsumer = new MQConsumer();
		/*
		KafkaProducerServer kafkaProducer = new KafkaProducerServer();
		String topic = "orderTopic";
		String value = "test";
		String ifPartition = "0";
		Integer partitionNum = 3;
		String role = "test";//用来生成key
		Map<String,Object> res = kafkaProducer.sndMesForTemplate
				(topic, value, ifPartition, partitionNum, role);
		*/
//        producerHandler = (ProducerHandler) context.getBean("producerHandler");

        String vs = ( "1.0,2.0,3.0,4.0" );
        //v=vs;
        for(int i = 14; i <= 16; i++) {
            Map<String, Object> map = new ConcurrentHashMap<String, Object>();
            map.put("id", i + 1.0 );
            map.put("name",2.0 );
            map.put("score",3.0 );
            map.put("comments",new Date().getTime() + 0.1);

            String v = GsonUtils.toJsonStr( map );

            map = new ConcurrentHashMap<String, Object>();
            map.put("id", i + 1.0 );
            map.put("jsonObject",v);
            map.put("isPut", true );

            Msg msg = new Msg();
            msg.setPartition(0);
            msg.setValue(GsonUtils.toJsonStr( map ));
            msg.setRole("test");
            msg.setIfPartition(false);
            msg.setTopic("cstest1");

            Map<String, Object> res = producerHandler.sendMsg(msg);
            System.out.println("测试结果如下：===============");
            String message = (String) res.get("message");
            String code = (String) res.get("code");

            System.out.println("code:" + code);
            System.out.println("message:" + message);
            System.out.println("producerHandler.sendMsg(msg) return:" + GsonUtils.toJsonStr( res ));
        }
/*
        java.util.HashMap consumerProperties1 = (java.util.HashMap)context.getBean("consumerProperties1");
        DefaultKafkaConsumerFactory consumerFactory1 = (DefaultKafkaConsumerFactory)context.getBean("consumerFactory1");
        ContainerProperties containerProperties_example1 = (ContainerProperties)context.getBean("containerProperties_example1");
        containerProperties_example1.setMessageListener(new AbstractConsumerHandler(){

            @Override
            public void processMsg(Msg msg) {
                LOG.info("=============AbstractConsumerHandler开始处理消息=============");
                LOG.info("Value:" + msg.getValue());
                LOG.info("Key:" + msg.getKey());
                LOG.info("Topic:" + msg.getTopic());

                LOG.info("=============AbstractConsumerHandler成功处理消息=============");
                //mQConsumer.doStop();
            }
        });
        KafkaMessageListenerContainer messageListenerContainer_example1 = (KafkaMessageListenerContainer)context.getBean("messageListenerContainer_example1");
*/

    }

    @Test
    public void main() {
        //public static void main(String... args) {
        context = getInstance("classpath*:/ioc_conf/com-mq-test-context.xml");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void main1() {
        //public static void main(String... args) {
//        context = getInstance("classpath*:/ioc_conf/com-mq-test-context.xml");
//        Properties properties = new Properties();
//
//        properties.setProperty("bootstrap.servers", "localhost:9092");
//        //        properties.setProperty("zookeeper.connect","localhost:2181")
//        properties.setProperty("group.id", "1");
//        properties.setProperty("retries", "1");
//        properties.setProperty("batch.size", "16384");
//        properties.setProperty("linger.ms", "1");
//        properties.setProperty("buffer.memory", "33554432");
//        properties.setProperty("enable.auto.commit", "true");
//        properties.setProperty("auto.commit.interval.ms", "1000");
//        properties.setProperty("session.timeout.ms", "15000");
//        properties.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        properties.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

//        context = getInstance("classpath*:/com-mq-test-context.xml");

        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", "192.168.0.112:9092,127.0.0.1:9092");
//        consumerProperties.put("advertised.host.name","192.168.0.112");
//        consumerProperties.put("metadata.broker.list","192.168.0.112:9092,127.0.0.1:9092");
        consumerProperties.put("group.id", "1");
        consumerProperties.put("retries", "1");
        consumerProperties.put("batch.size", "16384");
        consumerProperties.put("linger.ms", "1");
        consumerProperties.put("buffer.memory", "33554432");
        consumerProperties.put("enable.auto.commit", "true");
        consumerProperties.put("auto.commit.interval.ms", "1000");
        consumerProperties.put("session.timeout.ms", "15000");
        consumerProperties.put("auto.offset.reset", "latest");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        consumerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        consumerProperties.put("partition.assignment.strategy","org.apache.kafka.clients.consumer.RangeAssignor");

        final MQConsumer mQConsumer = new MQConsumer();
        mQConsumer.configMQConsumer(consumerProperties, "httptopic", new AbstractConsumerHandler(){

            @Override
            public void processMsg(Msg msg) {
                LoggerUtils.info(MQConsumer.class,"=============AbstractConsumerHandler开始处理消息=============");
                LoggerUtils.info(MQConsumer.class,"Value:" + msg.getValue());
                LoggerUtils.info(MQConsumer.class,"Key:" + msg.getKey());
                LoggerUtils.info(MQConsumer.class,"Topic:" + msg.getTopic());
                LoggerUtils.info(MQConsumer.class,"offset:" + msg.getOffset());

                LoggerUtils.info(MQConsumer.class,"=============AbstractConsumerHandler成功处理消息=============");
//                mQConsumer.doStop();
//                this.setMQConsumer(mQConsumer);
            }
        }, false, false);
        mQConsumer.doStart();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void main2() {
        //public static void main(String... args) {
//        context = getInstance("classpath*:/ioc_conf/com-mq-test-context.xml");
//        context = getInstance("classpath*:/com-mq-test-context.xml");

        Properties consumerProperties = new Properties();
        consumerProperties.put("bootstrap.servers", "192.168.0.112:9092");
        consumerProperties.put("group.id", "11");
        consumerProperties.put("retries", "1");
        consumerProperties.put("batch.size", "16384");
        consumerProperties.put("linger.ms", "1");
        consumerProperties.put("buffer.memory", "33554432");
        consumerProperties.put("enable.auto.commit", "true");
        consumerProperties.put("auto.commit.interval.ms", "1000");
        consumerProperties.put("session.timeout.ms", "15000");
        consumerProperties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumerProperties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        consumerProperties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        consumerProperties.put("partition.assignment.strategy","org.apache.kafka.clients.consumer.RangeAssignor");

        final MQConsumer mQConsumer = new MQConsumer();
        mQConsumer.configMQConsumer(consumerProperties, "httptopic", new AbstractConsumerHandler(){

            @Override
            public void processMsg(Msg msg) {
                LoggerUtils.info(MQConsumer.class,"=============AbstractConsumerHandler开始处理消息=============");
                LoggerUtils.info(MQConsumer.class,"Value:" + msg.getValue());
                LoggerUtils.info(MQConsumer.class,"Key:" + msg.getKey());
                LoggerUtils.info(MQConsumer.class,"Topic:" + msg.getTopic());
                LoggerUtils.info(MQConsumer.class,"offset:" + msg.getOffset());

                LoggerUtils.info(MQConsumer.class,"=============AbstractConsumerHandler成功处理消息=============");
//                mQConsumer.doStop();
//                this.setMQConsumer(mQConsumer);
            }
        }, false, false);
        mQConsumer.doStart();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
