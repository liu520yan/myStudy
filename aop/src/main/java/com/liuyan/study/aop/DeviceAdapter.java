package com.liuyan.study.aop;

import com.liuyan.study.annotation.User;
import com.liuyan.study.utils.UrlParamUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by liuyan on 2017/11/30.
 */
//@Aspect
//@Component
@Slf4j
public class DeviceAdapter {
    private static final String MOBILE_PREFIX = "mobile/";

    /**
     * 切入点：cn.ictgu.controller 下所有 @GetMapping 方法
     */
    @Pointcut("execution(* com.liuyan.study.controller..*(..)) && @annotation(com.liuyan.study.annotation.User) && @annotation(user)")
//    @Pointcut("@annotation(com.liuyan.study.User)")
    public void controllerMethodPointcut(User user) {
    }

    /**
     * 识别用户请求的设备并返回对应的页面
     */
    @Around("controllerMethodPointcut(user)")
    public String around(ProceedingJoinPoint joinPoint, User user) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            try {
                HttpServletRequest request = attributes.getRequest();
                String userAgent = request.getHeader("User-Agent");
                String param = request.getQueryString();
//                if (Base64.isBase64(param)) {
//                    param = new String(Base64.decodeBase64(param), StandardCharsets.UTF_8);
//                }
                Map map = UrlParamUtils.getUrlParams(param);
                Integer deviceType = UserAgentTools.recognize(userAgent);
                String path = (String) joinPoint.proceed();
                String a = deviceType == UserAgentTypeEnum.PHONE.getCode() ? MOBILE_PREFIX + path : path;
                System.out.println(a + user.age() + map.get("userName"));
                return a + user.age() + param;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("DeviceAdapter，ServletRequestAttributes is null!");
    }
}
