package com.liuyan.study.annotation;

import java.lang.annotation.*;

/**
 * Created by liuyan on 2017/12/21.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface ReadOnly {
    boolean readOnly() default true;

    String msg() default "hello";
}
