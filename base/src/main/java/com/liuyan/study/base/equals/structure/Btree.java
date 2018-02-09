package com.liuyan.study.base.equals.structure;

import javax.xml.soap.Node;

/**
 * Created by liuyan on 2018/1/26.
 */
public class Btree {

    //选择一个值M 构造一个多向树
    private static final int M = 4;

    //更节点
    private Node node;

    //高
    private int Height;

    private static final class Node {
        private int children_length;
        private Entry[] children = new Entry[M];
        // create a node with k children
        private Node(int k) {
            children_length = k;
        }
    }

    // internal nodes : only use key and next
// external nodes : only use key and value
    private static class Entry {
        private Comparable key;
        private final Object value;
        private Btree.Node next;
        private Entry(Comparable key, Object value, Btree.Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
