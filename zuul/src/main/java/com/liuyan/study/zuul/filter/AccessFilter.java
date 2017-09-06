package com.liuyan.study.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.liuyan.study.zuul.security.SignatureGenerator.generate;

/**
 * Created by liuyan on 2017/9/6.
 */

public class AccessFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);

    @Override
    public String filterType() {
        return "pre";
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
    public Object run() {

        String secretKey = null;

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> stringStringMap = new HashMap<>();
        for (String key : parameterMap.keySet()) {
            if ("secretKey".equals(key)) {
                secretKey = parameterMap.get(key)[0];
                continue;
            }
            stringStringMap.put(key, parameterMap.get(key)[0]);
        }
        String uri = request.getRequestURI();
        String sign = null;
        try {
            sign = generate(uri, stringStringMap, secretKey);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        request.removeAttribute("secretKey");
        request.setAttribute("secretKey", sign);
        ctx.setRequest(request);

        log.info("add sign success !!!  sign \n{}", sign);
        return null;
    }
}
