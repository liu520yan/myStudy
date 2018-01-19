package com.liuyan.study.rpc.server.common.common.base;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liuyan on 2017/12/26.
 */
@Getter
@Setter
public class RpcRequest {
    private String requestId;
    private String className;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
}
