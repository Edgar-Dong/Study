package com.algorithm.example.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author:無忌
 * @date:2020/10/12
 * @description:32.2.分行从上到下打印二叉树
 */
class P174_printTreeInLine {
    public static void printTreeInLine(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode<Integer> temp;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                temp = queue.poll();
                System.out.print(temp.val);
                System.out.print("\t");
                if (temp.left != null) {
                    queue.offer(temp.left);
                }
                if (temp.right != null) {
                    queue.offer(temp.right);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //       /  \   / \
        //      4    5 6   7
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<Integer>(4);
        root.left.right = new TreeNode<Integer>(5);
        root.right.left = new TreeNode<Integer>(6);
        root.right.right = new TreeNode<Integer>(7);
        printTreeInLine(root);
    }
}
