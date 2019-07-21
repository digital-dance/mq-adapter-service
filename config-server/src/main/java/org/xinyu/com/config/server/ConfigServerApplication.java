package org.xinyu.com.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 *
 * @Title: ConfigServerApplication
 * @Description:
 * 程序入口
 * EnableDiscoveryClient: 启用服务注册与发现
EnableConfigServer:    启用config配置中心
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月29日
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ConfigServerApplication.class, args);
        ConfigurableApplicationContext context  = new SpringApplicationBuilder(ConfigServerApplication.class).web(WebApplicationType.SERVLET).run(args);
        System.out.println("配置中心服务端启动成功!");
    }
}