package org.xinyu.com.mq;

import com.digital.dance.framework.infrastructure.commons.Log;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.core.io.support.ResourcePropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
public class SystemPropertySourceFactory implements PropertySourceFactory {

    private Log log = new Log( SystemPropertySourceFactory.class );

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource encodedResource) throws IOException {

        String activeEnv = "";
        try {
            activeEnv = SpringContextUtil.getActiveProfile();
        } catch (Exception ex){
            LoggerUtils.info( AppPropsConfig.class, GsonUtils.toJsonStr(ex) );
        }
        //spring.profiles.active = dev,test
        //取得当前活动的环境名称（因为直接获取spring.profiles.active 失败，所以才把环境名称拼在文件名后面来拿）
        //其实感觉应该有可以直接取的方法比如从环境里取
        String[] fileproperty = encodedResource.getResource().getFilename().split("\\.");
        String suffix = fileproperty[1];

        String fileName = fileproperty[0];

        String[] actives = activeEnv.split(",");

        //如果是多个
        List<InputStream> inputStreamList = new ArrayList<>();

        //遍历后把所有环境的url全部抓取到list中
        Arrays.stream(actives).forEach(active -> {
            String resourceName = fileName.concat("-" + active).concat(".").concat(suffix);
            InputStream in = SpringContextUtil.getApplicationClass().getResourceAsStream( "/" + resourceName );
            if (in != null) {
                inputStreamList.add(in);
                log.info("SpringContextUtil.getApplicationClass().getResourceAsStream" );
            } else {
                in = SpringContextUtil.getApplicationClass().getClassLoader().getResourceAsStream( resourceName );
                if (in != null) {
                    inputStreamList.add(in);
                    log.info("SpringContextUtil.getApplicationClass().getClassLoader().getResourceAsStream" );
                }
            }
        });

        if (inputStreamList != null && inputStreamList.size() > 0) {

            //串行流，将多个文件流合并车一个流
            SequenceInputStream inputStream = new SequenceInputStream( Collections.enumeration( inputStreamList ) );
            //转成resource
            InputStreamResource resource = new InputStreamResource( inputStream );

            return (fileName != null ? new ResourcePropertySource( fileName, new EncodedResource( resource ) ) : new ResourcePropertySource(new EncodedResource( resource ) ) );
        } else {
            return (fileName != null ? new ResourcePropertySource( fileName, encodedResource ) : new ResourcePropertySource( encodedResource ) );
        }
    }
}
