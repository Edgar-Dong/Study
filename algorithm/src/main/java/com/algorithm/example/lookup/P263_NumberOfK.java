package com.algorithm.example.lookup;

/**
 * @author:無忌
 * @date:2020/10/28
 * @description:53.数字在排序数组中出现的次数
 */
class P263_NumberOfK {
    public static int getNumberOfK(int[] data, int k) {
        int i = 0, j = data.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (data[mid] <= k) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        int right = i;
        if (j != 0 && data[j] != k) {
            return 0;
        }
        i = 0;
        j = data.length - 1;
        while (i <= j) {
            int mid = (i + j) / 2;
            if (data[mid] < k) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        int left = j;
        return right - left - 1;
    }

    public static void main(String[] args) {
        int[] data1 = new int[]{1, 2, 3, 3, 3, 3, 5, 6};
        int[] data2 = new int[]{1, 2, 3, 3, 3, 3, 4, 5};
        int[] data3 = new int[]{3, 3, 3, 3, 3, 3, 3, 3};
        System.out.println(getNumberOfK(data1, 4));//0
        System.out.println(getNumberOfK(data2, 3));//4
        System.out.println(getNumberOfK(data3, 3));//8
    }
}
