package com.liuyan.study.base.equals.sort;

/**
 * 直接插入排序
 * Created by liuyan on 2017/12/21.
 */
public class DirectSort {
    private void sort(int[] data, boolean reverse) {
        if (data == null || data.length == 1) {
            return;
        }
        int[] target = new int[data.length];
        for (int i = 0; i < target.length - 1; i++) {
            arraySort(target, data[i]);
        }
    }

    private void arraySort(int[] target, int datum) {
        if (target == null || target.length == 0) {
            return;
        }
        for (int i = 0; i < target.length - 1; i++) {
            if (target[i] < datum) {
                if (i + 1 < target.length && target[i + 1] > datum) {

                }else {

                }
            }
        }
    }

}
