package com.liuyan.study.design.singleton;

/**
 * Created by liuyan on 2017/9/15.
 * double check 版本
 */
public class SingletonA3 {
    private static SingletonA3 singleton;

    private static SingletonA3 getSingletonA3() {
        if (singleton == null) { //减少进去同步代码块的几率
            synchronized (SingletonA3.class) {
                if (singleton == null) { //判断是否存在实例 ,在上一个判空动作之后其他线程可能会新建实例。
                    singleton = new SingletonA3();
                }
            }
        }
        return singleton;
    }
}
