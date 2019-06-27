package org.xinyu.com.zipkin.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import org.springframework.context.ConfigurableApplicationContext;
import zipkin.server.internal.EnableZipkinServer;

/**
 *
 * @Title: ZipkinServerApplication
 * @Description: 程序入口 EnableDiscoveryClient: 启用服务注册与发现 EnableZipkinServer:
 *               启用Zipkin
 * @Version:1.0.0
 * @author pancm
 * @date 2018年3月29日
 */
@EnableDiscoveryClient
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServerApplication {

    public static void main(String[] args) {
        // SpringApplication.run(ConfigServerApplication.class, args);
        ConfigurableApplicationContext context = new SpringApplicationBuilder(ZipkinServerApplication.class)
                .web(WebApplicationType.SERVLET).run(args);
        System.out.println("Zipkin服务端启动成功!");
    }
}