package com.liuyan.study.thread.callBack;

import java.util.concurrent.Callable;

/**
 * Created by liuyan on 2017/9/4.
 */
public class CallBack implements Callable<String> {
    @Override
    public String call() throws Exception {
        return this.getClass().getName()+"---------- Hello CallBack";
    }
}
