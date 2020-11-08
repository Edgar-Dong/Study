package com.algorithm.example.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:無忌
 * @date:2020/10/12
 * @description:34.二叉树中和为某一值的路径
 */
class P182_FindPath {
    //用类似于前序遍历的思路解决
    public static void findPath(TreeNode<Integer> root, int exceptedSum) {
        if (root == null) {
            return;
        }
        List<Integer> path = new ArrayList<>();
        findPath(root, path, exceptedSum, 0);
    }

    private static void findPath(TreeNode<Integer> curNode, List<Integer> path, int expectedSum, int currentSum) {
        path.add(curNode.val);
        currentSum += curNode.val;
        if (curNode.left != null) {
            findPath(curNode.left, path, expectedSum, currentSum);
        }
        if (curNode.right != null) {
            findPath(curNode.right, path, expectedSum, currentSum);
        }
        if (curNode.left == null && curNode.right == null && expectedSum == currentSum) {
            System.out.println(path);
        }
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        //            10
        //          /   \
        //         5     12
        //       /  \
        //      4    7
        TreeNode<Integer> root = new TreeNode<Integer>(10);
        root.left = new TreeNode<Integer>(5);
        root.right = new TreeNode<Integer>(12);
        root.left.left = new TreeNode<Integer>(4);
        root.left.right = new TreeNode<Integer>(7);
        findPath(root, 22);
        findPath2(root, 22);
    }

    //如果二叉树的所有节点值都是大于0的（原题中并没有这个条件），可以进行剪枝。
    //如果所有节点值均大于0，可进行剪枝
    public static void findPath2(TreeNode<Integer> root, int exceptedSum) {
        if (root == null) {
            return;
        }
        List<Integer> path = new ArrayList<>();
        findPath2(root, path, exceptedSum, 0);
    }

    private static void findPath2(TreeNode<Integer> curNode, List<Integer> path, int expectedSum, int currentSum) {
        path.add(curNode.val);
        currentSum += curNode.val;
        if (currentSum < expectedSum) {
            //只有当currentSum小于exceptedSum时需要继续当前节点的子节点的遍历
            if (curNode.left != null) {
                findPath(curNode.left, path, expectedSum, currentSum);
            }
            if (curNode.right != null) {
                findPath(curNode.right, path, expectedSum, currentSum);
            }
        } else if (curNode.left == null && curNode.right == null && expectedSum == currentSum) {
            System.out.println(path);
        }
        path.remove(path.size() - 1);
    }
}