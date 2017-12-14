package com.liuyan.study.design.factory.simpleFactory.singleton;

/**
 * Created by liuyan on 2017/9/15.
 */
public class SingletonB1 {
    //饿汉式实现
    private static final SingletonB1 INSTANCE = new SingletonB1();

    private SingletonB1() {
    }

    public static SingletonB1 getInstance() {
        return INSTANCE;
    }

}
