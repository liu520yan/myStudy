package com.liuyan.study.thread.threadPool;

import java.util.concurrent.*;

/**
 * Created by liuyan on 2017/11/24.
 */
public class ThreadPool {

    private int corePoolSize = 1;//核心线程池大小
    private int maximumPoolSize; //最大线程池大小
    private long keepAliveTime; //非核心线程空闲时间
    private TimeUnit unit; //单位
    private BlockingQueue<Runnable> workQueue; //队列
    private ThreadFactory threadFactory;  //线程工厂
    private RejectedExecutionHandler handler;  //

    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);


    public static void main(String[] args) {

    }
}
