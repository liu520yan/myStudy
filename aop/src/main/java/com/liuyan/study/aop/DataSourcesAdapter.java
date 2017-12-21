package com.liuyan.study.aop;

import com.liuyan.study.annotation.ReadOnly;
import com.liuyan.study.annotation.User;
import com.liuyan.study.service.DataSourceContextHolder;
import com.liuyan.study.service.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.liuyan.study.service.DataSourceContextHolder.DATA_SOURCE_READ;
import static com.liuyan.study.service.DataSourceContextHolder.DATA_SOURCE_WRITE;

/**
 * Created by liuyan on 2017/12/21.
 */
@Aspect
@Component
@Slf4j
public class DataSourcesAdapter {
    /**
     * 切入点：cn.ictgu.controller 下所有 @GetMapping 方法
     */
    @Pointcut("@annotation(com.liuyan.study.annotation.ReadOnly) && @annotation(readOnly)")
//    @Pointcut("@annotation(com.liuyan.study.User)")
    public void controllerMethodPointcut(ReadOnly readOnly) {
    }

    @Around("controllerMethodPointcut(readOnly)")
    public Object around(ProceedingJoinPoint joinPoint, ReadOnly readOnly) throws Throwable {
        try {
            if (readOnly.readOnly()) {
                DataSourceContextHolder.setType(DATA_SOURCE_READ);
            } else {
                DataSourceContextHolder.setType(DATA_SOURCE_WRITE);
            }
            return joinPoint.proceed();
        } finally {
            DataSourceContextHolder.clearType();
        }
    }

//    /**
//     * 识别用户请求的设备并返回对应的页面
//     */
//    @Before(value = "controllerMethodPointcut(readOnly)", argNames = "readOnly")
//    public void around(ReadOnly readOnly) throws Throwable {
//        if (readOnly.readOnly()) {
//            DataSourceContextHolder.setType(DATA_SOURCE_READ);
//        } else {
//            DataSourceContextHolder.setType(DATA_SOURCE_WRITE);
//        }
//    }
//
//    @After(value = "controllerMethodPointcut(readOnly)", argNames = "readOnly")
//    public void after(ReadOnly readOnly) {
//        DataSourceContextHolder.clearType();
//    }


}
