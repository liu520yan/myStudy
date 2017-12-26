package com.liuyan.study.rpc;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by liuyan on 2017/12/26.
 */
@Target(ElementType.TYPE) // 用于描述类、接口(包括注解类型) 或enum声明
@Retention(RetentionPolicy.RUNTIME) //这种类型的Annotations将被JVM保留,所以他们能在运行时被JVM或其他使用反射机制的代码所读取和使用.
@Component
public @interface RpcService {
    Class<?> value();
}
