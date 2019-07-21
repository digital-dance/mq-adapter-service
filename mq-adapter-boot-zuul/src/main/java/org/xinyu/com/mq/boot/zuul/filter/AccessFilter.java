package org.xinyu.com.mq.boot.zuul.filter;

import com.digital.dance.client.core.shiro.filter.PermissionHelper;
import com.digital.dance.framework.infrastructure.commons.AppPropsConfig;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.digital.dance.framework.infrastructure.commons.Log;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

@RefreshScope
@Component
public class AccessFilter extends ZuulFilter {
    Log log = new Log(AccessFilter.class);

    @Autowired
    private PermissionHelper permissionHelper;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    HttpServletResponse httpServletResponse;

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String sessionId = httpServletRequest.getSession().getId();

        HttpServletRequest request = ctx.getRequest();
        String value0 = request.getHeader("cookie");
        String value = request.getHeader("Cookie");
        if ( (value0 == null || "".equalsIgnoreCase(value0)) && (value == null || "".equalsIgnoreCase(value)) ) {
            ctx.addZuulRequestHeader("Cookie", "SESSION=" + sessionId);
        }
        // 如果在Cookie内通过如下方式取
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                ctx.addZuulRequestHeader(cookie.getName(), cookie.getValue());
                log.info("request.getCookies():: " + cookie.getName() + " : " + cookie.getValue());
            }
        } else {
            log.warn("AccessFilter, 获取Cookie失败！");
        }
        // 如果放在header内通过如下方式取
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();

                Enumeration<String> values = request.getHeaders(name);
                if(values != null && values.hasMoreElements()) {
                    while (values.hasMoreElements()) {
                        String value1 = values.nextElement();
                        if("Cookie".equalsIgnoreCase(name)){
                            ctx.addZuulRequestHeader("Cookie", value1);
                        }else {
                            ctx.addZuulRequestHeader(name, value1);
                        }
                        log.info("request.getHeaders:: " + name + " : " + value1);
                    }
                }
            }
        } else {
            log.warn("AccessFilter, 获取请求头失败！");
        }
        Map map = AppPropsConfig.getProperties("classpath:system.properties", AccessFilter.class);
        log.info(map);
        getPermissionHelper().init(map);
        ctx.setSendZuulResponse(true);// 对该请求进行路由
        ctx.setResponseStatusCode(200); // 返回200正确响应
        //return ctx;
        return null;
    }

    @Override
    public String filterType() {
        // pre：路由之前 routing：路由之时 post： 路由之后 error：发送错误调用
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    public PermissionHelper getPermissionHelper() {
        return (permissionHelper == null) ? new PermissionHelper() : permissionHelper;
    }

    public void setPermissionHelper(PermissionHelper permissionHelper) {
        this.permissionHelper = permissionHelper;
    }

}
