package com.liuyan.study.aop;

/**
 * Created by liuyan on 2017/11/30.
 */
public enum UserAgentTypeEnum {

    // 电脑
    PC(0),
    // 平板电脑
    TABLET(1),
    // 手机
    PHONE(2);

    private int code;

    UserAgentTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
