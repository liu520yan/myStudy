//package com.liuyan.study;
//
//import com.alibaba.fastjson.JSONObject;
//import edu.princeton.cs.algs4.Quick;
//import edu.princeton.cs.algs4.StdRandom;
//
//import java.util.Random;
//
///**
// * 选择排序
// * <p>
// * 从第一个元素开始，找出最小的那个数，并与第一个元素交换位子。
// * 然后第二个、第三个元素依次进行。
// * <p>
// * 选择排序需要 N的2次方/2次交换   N次交换
// * Created by liuyan on 2017/10/20.
// */
//public class ChoseSort extends BaseData {
//
//    private static Comparable[] choseSort(Comparable[] a) {
//        int length = a.length;
//        for (int i = 0; i < length; i++) {
//            for (int j = 1; j <; j++) {
//
//            }
//        }
//
//        for (int i = 0; i < N; i++) {
//            int min = i + 1;
//            if (i < N - 1) {
//                break;
//            }
//            if (a[min].compareTo(a[i]) > 0) {
//                //交换位子
//                Comparable temp = a[min];
//                a[min] = a[i];
//                a[i] = temp;
//            }
//        }
//        return a;
//    }
//
//
//    public static void main(String[] args) {
//        Double[] a = getDoubles(10);
//        System.out.println(JSONObject.toJSONString(choseSort(a)));
//
//    }
//}
