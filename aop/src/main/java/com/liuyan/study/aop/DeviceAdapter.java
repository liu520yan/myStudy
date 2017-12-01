package com.liuyan.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuyan on 2017/11/30.
 */
@Aspect
@Component
@Slf4j
public class DeviceAdapter {
    private static final String MOBILE_PREFIX = "mobile/";

    /**
     * 切入点：cn.ictgu.controller 下所有 @GetMapping 方法
     */
    @Pointcut("execution(* com.liuyan.study.controller..*(..)) && @annotation(com.liuyan.study.annotation.User)")
//    @Pointcut("@annotation(com.liuyan.study.User)")
    public void controllerMethodPointcut() {
    }

    /**
     * 识别用户请求的设备并返回对应的页面
     */
    @Around("controllerMethodPointcut()")
    public String around(ProceedingJoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            try {
                HttpServletRequest request = attributes.getRequest();
                String userAgent = request.getHeader("User-Agent");

                Integer deviceType = UserAgentTools.recognize(userAgent);
                String path = (String) joinPoint.proceed();
                return deviceType == UserAgentTypeEnum.PHONE.getCode() ? MOBILE_PREFIX + path : path;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("DeviceAdapter，ServletRequestAttributes is null!");
    }
}
