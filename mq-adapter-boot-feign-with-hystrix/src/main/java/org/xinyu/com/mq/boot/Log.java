package org.xinyu.com.mq.boot;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

public class Log extends com.digital.dance.framework.infrastructure.commons.Log {

	//Logger log = null;

	@SuppressWarnings("rawtypes")
	public Log(Class clazz) {
		super(clazz);
		//log = LoggerFactory.getLogger(clazz);
	}
	
	public Log(String name) {
		super(name);
		//log = LoggerFactory.getLogger(name);
	}
//
//	/**
//	 * debug
//	 * @param arg0
//	 */
//	public void debug(Object arg0) {
//		log.debug(arg0.toString());
//	}
//
//	/**
//	 * debug
//	 * @param arg0
//	 * @param arg1
//	 */
//	public void debug(Object arg0, Throwable arg1) {
//		log.debug(arg0.toString(), arg1);
//	}
//
//	/**
//	 * info
//	 * @param arg0
//	 */
//	public void info(Object arg0) {
//		log.info(arg0.toString());
//	}
//
//	/**
//	 * info
//	 * @param arg0
//	 * @param arg1
//	 */
//	public void info(Object arg0, Throwable arg1) {
//		log.info(arg0.toString(), arg1);
//	}
//
//	/**
//	 * warn
//	 * @param arg0
//	 */
//	public void warn(Object arg0) {
//		log.warn(arg0.toString());
//	}
//
//	/**
//	 * warn
//	 * @param arg0
//	 * @param arg1
//	 */
//	public void warn(Object arg0, Throwable arg1) {
//		log.warn(arg0.toString(), arg1);
//	}
//
//	/**
//	 * error
//	 * @param arg0
//	 */
//	public void error(Object arg0) {
//		log.error(arg0.toString());
//	}
//
//	/**
//	 * error
//	 * @param arg0
//	 * @param arg1
//	 */
//	public void error(Object arg0, Throwable arg1) {
//		log.error(arg0.toString(), arg1);
//	}
//
//	/**
//	 * fatal
//	 * @param arg0
//	 */
//	public void fatal(Object arg0) {
//		log.error(arg0.toString());
//	}
//
//	/**
//	 * fatal
//	 * @param arg0
//	 * @param arg1
//	 */
//	public void fatal(Object arg0, Throwable arg1) {
//		log.error(arg0.toString(), arg1);
//	}
}
