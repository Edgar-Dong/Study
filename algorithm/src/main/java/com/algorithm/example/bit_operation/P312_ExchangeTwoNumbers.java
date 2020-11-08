package com.algorithm.example.bit_operation;

/**
 * @author:無忌
 * @date:2020/10/26
 * @description:不用新变量交换两个原有变量的值
 */
class P312_ExchangeTwoNumbers {
    public static void main(String[] args) {
        int a = 2;
        int b = 3;
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a=" + a + ", b=" + b);
    }
}
