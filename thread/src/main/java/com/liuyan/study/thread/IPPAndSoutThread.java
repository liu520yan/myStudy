package com.liuyan.study.thread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Created by liuyan on 2017/9/4.
 */
public class IPPAndSoutThread extends Thread {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    RestTemplateBuilder restTemplateBuilder;
    @Autowired
    AsyncRestTemplate asyncRestTemplate;

    private int i = 100;

    @Override
    public void run() {
        System.out.println("i=" + (i--) + " name:="+ Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {

        IPPAndSoutThread thread = new IPPAndSoutThread();
        for (int i =0 ; i< 100 ;i++) {
            Thread thread1 = new Thread(thread);
            thread1.start();
            Thread.sleep(123);
            TimeUnit.HOURS.sleep(1L);
            thread.wait();
            thread.notify();
            synchronized (thread) {

            }
            Thread.yield();
        }

    }


}