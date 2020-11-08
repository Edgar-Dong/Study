package com.algorithm.example.bit_operation;

/**
 * @author:無忌
 * @date:2020/10/22
 * @description:56.2.数组中唯一出现一次的数字
 * 题目要求：
 * 一个整数数组里除了一个数字出现一次，其他数字都出现两次。请找出这个数字。要求时间复杂度为o(n)，空间复杂度为o(1)。
 */
class P278_NumberAppearOnce {
    //1、a^0=a 2、a^a=0 3、a^b^a=b^a^a=b
    public static int numberOfOnce(int[] array) {
        int number = 0;
        for (int i = 0; i < array.length; i++) {
            number ^= array[i];
        }
        return number;
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 1,2,8};
        System.out.println(numberOfOnce(array));
    }
}
