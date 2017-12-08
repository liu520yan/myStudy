package com.liuyan.study.interview.meituan;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * Created by liuyan on 2017/12/8.
 */
@AllArgsConstructor
public class Node {
    private int id;
    private int patentId;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPatentId() {
        return patentId;
    }

    public void setPatentId(int patentId) {
        this.patentId = patentId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
