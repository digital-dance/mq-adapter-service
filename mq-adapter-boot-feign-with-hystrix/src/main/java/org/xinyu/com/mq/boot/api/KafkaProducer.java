package org.xinyu.com.mq.boot.api;

import com.digital.dance.framework.infrastructure.commons.ResponseVo;

import feign.RequestLine;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import org.xinyu.com.mq.boot.api.hystrix.KafkaProducerFallbackFactory;

import java.net.URI;


//@FeignClient(name = "kafka-producer-rest", fallback = KafkaProducerHystrix.class)
@FeignClient(name = "kafka-producer-rest-provider", fallbackFactory = KafkaProducerFallbackFactory.class, configuration = FeignClientsConfigurationCustom.class)
@RequestMapping("/kafka")
public interface KafkaProducer {
//topic=orderTopic&value=test&partition=3&role=tester&ifPartition=true
    @RequestMapping("/producer")
    @ResponseBody
    public ResponseVo sendMsg(@RequestParam("topic") String topic
            , @RequestParam("value") String value
            , @RequestParam("partition") int partition
            , @RequestParam("role") String role
            , @RequestParam("ifPartition") boolean ifPartition)
            throws Exception;

    @RequestMapping("/producer/json")
    @ResponseBody
    public ResponseVo sendJsonMsg( @RequestParam("pJsonMsg") String pJsonMsg)//@RequestParam("pJsonMsg")
            throws Exception;

    @RequestLine("GET /producer/json")
    @ResponseBody
    public ResponseVo sendJsonMsg(URI uri, @RequestParam("pJsonMsg") String pJsonMsg)//@RequestParam("pJsonMsg")
            throws Exception;
}
