package com.liuyan.study.annotation;

import java.lang.annotation.*;

/**
 * Created by liuyan on 2017/11/30.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface User {
    int age() default 18;
}
