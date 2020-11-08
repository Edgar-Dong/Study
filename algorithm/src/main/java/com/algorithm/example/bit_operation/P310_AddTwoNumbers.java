package com.algorithm.example.bit_operation;

/**
 * @author:無忌
 * @date:2020/10/26
 * @description:不用加减乘除做加法
 */
class P310_AddTwoNumbers {
    public static int add(int a, int b) {
        while (b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(add(1, 2));
        System.out.println(add(5, 7));
        System.out.println(add(8, -2));
    }
}
