package com.ecommerce.gateway.interceptor;

/**
 * For those direct access to api-gateway without zuul routing
 * we still need a separate interceptor to log request
 *
 * Created by zhengzhiqing on 2018/6/18.
 */
import com.ecommerce.gateway.util.RequestLogging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * log request/response
 */
@Configuration
public class AccessLogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(AccessLogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info(RequestLogging.logRequest(request));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}