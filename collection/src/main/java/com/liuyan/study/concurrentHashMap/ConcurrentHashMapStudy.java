package com.liuyan.study.concurrentHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 *concurrentHashMap
 * 并发安全
 * 直接支持一些原子复合操作
 * 支持高并发、读操作完全并行、写操作支持一定程度的并行
 * 与同步容器Collections.synchronizedMap相比，迭代不用加锁，不会抛出ConcurrentModificationException
 * 弱一致性
 *
 * Created by liuyan on 2017/9/4.
 */
public class ConcurrentHashMapStudy {
    public static void unsafeConcurrentUpdate() {
//        final Map<Integer, Integer> map = new HashMap<>();  // 会出现死循环，占满CPU。
        final Map<Integer, Integer> map = new ConcurrentHashMap<>(); //在这使用ConcurrentHashMap 正常使用
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread() {
                Random rnd = new Random();

                @Override
                public void run() {
                    for (int i = 0; i < 10000000; i++) {
                        map.put(rnd.nextInt(), 1);
                    }
                }
            };
            t.start();
        }
    }

    public static void main(String[] args) {
        unsafeConcurrentUpdate();
    }
}
