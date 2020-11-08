package com.algorithm.example.lookup;

/**
 * @author:無忌
 * @date:2020/10/27
 * @description:4.二维数组中的查找
 * 题目要求：
 * 一个二维数组中，每一行从左到右递增，每一列从上到下递增。输入一个整数，判断数组中是否含有该整数
 */
class P44_FindInPartiallySortedMatrix {
    public static boolean findInPartiallySortedMatrix(int[][] array, int target) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return false;
        }
        int rowMax = array.length - 1, colMax = array[0].length - 1;
        int rowCur = array.length - 1, colCur = 0;
        while (true) {
            if (rowCur < 0 || colCur < 0 || rowCur > rowMax || colCur > colMax) {
                return false;
            }
            if (array[rowCur][colCur] == target) {
                return true;
            } else if (array[rowCur][colCur] > target) {
                rowCur--;
            } else {
                colCur++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] data = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(findInPartiallySortedMatrix(data,10));
        System.out.println(findInPartiallySortedMatrix(data,5));
    }
}
