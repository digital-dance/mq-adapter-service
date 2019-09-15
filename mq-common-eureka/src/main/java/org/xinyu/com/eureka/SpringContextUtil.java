package org.xinyu.com.eureka;

import com.digital.dance.framework.infrastructure.commons.AppPropsConfig;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Configuration
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext context = null;
    private static String activeProfile;

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
    public static  <T> T getBean(String beanName) {
        return (T) context.getBean(beanName);
    }

    // 国际化使用
    public static String getMessage(String key) {
        return context.getMessage(key, null, Locale.getDefault());
    }

    /// 获取当前环境
    public static String getActiveProfile() {
//        return context.getEnvironment().getActiveProfiles()[0];
        if( activeProfile == null ) {
            activeProfile = AppPropsConfig.getStrProperties("application.properties", EurekaServerApplication.class)
                    .get("spring.profiles.active");
        }
        return activeProfile;
    }
}
