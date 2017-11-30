package com.liuyan.study.base.equals;


/**
 * Created by liuyan on 2017/9/11.
 */
public class equals {
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    public static void main(String[] args) {
        Integer a = 100, b = 100, c = 150, d = 150;
        System.out.println(a == b);
        System.out.println(c == d);
        String s = "wsd";
        String s2 = new String("wsd");
        System.out.println(s==s2);
        System.out.println(DEFAULT_INITIAL_CAPACITY);

    }
}

