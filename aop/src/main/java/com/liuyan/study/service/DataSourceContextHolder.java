package com.liuyan.study.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by liuyan on 2017/12/21.
 */
@Slf4j
@Component
public class DataSourceContextHolder {

    public static final String DATA_SOURCE_WRITE = "WRITE";

    public static final String DATA_SOURCE_READ = "READ";
    // 线程本地环境
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    // 设置数据源类型
    public static void setType(String type) {
        log.info("==============切换数据源，类型：" + type + "================");
        contextHolder.set(type);
    }

    // 获取数据源类型
    public static String getType() {
        log.info("==============获取数据源，类型：" + contextHolder.get() + "================");
        return (contextHolder.get());
    }

    // 清除数据源类型
    public static void clearType() {
        log.info("==============清除数据源=====================");
        contextHolder.remove();
    }
}
