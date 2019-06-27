package org.xinyu.com.mq.boot.zuul;

import com.digital.dance.client.core.shiro.filter.PermissionHelper;
import com.digital.dance.client.core.shiro.service.impl.PermissionImpl;
import com.digital.dance.service.Permission;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PermissionConfiguration {

    @Bean(name="permissionHelper")
    public PermissionHelper permissionHelper(){
        return new PermissionHelper();
    }

    @Bean(name="permission")
    public Permission permission(){
        return new PermissionImpl();
    }
}
