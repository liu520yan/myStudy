package com.liuyan.study.base.equals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by liuyan on 2017/11/27.
 */
public class InvokeStudy {
    public void sayHello() {
        System.out.println("hello word !!!");
    }

    public void sayParam(String... param) {
        Arrays.stream(param).forEach(System.out::println);
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        InvokeStudy invokeStudy = new InvokeStudy();
        Method method = InvokeStudy.class.getMethod("sayParam", String[].class);
        method.invoke(invokeStudy, new String[][]{new String[]{"sds","sdf"}});

    }
}
