package com.liuyan.study;

/**
 * 最大公约数
 * Created by liuyan on 2017/10/19.
 */
public class GreatestCommonDivisor {

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor(12345, 1));
    }

    /**
     * 获取最大公约数
     * <p>
     * 传入 p q 两个费 负整数。
     * q 等于0 时 ，最大公约数为p
     * p对q取余得到c
     * q和c的最大公约数为p和q 的最大公约数
     *
     * @param p
     * @param q
     * @return
     */
    public static int getGreatestCommonDivisor(int p, int q) {
        if (q == 0) {
            return p;
        }
        int c = p % q;
        return getGreatestCommonDivisor(q, c);
    }
}
