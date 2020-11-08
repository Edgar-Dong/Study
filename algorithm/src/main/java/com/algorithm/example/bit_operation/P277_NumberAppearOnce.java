package com.algorithm.example.bit_operation;

/**
 * @author:無忌
 * @date:2020/10/22
 * @description:56.数组中只出现一次的两个数字 题目要求：
 * 一个整数数组里除了两个数字出现一次，其他数字都出现两次。请找出这两个数字。要求时间复杂度为o(n)，空间复杂度为o(1)。
 */
class P277_NumberAppearOnce {
    public static int[] findNumsAppearOnce(int[] data) {
        //1、查找分组索引
        int result = 0;
        for (int i = 0; i < data.length; i++) {
            result ^= data[i];
        }
        int indexOf1 = findFirstBit1(result);
        //2、分组
        int ret[] = new int[]{0, 0};
        if (indexOf1 < 0) {
            return ret;
        }
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & indexOf1) == 0) {
                ret[0] ^= data[i];
            } else {
                ret[1] ^= data[i];
            }
        }
        return ret;
    }

    private static int findFirstBit1(int num) {
        if (num < 0) {
            return -1;
        }
        int indexOf1 = 1;
        while (num != 0) {
            if ((num & 1) == 1) {
                return indexOf1;
            } else {
                num = num >> 1;
                indexOf1 = indexOf1 * 2;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = new int[]{2, 4, 3, 6, 3, 2, 5, 5};
        int[] result = findNumsAppearOnce(data); // 4,6
        System.out.println(result[0]);
        System.out.println(result[1]);

    }
}
