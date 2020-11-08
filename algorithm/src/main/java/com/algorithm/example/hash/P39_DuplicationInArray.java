package com.algorithm.example.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author:無忌
 * @date:2020/10/29
 * @description:3.数组中重复的数
 * 题目要求：
 * 在一个长度为n的数组中，所有数字的取值范围都在[0,n-1]，但不知道有几个数字重复或重复几次，找出其中任意一个重复的数字。
 */
class P39_DuplicationInArray {
    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }

    public static void main(String[] args) {
        int[] data = {2,3,1,0,2,5,3};
        System.out.println(findRepeatNumber(data));

        int[] data1 = {2,3,1,0,4,5,5};
        System.out.println(findRepeatNumber(data1));
    }
}
