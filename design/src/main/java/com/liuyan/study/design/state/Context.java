package com.liuyan.study.design.state;

import lombok.Data;

/**
 * Created by liuyan on 2017/12/25.
 */
@Data
public class Context {
    private State state;

    public void request(){
//        this.state.hander();
    }
}
