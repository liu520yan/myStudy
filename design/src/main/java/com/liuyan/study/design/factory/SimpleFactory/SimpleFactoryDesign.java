package com.liuyan.study.design.factory.SimpleFactory;

/**
 * 来源：http://www.jianshu.com/p/6939416ecfa3
 * 简单工厂模式
 * Created by liuyan on 2017/9/14.
 */
public class SimpleFactoryDesign {

    public interface Product {

    }

    public class Tv implements Product {
        public Tv() {
            System.out.println("电视被制造了");
        }
    }

    public class Car implements Product {
        public Car() {
            System.out.println("汽车被制造了");
        }
    }

    public class ProductFactory {
        public Product produce(String productName) throws Exception {
            switch (productName) {
                case "tv":
                    return new Tv();
                case "car":
                    return new Car();
                default:
                    throw new Exception("没有该产品");
            }
        }
    }

    public class ProductFactory2 {
        public Product produce(String className) throws Exception {
            try {
                return (Product) Class.forName(className).newInstance();
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
            throw new Exception("不存在该产品");
        }
    }
}
