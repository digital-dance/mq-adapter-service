package org.xinyu.com.mq;


import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource(name="systemConfig"
//        , value = {"classpath:/systemConfig-${spring.profiles.active}.properties","classpath:/jdbc-${spring.profiles.active}.properties"}
//        , factory = SystemPropertySourceFactory.class
//        , ignoreResourceNotFound=true, encoding="UTF-8")
@PropertySource(name="systemConfig"
        , value = {"classpath:/systemConfig.properties","classpath:/jdbc.properties","classpath:/redis.properties"}
        , factory = org.xinyu.com.mq.SystemPropertySourceFactory.class
        , ignoreResourceNotFound=true, encoding="UTF-8")
public class SystemProperties {
}
