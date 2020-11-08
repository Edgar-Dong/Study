package com.algorithm.example.tree;

import java.util.Stack;

/**
 * @author:無忌
 * @date:2020/10/15
 * @description:54.二叉搜索树的第k大节点
 */
class P269_KthNodeInBST {
    public static TreeNode<Integer> kthNode(TreeNode<Integer> root, int k) {
        if (root == null || k < 0) {
            return null;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = root;
        int count = 0;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                count++;
                if (count == k) {
                    return cur;
                }
                cur = cur.right;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        //       5
        //     /   \
        //    3     7
        //   / \   / \
        //  2  4  6   8
        // 2,3,4,5,6,7,8
        TreeNode<Integer> root = new TreeNode<>(5);
        root.left = new TreeNode<>(3);
        root.left.left = new TreeNode<>(2);
        root.left.right = new TreeNode<>(4);
        root.right = new TreeNode<>(7);
        root.right.left = new TreeNode<>(6);
        root.right.right = new TreeNode<>(8);
        System.out.println(kthNode(root,3).val);//4
        System.out.println(kthNode(root,6).val);//7
        System.out.println(kthNode(root,8));//null
    }
}
