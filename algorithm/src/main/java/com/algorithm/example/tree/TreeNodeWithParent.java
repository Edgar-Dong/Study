package com.algorithm.example.tree;

/**
 * @author:無忌
 * @date:2020/9/30
 * @description:带有父指针的二叉树节点
 */
class TreeNodeWithParent {
    public int val;
    public TreeNodeWithParent left;
    public TreeNodeWithParent right;
    public TreeNodeWithParent parent;

    public TreeNodeWithParent(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
