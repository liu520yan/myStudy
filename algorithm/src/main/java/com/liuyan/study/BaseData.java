package com.liuyan.study;

import java.util.Random;

/**
 * @author liuyan
 * @date 2017/10/20
 */
public class BaseData {
    public static final Random random = new Random();


    public static Double[] getDoubles(int size) {
        Double[] aDouble = new Double[size];
        for (int i = 0; i < size; i++) {
            aDouble[i] = random.nextDouble();
        }
        return aDouble;
    }
}
