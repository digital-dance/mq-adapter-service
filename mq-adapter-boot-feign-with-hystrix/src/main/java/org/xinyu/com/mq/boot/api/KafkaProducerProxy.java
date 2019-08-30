package org.xinyu.com.mq.boot.api;

import com.digital.dance.framework.infrastructure.commons.ResponseVo;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.xinyu.com.mq.boot.api.hystrix.KafkaProducerFallbackFactory;
import org.xinyu.com.mq.boot.api.hystrix.KafkaProducerProxyFallbackFactory;

import java.net.URI;


//@FeignClient(name = "kafka-producer-rest", fallback = KafkaProducerHystrix.class)
@FeignClient(name = "kafka-producer-rest-provider", fallbackFactory = KafkaProducerProxyFallbackFactory.class, configuration = FeignClientsConfigurationCustom.class)
@RequestMapping("/kafka")
public interface KafkaProducerProxy {
//topic=orderTopic&value=test&partition=3&role=tester&ifPartition=true

    @RequestLine("GET /producer/json")
    @ResponseBody
    public ResponseVo sendJsonMsg(URI uri, @RequestParam("pJsonMsg") String pJsonMsg)//@RequestParam("pJsonMsg")
            throws Exception;

    @RequestLine("GET /producer")
    @ResponseBody
    public ResponseVo sendMsg(URI uri, @RequestParam("topic") String topic
            , @RequestParam("value") String value
            , @RequestParam("partition") int partition
            , @RequestParam("role") String role
            , @RequestParam("ifPartition") boolean ifPartition)
            throws Exception;
}
