package com.liuyan.study.design.factory.simpleFactory.singleton;

/**
 * Created by liuyan on 2017/9/15.
 * 加上同步锁，解决线程可能导致的同时new两个对象的问题。
 */
public class SingletonA2 {
    private static SingletonA2 singleton2;

    private SingletonA2() {

    }

    public synchronized static SingletonA2 getInstance() {
        if (singleton2 == null) {
            singleton2 = new SingletonA2();
        }
        return singleton2;
    }
}
