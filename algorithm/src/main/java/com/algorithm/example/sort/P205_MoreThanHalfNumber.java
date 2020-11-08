package com.algorithm.example.sort;

/**
 * @author:無忌
 * @date:2020/11/4
 * @description:39.数组中出现次过一半的数字
 */
class P205_MoreThanHalfNumber {
    public static int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums) {
            if (votes == 0) {
                x = num;
            }
            votes += ((num == x) ? 1 : -1);
        }
        return x;
    }

    public static void main(String[] args) {
        int[] data = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(majorityElement(data));
    }
}
