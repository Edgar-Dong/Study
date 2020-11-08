package com.algorithm.example.tree;

/**
 * @author:無忌
 * @date:2020/10/19
 * @description:55.2.平衡二叉树
 */
class P273_isBalanced {
    public static boolean isBalanced(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        int diff = left - right;
        if (diff < -1 || diff > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public static int treeDepth(TreeNode<Integer> root) {
        if (root == null) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    public static boolean isBalanced2(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        int depth[] = new int[]{0};
        return isBalancedCore(root, depth);
    }

    private static boolean isBalancedCore(TreeNode<Integer> root, int[] depth) {
        if (root == null) {
            depth[0] = 0;
            return true;
        }
        int left[] = new int[]{0};
        int right[] = new int[]{0};
        if (isBalancedCore(root.left, left) && isBalancedCore(root.right, right)) {
            int diff = left[0] - right[0];
            if (diff >= -1 && diff <= 1) {
                depth[0] = 1 + (left[0] > right[0] ? left[0] : right[0]);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = new TreeNode<>(1);
        root.left = new TreeNode<>(2);
        root.left.left = new TreeNode<>(4);
        root.left.right = new TreeNode<>(5);
        root.left.right.left = new TreeNode<>(7);
        root.right = new TreeNode<>(3);
        root.right.right = new TreeNode<>(6);
        System.out.println(isBalanced(root));
        System.out.println(isBalanced2(root));
    }
}
