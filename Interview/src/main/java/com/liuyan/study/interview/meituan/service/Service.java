package com.liuyan.study.interview.meituan.service;

import com.liuyan.study.interview.meituan.tool.Tool;
import com.liuyan.study.interview.meituan.worker.Worker;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 思路：阻塞队列，四个程序员都在里面，然后工具没有加锁就出队，然后就获取工具的锁，sleep一段时间，释放工具的锁，程序员重新入队。
 * Created by liuyan on 2017/12/8.
 */
@Slf4j
public class Service {

    //工人
    private static BlockingQueue<Worker> workers = new ArrayBlockingQueue<>(5);
    private static final Tool t1 = new Tool("c1", "d1", 1);
    private static final Tool t2 = new Tool("c2", "d2", 2);
    static List<Tool> list = Arrays.asList(t1, t2);

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        //入队
        workers.put(new Worker(1));
        workers.put(new Worker(2));
        workers.put(new Worker(3));
        workers.put(new Worker(4));
        //tool


        Service s = new Service();
        for (Tool t : list) {
            new Thread(new Producer(s, t)).start();
        }
    }

    //worker重新入队
    private void setWorkers(Worker w) {
        try {
            workers.put(w);
           log.info("程序员" + w.getId() + "休息");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //消费worker
    private Worker getWorker() throws InterruptedException {
        Worker w = workers.take();
        log.info("程序员" + w.getId() + "工作");
        return w;
    }

    //消费者  消费者
    static class Producer extends Thread {
        private Service s;
        private final Tool t;

        public Producer(Service s, Tool t) {
            this.s = s;
            this.t = t;
        }

        public void run() {
            while (true) {
                synchronized (t) {
                    working();
                }
            }

        }

        private void working() {
            try {
                TimeUnit.SECONDS.sleep(5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Worker w = null;
            try {
                w = s.getWorker();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("size"+workers.size());
            s.setWorkers(w);
            log.info("size"+workers.size());
        }


    }
}
