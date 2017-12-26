package com.liuyan.study.design.state;

/**
 * Created by liuyan on 2017/12/25.
 */
public class State {
    public void hander(Context context) {
        if (context.getState() == new State()) {
            //
        } else {
            //本类不能处理时
            //将context的state变量设为能处理状态类的对象
            context.setState(new ConcreteStateB());
            //执行context中的request，让下一个状态类处理
            context.request();
        }
    }
}
