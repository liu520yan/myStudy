package com.liuyan.study.base.equals.pojo;

/**
 * Created by liuyan on 2018/3/20.
 */
public class ManTest {

    public static void main(String[] args) {
        Class man1 = Man.class;
        Man man = new Man();
        Class man2 = man.getClass();
        System.out.println(man1 == man2);
        try {
            Class man3 = Class.forName(Man.class.getName());
            System.out.println(man3 == man1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
