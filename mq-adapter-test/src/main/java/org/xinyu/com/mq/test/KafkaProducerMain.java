package org.xinyu.com.mq.test;

import com.digital.dance.framework.codis.impl.CodisImpl;
import com.digital.dance.framework.codis.impl.GsonUtils;

public class KafkaProducerMain {

	public static void main(String[] args) {
//        context = getInstance("classpath*:/com-mq-test-context.xml");
//        KafkaProducerTest KafkaProducerTest = new KafkaProducerTest();
//        KafkaProducerTest.producer_main();
        String s = GsonUtils.toJsonStr("en-" + "cstest1_0");
        System.out.println(s);
	}

}
