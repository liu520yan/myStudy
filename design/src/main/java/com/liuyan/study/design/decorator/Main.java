package com.liuyan.study.design.decorator;

/**
 * Created by liuyan on 2017/12/14.
 */
public class Main {
    public static void main(String[] args) {
        //创建一个需要被装饰的对象
        ConcreteComponent concreteComponent = new ConcreteComponent();

        //创建一系列修饰类的对象
        ConcreteDecoratorA decoratorA = new ConcreteDecoratorA();
        ConcreteDecoratorB decoratorB = new ConcreteDecoratorB();

        //先使用ConcreteDecoratorA包装被装饰对象
        decoratorA.setComponent(concreteComponent);
        //再使用ConcreteDecoratorB包装decoratorA对象
        decoratorB.setComponent(decoratorA);
    }
}
