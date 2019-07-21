package org.xinyu.com.mq.boot.api.hystrix;

import com.digital.dance.framework.infrastructure.commons.Constants;
import com.digital.dance.framework.infrastructure.commons.ResponseVo;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import org.xinyu.com.mq.boot.api.KafkaProducer;

@Component
@RefreshScope
public class KafkaProducerFallbackFactory implements FallbackFactory<KafkaProducer> {


    @Override
    public KafkaProducer create(Throwable throwable) {
        return new KafkaProducer() {
            @Override
            public ResponseVo sendMsg(@RequestParam("topic") String topic
                    , @RequestParam("value") String value
                    , @RequestParam("partition") int partition
                    , @RequestParam("role") String role
                    , @RequestParam("ifPartition") boolean ifPartition) throws Exception {
                ResponseVo ret = new ResponseVo();
                ret.setMsg(Constants.FAILED_MSG);
                ret.setCode(Constants.RETURN_CODE_FAILED);
                ret.setResult("The result is from org.xinyu.com.mq.boot.api.hystrix.KafkaProducerFallbackFactory, KafkaProducer.sendMsg.");
                return ret;
            }

            @Override
            public ResponseVo sendJsonMsg(String pJsonMsg) throws Exception {
                ResponseVo ret = new ResponseVo();
                ret.setMsg(Constants.FAILED_MSG);
                ret.setCode(Constants.RETURN_CODE_FAILED);
                ret.setResult("The result is from org.xinyu.com.mq.boot.api.hystrix.KafkaProducerFallbackFactory, KafkaProducer.sendJsonMsg.");
                return ret;
            }
        };
    }
}
