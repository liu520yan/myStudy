package com.liuyan.study.base.equals.hash;

/**
 * Created by liuyan on 2018/3/2.
 */
public interface ERMap<K, V> {
    V put(K key, V value);

    V get(K key);

    int size();

    //定义一个内部接口
    //可以根据Entry对象拿到这个对象的key和value
    interface Entry<K, V> {
        K getKey();
        V getValue();
    }
}
