package com.liuyan.study.base.equals.stringbuffer;

/**
 * Created by liuyan on 2017/12/19.
 */
public class StringBufferStudy {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer(10);
        stringBuffer.append("12345");
        System.out.println(stringBuffer.length());
        System.out.println(stringBuffer.capacity());
        System.out.println(args.length);
        String a = args[0];
        String b = args[1];
        String c = args[2];
        String d = args[3];
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);

    }
}
