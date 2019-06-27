package org.xinyu.com.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 声明这是一个Eureka Server
@SpringBootApplication
public class EurekaServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(EurekaServerApplication.class, args);
        new SpringApplicationBuilder(EurekaServerApplication.class).web(WebApplicationType.SERVLET).run(args);
    }
}