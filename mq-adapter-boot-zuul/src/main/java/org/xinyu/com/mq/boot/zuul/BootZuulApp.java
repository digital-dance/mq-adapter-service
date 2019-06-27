package org.xinyu.com.mq.boot.zuul;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//@Configuration
//@ComponentScan

//@EnableAutoConfiguration

@EnableHystrixDashboard
@EnableDiscoveryClient
//@EnableAutoConfiguration
@EnableZuulProxy
//@SpringBootConfiguration
@SpringBootApplication
@RefreshScope
@EnableRedisHttpSession(redisFlushMode = RedisFlushMode.IMMEDIATE)//增加redissession缓存支持
@ImportResource( locations={"classpath*:**/spring_permission.xml"} )
public class BootZuulApp {

    public static void main( String[] args )
    {
        System.out.println( "BootZuulApp is starting!" );
        //SpringApplication.run(BootApp.class, args);
//        ConfigurableApplicationContext context = SpringApplication.run(BootZuulApp.class, args);
        ConfigurableApplicationContext context  = new SpringApplicationBuilder(BootZuulApp.class).web(WebApplicationType.SERVLET).run(args);
//        System.out.println(context.getBean(User.class));
//        Map map = (Map) context.getBean("createMap");   //注意这里直接获取到这个方法bean
//        int age = (int) map.get("age");
//        System.out.println("age=="+age);
    }

}
