package com.liuyan.study.thread.wait;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by liuyan on 2018/3/22.
 */
public class MultipleThread {

    public static void main(String args[]) {
        System.out.println("How to use wait and notify method in Java");
        System.out.println("Solving Producer Consumper Problem");
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;
        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");
        producer.start();
        consumer.start();
    }
}
