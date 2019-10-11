//package org.xinyu.com.mq.boot.rest.provider;
//
//import jdk.nashorn.internal.objects.annotations.Getter;
//import jdk.nashorn.internal.objects.annotations.Setter;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.stereotype.Component;
//
//@PropertySource(value = {"classpath:system.properties","classpath:jdbc.properties"})
////@Setter
////@Getter
//@Component
//@ConfigurationProperties
//public class KafkaProperties {
//
//    @Value("${bootstrap.servers}")
//    public static String bootstrap_servers;
//
//    @Value("${group.id}")
//    public static String group_id;
//
//    @Value("${retries}")
//    public static String retries;
//
//    @Value("${batch.size}")
//    public static String batch_size;
//
//    @Value("${linger.ms}")
//    public static String linger_ms;
//
//    @Value("${buffer.memory}")
//    public static String buffer_memory;
//
//    @Value("${enable.auto.commit}")
//    public static String enable_auto_commit;
//
//    @Value("${auto.commit.interval.ms}")
//    public static String auto_commit_interval_ms;
//
//    @Value("${session.timeout.ms}")
//    public static String session_timeout_ms;
//}
