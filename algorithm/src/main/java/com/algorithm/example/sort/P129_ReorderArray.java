package com.algorithm.example.sort;

import java.util.Arrays;

/**
 * @author:無忌
 * @date:2020/11/4
 * @description:21.使数组中奇数位于偶数前面
 */
class P129_ReorderArray {
    public static void reorder(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        int left = 0, right = array.length - 1;
        while (left < right) {
            while (left < right && !isEven(array[left])) {
                left++;
            }
            while (left < right && isEven(array[right])) {
                right--;
            }
            if (left < right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
            }
        }
    }

    private static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,7,7};
        System.out.println("orign data:" + Arrays.toString(data));
        reorder(data);
        System.out.println("reorder data:" + Arrays.toString(data));
    }
}
