package org.xinyu.com.mq.test;

import com.digital.dance.framework.codis.impl.CodisImpl;
import com.digital.dance.framework.codis.impl.GsonUtils;
import com.digital.dance.framework.infrastructure.commons.AppPropsConfig;

import java.util.Map;

public class KafkaProducerMain {
    static Log log = new Log(KafkaProducerMain.class);
	public static void main(String[] args) {
//        context = getInstance("classpath*:/com-mq-test-context.xml");
//        KafkaProducerTest KafkaProducerTest = new KafkaProducerTest();
//        KafkaProducerTest.producer_main();
        String s = GsonUtils.toJsonStr("en-" + "cstest1_0");
        System.out.println(s);
        Map map = AppPropsConfig.getProperties("/app/logs/system.properties");
        log.info(map);
        System.out.println(com.digital.dance.framework.infrastructure.commons.GsonUtils.toJsonStr(map));
	}

}
