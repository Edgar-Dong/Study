package com.algorithm.example.tree;

import java.util.List;

/**
 * @author:無忌
 * @date:2020/9/27
 * @description:7.重建二叉树
 */
class P62_ConstructBinaryTree {
    public static TreeNode construct(int[] preOrder, int[] inOrder) {
        if (preOrder == null || inOrder == null || preOrder.length == 0 || inOrder.length == 0) {
            return null;
        }
        return constructCore(preOrder,0,inOrder,0,preOrder.length);
    }

    private static TreeNode constructCore(int[] preOrder, int preOderStart, int[] inOrder, int inOrderStart, int length) {
        if (length == 0) {
            return null;
        }
        int inorderIndex = -1;
        for (int i = inOrderStart; i < inOrderStart + length; i++) {
            if (inOrder[i] == preOrder[preOderStart]) {
                inorderIndex = i;
                break;
            }
        }
        int leftLength = inorderIndex - inOrderStart;
        TreeNode node = new TreeNode(preOrder[preOderStart]);
        node.left = constructCore(preOrder, preOderStart + 1, inOrder, inOrderStart, leftLength);
        node.right = constructCore(preOrder, preOderStart + leftLength + 1, inOrder, inorderIndex + 1, length - leftLength - 1);
        return node;
    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //        / \
        //       4   5
        //pre->12453  in->42513   post->45231
        int[] pre = {1, 2, 4, 5, 3};
        int[] in = {4, 2, 5, 1, 3};
        TreeNode root = construct(pre, in);
        //对重建后的树,进行前中后序遍历，验证是否重建正确
        //调用的重建函数见：http://www.jianshu.com/p/362d4ff42ab2
        List<Integer> preorder = P60_TraversalOfBinaryTree.preorderIteratively(root);
        List<Integer> inorder = P60_TraversalOfBinaryTree.inorderIteratively(root);
        List<Integer> postorder = P60_TraversalOfBinaryTree.postorderIteratively(root);
        System.out.println(preorder);
        System.out.println(inorder);
        System.out.println(postorder);
    }
}
