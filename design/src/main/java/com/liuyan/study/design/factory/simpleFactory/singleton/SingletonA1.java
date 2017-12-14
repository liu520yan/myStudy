package com.liuyan.study.design.factory.simpleFactory.singleton;

/**
 * 饿汉式单例模式 在类装载时就构建
 * 这种单例在两个线程同时运行时，可能同时发现instance为空，同时new，此时会出现两个实例
 * Created by liuyan on 2017/9/15.
 */
public class SingletonA1 {
    private static SingletonA1 instance = null;

    private SingletonA1() {
    }//防止被new

    //instance没有创建时判定为空。
    public static SingletonA1 getInstance() {
        if (instance == null) {
            instance = new SingletonA1();
        }
        return instance;
    }
}
