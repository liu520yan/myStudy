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
            int tmp;
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
        int[] a = new int[5];

        for (int i = 0; i < 5; i++) {
            int random = (int) (Math.random() * 1000);
            a[i] = random;
        }
//        bubbleSort.sort(a, false);
        bubbleSort.sss(a, true);
        for (int i = 0; i < 5; i++) {
            System.out.print(a[i] + ",");
        }

    }

    private void sss(int[] data, Boolean b) {
        if (data == null || data.length < 2) {
            return;
        }
        for (int i = 0; i < data.length - 1; i++) {
            int tmp;
            for (int j = i - 1 < 0 ? 0 : i - 1; j < data.length - 1; j++) {
                if (b) {
                    if (data[j] >= data[j + 1]) {
                        tmp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = tmp;
                    }
                } else {
                    if (data[j] < data[j + 1]) {
                        tmp = data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = tmp;
                    }
                }

            }
        }
    }
}
