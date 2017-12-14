package com.liuyan.study.design.decorator;

/**
 * Created by liuyan on 2017/12/14.
 */
public class ConcreteDecoratorA extends Component {
    //需要被装饰的对象
    private Component component;

    //提供给客户端将需要被装饰的对象设置进来
    public void setComponent(Component component) {
        this.component = component;
    }

    //执行需要被装饰对象原本的operation函数+附加的功能
    public void operation() {
        //新的功能……………………
        super.operation();
        //新的功能……………………
    }
}
