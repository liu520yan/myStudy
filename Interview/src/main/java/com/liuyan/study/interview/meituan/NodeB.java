package com.liuyan.study.interview.meituan;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyan on 2017/12/8.
 */
@ToString
public class NodeB extends Node {
    private List<NodeB> children;

    public NodeB(int id, int patentId, String code, List<NodeB> children) {
        super(id, patentId, code);
        this.children = children;
    }

    public List<NodeB> getChildren() {
        return children;
    }

    public void setChildren(List<NodeB> children) {
        this.children = children;
    }

    public void setChildrens(List<NodeB> children) {
        List<NodeB> nodeBS = new ArrayList<>();
        for (NodeB nodeB : children) {
            if (super.getId() + 1 == nodeB.getId()) {
                nodeB.setChildrens(children);
                nodeBS.add(nodeB);
                nodeB.setCode("code" + nodeB.getId());
                nodeB.setPatentId(nodeB.getId() - 1);
            }
        }
        setChildren(nodeBS);
    }
}
