package org.xinyu.com.mq;

import com.digital.dance.framework.infrastructure.commons.AppPropsConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Locale;

@Configuration
@Component
public class SpringContextUtil implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static ApplicationContext context = null;
    private static String activeProfile;
    private static Class applicationClass;
    private static ConfigurableListableBeanFactory beanFactory; // Spring应用上下文环境

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringContextUtil.beanFactory = beanFactory;
    }

    /* (non Javadoc)
     * @Title: setApplicationContext
     * @Description: spring获取bean工具类
     * @param applicationContext
     * @throws BeansException
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
//        this.context = applicationContext;
        if(null == context) {
            context = applicationContext;
        }
    }

    // 传入线程中
    public static <T> T getBean(String beanName) {
        if (beanFactory != null) {
            @SuppressWarnings("unchecked")
            T result = (T) beanFactory.getBean(beanName);
            return result;
        } else if (context != null) {
            @SuppressWarnings("unchecked")
            T result = (T) context.getBean(beanName);
            return result;
        } else {
            return null;
        }
    }

    /**
     * 获取类型为requiredType的对象
     *
     * @param clz
     * @return
     * @throws BeansException
     *
     */
    public static <T> T getBean(Class<T> clz) throws BeansException {
        if( beanFactory != null ) {
            @SuppressWarnings("unchecked")
            T result = (T) beanFactory.getBean(clz);
            return result;
        } else if ( context != null ) {
            @SuppressWarnings("unchecked")
            T result = (T) context.getBean(clz);
            return result;
        } else {
            return null;
        }
    }

    // 国际化使用
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }

    /// 获取当前环境
    public static String getActiveProfile() throws IOException {
//        return context.getEnvironment().getActiveProfiles()[0];
        if(SpringContextUtil.applicationClass == null){
            throw new IOException( "SpringContextUtil.applicationClass is need to set in main by SpringContextUtil.setApplicationClass");
        }
        if( activeProfile == null ) {
            activeProfile = AppPropsConfig.getStrProperties("application.properties", SpringContextUtil.applicationClass)
                    .get("spring.profiles.active");
        }
        return activeProfile;
    }

    public static void setApplicationClass(Class pApplicationClass){
        SpringContextUtil.applicationClass = pApplicationClass;
    }

    public static Class getApplicationClass(){
        return SpringContextUtil.applicationClass;
    }
}
