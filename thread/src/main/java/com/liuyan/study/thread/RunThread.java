package com.liuyan.study.thread;

import com.liuyan.study.thread.extendThread.ThreadA;
import com.liuyan.study.thread.extendThread.ThreadB;
import com.liuyan.study.thread.runnabale.ThreadC;
import com.liuyan.study.thread.runnabale.ThreadD;

/**
 * Created by liuyan on 2017/9/4.
 */
public class RunThread {

    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        ThreadD threadD = new ThreadD();

        //thread方法启动线程
        threadA.run();
        threadB.run();

        //runnable 方法启动线程
        Thread c = new Thread(threadC);
        c.start();
        Thread d = new Thread(threadD);
        d.start();
    }


}
