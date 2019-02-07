package com.improve.modules.java;

/**
 * 位运算
 * <p>
 * Created by javakam on 2018/7/7.
 */
public class 位运算 {

    public static void main(String[] args) {
        int a = 3;// 0011
        int b = 5;// 0101
        int c = 8;// 1000
        System.out.println("a = " + Integer.toBinaryString(a));
        System.out.println("b = " + Integer.toBinaryString(b));
        System.out.println("c = " + Integer.toBinaryString(c));

        // 一、&与运算
        // 所有对应位为1，结果为1，有一个为0，结果为0
        int result = a & b;
        System.out.println("result = " + Integer.toBinaryString(result));
        // 二、|或运算
        // 有一个为1，结果为1
        result = a | b;
        System.out.println("result = " + Integer.toBinaryString(result));
        // 三、^异或
        // 相同为1，不同为0
        result = a ^ b;
        System.out.println("result = " + Integer.toBinaryString(result));

        // ----------------------------------------------------------------------------------------------------->

        // 四、左移两位
        result = a << b;
        System.out.println("result = " + Integer.toBinaryString(result));
        // 五、右移两位
        result = a >> b;
        System.out.println("result = " + Integer.toBinaryString(result));
        // 四、右移两位
        result = a >>> b;
        System.out.println("result = " + Integer.toBinaryString(result));
    }
}
