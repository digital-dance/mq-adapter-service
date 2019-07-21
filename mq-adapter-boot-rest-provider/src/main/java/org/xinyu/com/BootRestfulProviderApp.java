package org.xinyu.com;


import com.digital.dance.framework.infrastructure.commons.AppPropsConfig;
import com.digital.dance.framework.infrastructure.commons.Log;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.xinyu.com.mq.ProducerHandler;
import org.xinyu.com.mq.boot.rest.provider.KafkaProperties;
import org.xinyu.com.mq.impl.ProducerHandlerImpl;

import java.util.Map;

@Configuration
@ComponentScan

@EnableAutoConfiguration
@EnableFeignClients
@EnableDiscoveryClient
//@SpringBootConfiguration
@SpringBootApplication
@ImportResource( {"classpath*:/ioc_conf/com-mq-boot-context.xml"} )
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 20000, redisFlushMode = RedisFlushMode.IMMEDIATE)//增加redissession缓存支持
@EnableConfigurationProperties(KafkaProperties.class)
@RefreshScope
public class BootRestfulProviderApp {

    protected static final Log LOG = new Log(BootRestfulProviderApp.class);
//    @Bean
//    public Runnable createRunnable() {
//        return () -> System.out.println("spring boot is running");
//    }
//@Bean
//public RememberMeServices rememberMeServices() {
//    RememberMeServices producerHandler = new SpringSessionRememberMeServices();
//    //((ProducerHandlerImpl)producerHandler).setKafkaProducerServer(KafkaProducerServer());
//    return producerHandler;
//}
//    @Bean
//    public DefaultKafkaProducerFactory producerFactory() {
//
//        Map<Object, Object> map = AppPropsConfig.getProperties("system.properties", BootRestfulProviderApp.class);
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

    public static void main( String[] args )
    {
        System.out.println( "BootRestfulProviderApp is starting!" );
        //SpringApplication.run(BootApp.class, args);
        ConfigurableApplicationContext context  = new SpringApplicationBuilder(BootRestfulProviderApp.class).web(WebApplicationType.SERVLET).run(args);
//        ConfigurableApplicationContext context = SpringApplication.run(BootApp.class, args);
        //context.getBean(Runnable.class).run();
//        System.out.println(context.getBean(User.class));
//        Map map = (Map) context.getBean("createMap");   //注意这里直接获取到这个方法bean
//        int age = (int) map.get("age");
//        System.out.println("age=="+age);
    }

}
