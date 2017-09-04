package com.liuyan.study.thread;

import java.util.Random;

/**
 * Created by liuyan on 2017/9/4.
 */
public class IPPAndSoutThread extends Thread {

    private int i = 100;

    @Override
    public void run() {
        System.out.println("i=" + (i--) + " name:="+ Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        IPPAndSoutThread thread = new IPPAndSoutThread();
        for (int i =0 ; i< 100 ;i++) {
            Thread thread1 = new Thread(thread);
            thread1.start();
        }

    }
}