package com.algorithm.example.tree;

import java.util.Stack;

/**
 * @author:無忌
 * @date:2020/10/13
 * @description:36.二叉搜索树与双向链表 https://www.jianshu.com/p/85874b9acb0d
 */
class P191_ConvertBinarySearchTree {
    public static TreeNode<Integer> pre = null;
    public static TreeNode<Integer> head = null;

    public static TreeNode<Integer> convert(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        inOrder(root);
        return head;
    }

    /**
     * 递归解法
     *
     * @param node
     */
    public static void inOrder(TreeNode<Integer> node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        node.left = pre;
        if (pre != null) {
            pre.right = node;
        }
        pre = node;
        if (head == null) {
            head = node;
        }
        inOrder(node.right);
    }

    /**
     * 非递归解法
     *
     * @param root
     * @return
     */
    public static TreeNode<Integer> convertWithStack(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer> headNode = null;
        TreeNode<Integer> preNode = null;
        TreeNode<Integer> curNode = root;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        while (!stack.isEmpty() || curNode != null) {
            //左子树节点入栈
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            //到了树最左边时，栈顶节点出栈
            curNode = stack.pop();
            //初始化时，将第一个节点赋值给链表头节点，并且将节点赋值给当前节点
            if (preNode == null) {
                preNode = curNode;
                headNode = curNode;
            } else {
                //双向链接
                preNode.right = curNode;
                curNode.left = preNode;
                preNode = curNode;
            }
            //遍历当前节点的右子树
            curNode = curNode.right;
        }
        return headNode;
    }
}
