package com.algorithm.example.bit_operation;

/**
 * @author:無忌
 * @date:2020/10/22
 * @description:15.二进制中1的个数
 */
class P100_NumberOf1InBinary {
    public static int numberOfOne(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag <<= 1;
        }
        return count;
    }

    public static int numberOfOne2(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        int a = 11;
        String binaryStr = Integer.toBinaryString(a);
        System.out.println(binaryStr);
        System.out.println(numberOfOne(a));
        System.out.println(numberOfOne2(a));
    }
}
