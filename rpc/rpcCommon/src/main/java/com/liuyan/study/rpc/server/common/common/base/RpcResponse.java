package com.liuyan.study.rpc.server.common.common.base;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liuyan on 2017/12/26.
 */
@Getter
@Setter
public class RpcResponse {
    private String requestId;
    private Throwable error;
    private Object result;
}
