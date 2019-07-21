package org.xinyu.com;

import com.digital.dance.framework.infrastructure.commons.Log;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


import java.util.Map;

//@Configuration
//@ComponentScan(basePackages = {"org.xinyu.com.mq.boot","org.xinyu.com.mq.boot.api","org.xinyu.com.mq.boot.rest"})

@EnableAutoConfiguration
@EnableFeignClients
@EnableDiscoveryClient
//@SpringBootConfiguration
@SpringBootApplication
@ImportResource( {"classpath*:/ioc_conf/com-mq-boot-context.xml"} )
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)//增加redissession缓存支持
//@SpringCloudApplication
@RefreshScope
public class BootRestfulApp {
   static Log log = new Log(BootRestfulApp.class);

//    @Bean
//    public org.springframework.kafka.core.DefaultKafkaProducerFactory producerFactory() {
//        Map<Object, Object> map = AppPropsConfig.getProperties("system.properties", BootRestfulApp.class);
//
//        DefaultKafkaProducerFactory producerFactory = new DefaultKafkaProducerFactory(map);
//
//        return producerFactory;
//    }
//
//    @Bean
//    public org.xinyu.com.mq.kafka.producer.KafkaProducerListener producerListener() {
//        org.xinyu.com.mq.kafka.producer.KafkaProducerListener listener = new org.xinyu.com.mq.kafka.producer.KafkaProducerListener();
//        return listener;
//    }
//
//    @Bean
//    public org.springframework.kafka.core.KafkaTemplate kafkaTemplate() {
//        org.springframework.kafka.core.KafkaTemplate
//                kafkaTemplate = new org.springframework.kafka.core
//                .KafkaTemplate(producerFactory(), true);
//        kafkaTemplate.setDefaultTopic("defaultTopic");
//        kafkaTemplate.setProducerListener(producerListener());
//        return kafkaTemplate;
//    }
//
//    @Bean
//    public org.xinyu.com.mq.kafka.producer.KafkaProducerServer KafkaProducerServer() {
//        org.xinyu.com.mq.kafka.producer.KafkaProducerServer
//                KafkaProducerServer = new org.xinyu.com.mq.kafka.producer
//                .KafkaProducerServer();
//        KafkaProducerServer.setKafkaTemplate(kafkaTemplate());
//
//        return KafkaProducerServer;
//    }
//
//    @Bean
//    public ProducerHandler producerHandler() {
//        ProducerHandler producerHandler = new ProducerHandlerImpl();
//        ((ProducerHandlerImpl)producerHandler).setKafkaProducerServer(KafkaProducerServer());
//        return producerHandler;
//    }
    @Bean
    public Runnable createRunnable() {
        return () -> System.out.println("spring boot is running");
    }

    public static void main( String[] args )
    {
        System.out.println( "BootRestfulApp is starting!" );
        //SpringApplication.run(BootRestfulApp.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(BootRestfulApp.class, args);
        ConfigurableApplicationContext context  = new SpringApplicationBuilder(BootRestfulApp.class).web(WebApplicationType.SERVLET).run(args);
        context.getBean(Runnable.class).run();
//        System.out.println(context.getBean(User.class));
//        KafkaProducer kafkaProducer = null;
//        try {
//            kafkaProducer = (KafkaProducer) context.getBean(KafkaProducer.class);   //注意这里直接获取到这个方法bean
//            Msg msg = new Msg();
//            msg.setPartition(2);
//            msg.setTopic("feign-topic");
//            msg.setValue("feign-msg");
//            msg.setIfPartition(false);
//            ResponseVo responseVo = kafkaProducer.sendMsg("feign-topic", "feign-msg", 2, "feign", false);
//            log.info("org.xinyu.com.mq.boot.api.KafkaProducer.sendMsg - "+ GsonUtils.toJsonStr(responseVo) );
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("org.xinyu.com.mq.boot.api.KafkaProducer.sendMsg - ", e);
//        }
//
//        try {
//            Msg msg = new Msg();
//            msg.setPartition(1);
//            msg.setTopic("feign-topic");
//            msg.setValue("feign-msg");
//            msg.setIfPartition(false);
//            ResponseVo responseVo = kafkaProducer.sendJsonMsg(GsonUtils.toJsonStr(msg));
//            log.info("org.xinyu.com.mq.boot.api.KafkaProducer.sendJsonMsg - "+ GsonUtils.toJsonStr(responseVo) );
//        } catch (Exception e) {
//            e.printStackTrace();
//            log.error("org.xinyu.com.mq.boot.api.KafkaProducer.sendJsonMsg - ", e);
//        }
//        System.out.println("age=="+age);
    }

}
