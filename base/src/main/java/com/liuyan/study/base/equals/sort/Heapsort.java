package com.liuyan.study.base.equals.sort;

/**
 * Created by liuyan on 2017/12/20.
 */
public class Heapsort {
    private void sort(int[] data, boolean reverse) {
        if (data == null || data.length == 1) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            //建堆
            buildHeap(data, 0, data.length - 1, reverse);
        }
    }

    /**
     * 将指定开始和结束段的数据建堆
     *
     * @param data       数据
     * @param beginIndex 开始段
     * @param endIndex   结束段
     * @param reverse    排序方式
     */
    private void buildHeap(int[] data, int beginIndex, int endIndex, boolean reverse) {
        if (beginIndex >= endIndex) {
            return;
        }
        for (int i = (endIndex + beginIndex - 1) / 2; i >= beginIndex; i--) {
            int cur = i;
            if (reverse) {   //大顶堆,用来从小到大排序
                //发生交换之后需要检查孙子节点,重孙子节点...
                while (2 * cur + 1 <= endIndex) {
                    int biggerChildIndex = 2 * cur + 1;
                    if (biggerChildIndex + 1 <= endIndex) {
                        if (data[biggerChildIndex] < data[biggerChildIndex + 1]) {
                            biggerChildIndex = biggerChildIndex + 1;
                        }
                    }
                    //找到最大子节点,如果比当前节点大,就交换
                    if (data[i] < data[biggerChildIndex]) {
                        int tmp = data[i];
                        data[i] = data[biggerChildIndex];
                        data[biggerChildIndex] = tmp;
                        //准备检查孙子节点
                        cur = biggerChildIndex;
                    } else {
                        break;
                    }
                }
            } else {    //小顶堆,用来从大到小排序
                //发生交换之后需要检查孙子节点,重孙子节点...
                while (2 * cur + 1 <= endIndex) {
                    int samllerChildIndex = 2 * i + 1;
                    if (samllerChildIndex + 1 <= endIndex) {
                        if (data[samllerChildIndex] > data[samllerChildIndex + 1]) {
                            samllerChildIndex = samllerChildIndex + 1;
                        }
                    }
                    //找到最小子节点,如果比当前节点小,就交换
                    if (data[i] > data[samllerChildIndex]) {
                        int tmp = data[i];
                        data[i] = data[samllerChildIndex];
                        data[samllerChildIndex] = tmp;
                        cur = samllerChildIndex;
                    } else {
                        break;
                    }

                }
            }
        }
    }
}