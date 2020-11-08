package com.algorithm.example.lookup;

/**
 * @author:無忌
 * @date:2020/10/28
 * @description:53.2.0~n-1中缺失的数字
 */
class P266_GetMissingNumber {
    public static int missingNumber(int numbers[]) {
        int i = 0, j = numbers.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (numbers[mid] == mid) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{0, 1, 2, 3, 4, 5}; //6
        int[] data2 = new int[]{0, 1, 3, 4, 5}; //2
        int[] data3 = new int[]{1, 2}; //0
        System.out.println(missingNumber(data1));
        System.out.println(missingNumber(data2));
        System.out.println(missingNumber(data3));
    }
}
