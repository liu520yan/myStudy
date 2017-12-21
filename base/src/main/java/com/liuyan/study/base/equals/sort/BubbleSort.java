package com.liuyan.study.base.equals.sort;

import java.util.Random;

/**
 * 冒泡排序
 * Created by liuyan on 2017/12/20.
 */
public class BubbleSort {
    /**
     * @param data    需要排序的数组
     * @param reverse 排序方式  true:从大到小 ， true 从小到大
     */
    private void sort(int[] data, boolean reverse) {
        if (data == null || data.length == 1) {
            return;
        }
        for (int i = 0; i < data.length - 1; i++) {
            int tmp = 0;
            for (int j = 0; j < data.length - 1; j++) {
                if (reverse) {
                    if (data[j] >= data[j + 1]) {
                        //换位子
                        tmp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = tmp;
                    }
                } else {
                    if (data[j] <= data[j + 1]) {
                        //换位子
                        tmp = data[j + 1];
                        data[j + 1] = data[j];
                        data[j] = tmp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        int[] a = new int[20];

        for (int i = 0; i < 20; i++) {
            int random = (int) (Math.random() * 2000);
            a[i] = random;
        }
        bubbleSort.sort(a, false);
        for (int i = 0; i < 20; i++) {
            System.out.print(a[i]+",");
        }

    }
}
