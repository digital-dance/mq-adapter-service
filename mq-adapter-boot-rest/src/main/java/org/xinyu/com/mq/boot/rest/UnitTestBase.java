package org.xinyu.com.mq.boot.rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public abstract class UnitTestBase {
	private final static Log log = new Log(UnitTestBase.class);
	
	protected volatile static ApplicationContext context = null;
	protected volatile static Object lockObj = new Object();
	public static ApplicationContext getInstance(String springcfgPath) {
        if (context == null) {
            synchronized (UnitTestBase.lockObj) {
                if (context == null) {
                	log.info(
					"-----------begin to load " + springcfgPath + " -----------");
                    String[] configLocations = new String[1];
                    	configLocations[0] = springcfgPath;
                    context = new ClassPathXmlApplicationContext(configLocations);
                    //((ClassPathXmlApplicationContext)context).start();
                    log.info(
        					"-----------end to load " + springcfgPath + " -----------");
                }
            }
        }
        return context;
    }
}

