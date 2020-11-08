package com.algorithm.example.tree;

/**
 * @author:無忌
 * @date:2020/10/12
 * @description:33.二叉搜索树的后序遍历
 */
class P179_SequenceOfBST {
    public static boolean verifySquenceOfBST(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }
        return verifySquenceOfBST(data, 0, data.length - 1);
    }

    private static boolean verifySquenceOfBST(int[] data, int start, int end) {
        if (end - start <= 1) {
            return true;
        }
        int root = data[end];
        int rightStart = 0;
        for (int i = 0; i < end; i++) {
            if (data[i] > root) {
                rightStart = i;
                break;
            }
        }
        for (int i = rightStart; i < end; i++) {
            if (data[i] < root) {
                return false;
            }
        }
        return verifySquenceOfBST(data, start, rightStart - 1) && verifySquenceOfBST(data, rightStart, end - 1);
    }

    public static void main(String[] args) {
        //            8
        //          /   \
        //         6     10
        //       /  \   / \
        //      5    7 9   11
        int[] data = {5, 7, 6, 9, 4, 10, 8};
        int[] data1 = {5, 7, 6, 9, 11, 10, 8};
        System.out.println(verifySquenceOfBST(data));
        System.out.println(verifySquenceOfBST(data1));
    }
}
