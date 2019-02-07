package com.improve.modules.java;

/**
 * Created by javakam on 2018/7/8.
 */
public class 冒泡排序 {
    public static void main(String[] args) {
        int[] a = {4, 6, 10, 1, 3, 5, 7, 9, 8, 2};
        for (int i : a) {
            System.out.print(i + " , ");
        }

        System.out.println("\n------------------------------------------------");

        int temp;
        int size = a.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {// j 就是 i+1 ，往下找，找比它小的数放到 temp 里，然后再往上放
                if (a[i] > a[j]) {
                    temp = a[j];
                    a[j] = a[i];
                    a[i] = temp;
                }
            }
        }

        for (int i : a) {
            System.out.print(i + " , ");
        }
        //TODO 2018-7-8 降序排序
    }
}
