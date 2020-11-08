package com.algorithm.example.tree;

/**
 * @author:無忌
 * @date:2020/9/30
 * @description:8.二叉树的下一个节点
 */
class P65_NextNodeInBinaryTrees {
    public static TreeNodeWithParent getNext(TreeNodeWithParent pNode) {
        if (pNode == null) {
            return null;
        } else if (pNode.right != null) {
            //当前节点的右节点不为空，找以右节点为跟节点的最左节点
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        //右节点为空，判断当前节点父节点的左子节点和当前节点是否相同
        //1、相同则当前节点的父节点即为下一个节点
        //2、否则以当前节点的父节点为新节点继续向上找新的父节点，直到相同
        while (pNode.parent != null) {
            if (pNode.parent.left == pNode) {
                return pNode.parent;
            }
            pNode = pNode.parent;
        }
        return null;
    }

    public static void main(String[] args) {
        //            1
        //          // \\
        //         2     3
        //       // \\
        //      4     5
        //    inorder->42513
        TreeNodeWithParent root = new TreeNodeWithParent(1);
        root.left = new TreeNodeWithParent(2);
        root.left.parent = root;
        root.right = new TreeNodeWithParent(3);
        root.right.parent = root;
        root.left.left = new TreeNodeWithParent(4);
        root.left.left.parent = root.left;
        root.left.right = new TreeNodeWithParent(5);
        root.left.right.parent = root.left;

        System.out.println(getNext(root.left.left).val);
        System.out.println(getNext(root.left).val);
        System.out.println(getNext(root.left.right).val);
        System.out.println(getNext(root).val);
        System.out.println(getNext(root.right));
    }
}
