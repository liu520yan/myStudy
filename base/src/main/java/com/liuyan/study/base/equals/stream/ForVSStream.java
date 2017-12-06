package com.liuyan.study.base.equals.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by liuyan on 2017/12/6.
 */
public class ForVSStream {

    public static void main(String[] args) {
        List<String> a = integers();
        long b1 = System.currentTimeMillis();
        System.out.println(forTime(a));
        long end1 = System.currentTimeMillis();
        long time1 = end1 - b1;
        System.out.println("fortime :" + time1);


        long b2 = System.currentTimeMillis();
        System.out.println(stream(a));
        long end2 = System.currentTimeMillis();
        long time2 = end2 - b2;
        System.out.println("fortime :" + time2);
    }

    private static long forTime(List<String> a) {

        long num = 0;

        for (String anA : a) {
            if (anA.length() > 3) {
                num++;
            }
        }
        return num;
    }

    private static long stream(List<String> a) {

        return a.parallelStream().filter(b -> b.length() > 3).count();

    }

    private static List<String> integers() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            String b = UUID.randomUUID().toString();
            list.add(b);
        }
        return list;
    }
}
