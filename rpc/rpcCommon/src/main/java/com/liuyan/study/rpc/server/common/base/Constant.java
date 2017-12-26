package com.liuyan.study.rpc.server.common.base;

/**
 * Created by liuyan on 2017/12/26.
 */
public interface Constant {
    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/data";
}
