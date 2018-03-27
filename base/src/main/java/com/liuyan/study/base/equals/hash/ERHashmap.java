package com.liuyan.study.base.equals.hash;

/**
 * Created by liuyan on 2018/3/2.
 */
public class ERHashmap<K, V> implements ERMap<K, V> {

    private static Integer defLength = 16;

    private static double defLoad = 0.75;

    private Entry<K, V>[] table = null;//定一个数组，盛放Entry对象

    private int size = 0;//定义一个常量，用来记录数组元素个数

    ERHashmap(int defLength, double defLoad) {
        this.defLength = defLength;
        this.defLoad = defLoad;

        table = new Entry[defLength];//定义一个默认数组，长度就是传过来的长度
    }

    ERHashmap() {
        this(defLength, defLoad);
    }

    @Override
    public V put(K key, V value) {
        //得到要放的数据的位置:也就是数组的下标
        int index = this.getIndex(key);

        //根据这个下标判断该数据是否有数据
        Entry<K, V> e = table[index];
        if (null == e) {
            table[index] = new Entry(key, value, null, index);
            size++;//数组长度加1
        } else {
            Entry newEntry = new Entry(key, value, e, index);
            table[index] = newEntry;
        }

        return table[index].getValue();
    }

    @Override
    public V get(K key) {
        //得到要放的数据的位置:也就是数组的下标
        int index = this.getIndex(key);
        return table[index] == null ? null : table[index].getValue();
    }

    @Override
    public int size() {
        return size;
    }


    //找数组下标的方法
    private int getIndex(K key) {
        //除留取余数法
        //m的取值是比数组长度小的质数的最大值
        //这里定义的长度为16，那么m就是13
        int m = defLength - 3;
        return key.hashCode() % m;
    }

    class Entry<K, V> implements ERMap.Entry<K, V> {

        K key;

        V value;

        Entry<K, V> next;

        int index;//记录下标

        Entry(K k, V v, Entry<K, V> n, int inx) {
            key = k;
            value = v;
            index = inx;
            next = n;//数组第一个元素的下一个元素
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}
