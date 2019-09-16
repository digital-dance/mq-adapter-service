package org.xinyu.com.eureka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@EnableAutoConfiguration
@EnableEurekaServer // 声明这是一个Eureka Server
@SpringBootApplication
public class EurekaServerApplication {
//    @Bean
//    public LoginController loginController(){
//        LoginController loginController = new LoginController();
//        return loginController;
//    }

    public static void main(String[] args) {
//        SpringApplication.run(EurekaServerApplication.class, args);
        org.xinyu.com.mq.SpringContextUtil.setApplicationClass( EurekaServerApplication.class );
        new SpringApplicationBuilder(EurekaServerApplication.class).web(WebApplicationType.SERVLET).run(args);
//    String redisPorts = redisPort;
    }
}