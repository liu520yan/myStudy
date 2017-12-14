package com.liuyan.study.design.factory.simpleFactory.singleton;

/**
 * Created by liuyan on 2017/9/15.
 */
public class SingletonA4 {
    private static volatile SingletonA4 singleton; //防止指令重排。

    private SingletonA4() {

    }

    public static SingletonA4 getInstance() {
        if (singleton == null) {
            synchronized (SingletonA4.class) {
                if (singleton == null) {
                    singleton = new SingletonA4();
                }
            }
        }
        return singleton;
    }
}
