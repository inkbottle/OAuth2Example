package com.yithm.proxy.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Configuration
public class AuthorizationFilter extends ZuulFilter {
    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    private Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public String filterType() {
        return "route";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        String token = "Bearer " + oAuth2RestTemplate.getAccessToken().getValue();
        logger.info("got access token: " + token);
        ctx.addZuulRequestHeader("Authorization", token);
        HttpServletRequest request = ctx.getRequest();
        Enumeration<String> itr = request.getHeaderNames();
        while(itr.hasMoreElements()) {
            String headerName = itr.nextElement();
            logger.info("got header:"+ headerName + " -----> " + request.getHeader(headerName));
        }
        return null;
    }
}
