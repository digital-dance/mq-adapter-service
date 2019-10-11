package org.xinyu.com.mq.boot.api;

//import feign.RequestInterceptor;
import feign.Contract;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.xinyu.com.mq.boot.FeignHystrixConcurrencyStrategy;
import org.xinyu.com.mq.boot.Log;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
//@EnableFeignClients(basePackages = "com.xxx.xxx.client")
@EnableFeignClients(basePackages = "org.xinyu.com")
@Component
public class FeignClientsConfigurationCustom implements RequestInterceptor {
    Log log = new Log(FeignClientsConfigurationCustom.class);

    @Override
    public void apply(RequestTemplate template) {

        log.info(   RequestTemplate.class.getTypeName() + ": " + template.method());
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        if (requestAttributes == null) {
//            log.info("requestAttributes is null.");
//            return;
//        }
////        template.header("Set-Cookie", "JSESSIONID="+requestAttributes.getSessionId()+";path=/;HttpOnly");
////        template.header("Cookie", "JSESSIONID="+requestAttributes.getSessionId());
//        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
//        //request.getCookies().
//        Enumeration<String> headerNames = request.getHeaderNames();
//        if (headerNames != null) {
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                log.info( "header:: " + name + " : " + request.getHeader(name) );
//                Enumeration<String> values = request.getHeaders(name);
//                while (values.hasMoreElements()) {
//                    String value = values.nextElement();
//                    template.header(name, value);
//                    log.info( "header:: " + name + " : " + value);
//                }
//            }
//        }

        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attrs != null) {
            HttpServletRequest request = attrs.getRequest();
            // 如果在Cookie内通过如下方式取
            Cookie[] cookies = request.getCookies();
            if (cookies != null && cookies.length > 0) {
                for (Cookie cookie : cookies) {
                    template.header(cookie.getName(), cookie.getValue());
                    log.info("request.getCookies():: " + cookie.getName() + " : " + cookie.getValue());
                }
            } else {
                log.warn("FeignClientsConfigurationCustom, 获取Cookie失败！");
            }
            // 如果放在header内通过如下方式取
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();

                    Enumeration<String> values = request.getHeaders(name);
                    if(values != null && values.hasMoreElements()) {
                        while (values.hasMoreElements()) {
                            String value1 = values.nextElement();
                            if("Cookie".equalsIgnoreCase(name)){
                                template.header("Cookie", value1);
                            }else {
                                template.header(name, value1);
                            }
                            log.info("request.getHeaders:: " + name + " : " + value1);
                        }
                    }

//                    String value = request.getHeader(name);
//                    /**
//                     * 遍历请求头里面的属性字段，将jsessionid添加到新的请求头中转发到下游服务
//                     * */
//                    template.header(name, value);
//                    log.info("request.getHeader:: " + name + " : " + value);
////                        if ("jsessionid".equalsIgnoreCase(name)) {
////                            log.debug("添加自定义请求头key:" + name + ",value:" + value);
//////                            template.header(name, value);
////                        } else {
////                            log.debug("FeignHeadConfiguration, 非jsessionid请求头key:" + name + ",value:" + value + "不需要添加!");
////                        }


                }
            } else {
                log.warn("FeignClientsConfigurationCustom, 获取请求头失败！");
            }

        }
    }

    @Bean
    public FeignHystrixConcurrencyStrategy feignHystrixConcurrencyStrategy() {
        return new FeignHystrixConcurrencyStrategy();
    }

//    @Bean
//    public Contract feignContract() {
//        //使用feign自带契约
//        return new feign.Contract.Default();
//    }
}
