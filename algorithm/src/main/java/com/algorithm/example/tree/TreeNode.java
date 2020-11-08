package com.algorithm.example.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author:無忌
 * @date:2020/9/23
 * @description:树节点
 */
class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    //层序遍历
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.offer(this);
        TreeNode<T> temp;
        while (!queue.isEmpty()) {
            temp = queue.poll();
            stringBuilder.append(temp.val);
            stringBuilder.append(",");
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf(","));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
