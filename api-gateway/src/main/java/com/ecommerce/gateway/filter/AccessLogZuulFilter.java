package com.ecommerce.gateway.filter;

import com.ecommerce.gateway.util.RequestLogging;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhengzhiqing on 16/12/17.
 */
@Component
public class AccessLogZuulFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(AccessLogZuulFilter.class);

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
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        logger.info(RequestLogging.logRequest(request));
        return null;
    }
}
