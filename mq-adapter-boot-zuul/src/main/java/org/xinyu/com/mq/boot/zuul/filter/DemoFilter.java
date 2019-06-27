package org.xinyu.com.mq.boot.zuul.filter;

import com.digital.dance.framework.infrastructure.commons.Log;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DemoFilter extends ZuulFilter {
    Log log = new Log(DemoFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(String.format("%s request to %s, session:%s", request.getMethod(), request.getRequestURL().toString(), request.getSession().getId()));

        return null;
    }

}
