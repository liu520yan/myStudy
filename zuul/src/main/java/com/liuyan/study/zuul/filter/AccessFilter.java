package com.liuyan.study.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        Map<String, List<String>> map = new HashMap<>();
        for (String key : parameterMap.keySet()) {

            if ("secretKey".equals(key)) {
                secretKey = parameterMap.get(key)[0];
                continue;
            }
            map.put(key, Arrays.asList(parameterMap.get(key)));
            stringStringMap.put(key, parameterMap.get(key)[0]);
        }
        String uri = request.getRequestURI();
        //除去第一个/后的路径
        String pattern = "/\\w+/(\\w+/.*?\\Z)";
        String sign = null;
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(uri);
        if (m.find()) {
            uri = m.group(1);
        }
        try {
            sign = generate(uri, stringStringMap, secretKey);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));

        request.removeAttribute("secretKey");
        request.setAttribute("secretKey", sign);

        map.put("sign", Collections.singletonList(sign));
        ctx.setRequestQueryParams(map);

        log.info("add sign success !!!  sign \n{}", sign);
        return null;
    }

    public static void main(String[] args) {
        String str = "/tima/name/?34sf";
        String pattern = "/\\w+/(\\w+/.*?\\Z)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        if (m.find()) {
            System.out.println(m.group(1));
        }
    }
}
