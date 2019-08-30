package org.xinyu.com.mq.boot.api.hystrix;

import com.digital.dance.framework.infrastructure.commons.Constants;
import com.digital.dance.framework.infrastructure.commons.ResponseVo;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import org.xinyu.com.mq.boot.api.KafkaProducerProxy;

import java.net.URI;

@Component
@RefreshScope
public class KafkaProducerProxyFallbackFactory implements FallbackFactory<KafkaProducerProxy> {


    @Override
    public KafkaProducerProxy create(Throwable throwable) {
        return new KafkaProducerProxy() {
            @Override
            public ResponseVo sendMsg(URI uri, @RequestParam("topic") String topic
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
            public ResponseVo sendJsonMsg(URI uri, String pJsonMsg) throws Exception {
                ResponseVo ret = new ResponseVo();
                ret.setMsg(Constants.FAILED_MSG);
                ret.setCode(Constants.RETURN_CODE_FAILED);
                ret.setResult("The result is from org.xinyu.com.mq.boot.api.hystrix.KafkaProducerFallbackFactory, KafkaProducer.sendJsonMsg.");
                return ret;
            }
        };
    }
}
