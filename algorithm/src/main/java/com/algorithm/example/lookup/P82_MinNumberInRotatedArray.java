package com.algorithm.example.lookup;

/**
 * @author:無忌
 * @date:2020/10/27
 * @description:旋转数组的最小数字
 * 题目要求：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 */
class P82_MinNumberInRotatedArray {
    public static int minArray(int array[]) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            int mid = (i + j) / 2;
            if (array[mid] > array[j]) {
                i = mid + 1;
            } else if (array[mid] < array[j]) {
                j = mid;
            } else {
                j--;
            }
        }
        return array[i];
    }

    public static void main(String[] args) {
        int[] data1 = {3,4,5,1,2};
        int[] data2 = {1,0,1,1,1};
        int[] data3 = {1,1,1,0,1};
        System.out.println(minArray(data1));
        System.out.println(minArray(data2));
        System.out.println(minArray(data3));
    }
}
