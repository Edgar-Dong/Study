package com.algorithm.example.tree;

/**
 * @author:無忌
 * @date:2020/10/15
 * @description:37.序列化二叉树
 */
class P194_SerializeBinaryTrees {
    public static String serialize(TreeNode<Integer> root) {
        if (root == null) {
            return "$,";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.val);
        stringBuilder.append(",");
        stringBuilder.append(serialize(root.left));
        stringBuilder.append(serialize(root.right));
        return stringBuilder.toString();
    }

    public static TreeNode<Integer> deserialize(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        return deserialize(stringBuilder);
    }

    private static TreeNode<Integer> deserialize(StringBuilder stringBuilder) {
        if (stringBuilder.length() == 0)
            return null;
        String num = stringBuilder.substring(0, stringBuilder.indexOf(","));
        stringBuilder.delete(0, stringBuilder.indexOf(","));
        stringBuilder.deleteCharAt(0);
        if (num.equals("$"))
            return null;
        TreeNode<Integer> node = new TreeNode<>(Integer.parseInt(num));
        node.left = deserialize(stringBuilder);
        node.right = deserialize(stringBuilder);
        return node;
    }

    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //       /      / \
        //      4      5   6
        //    1,2,4,$,$,$,3,5,$,$,6,$,$
        TreeNode<Integer> root = new TreeNode<Integer>(1);
        root.left = new TreeNode<Integer>(2);
        root.right = new TreeNode<Integer>(3);
        root.left.left = new TreeNode<Integer>(4);
        root.right.left = new TreeNode<Integer>(5);
        root.right.right = new TreeNode<Integer>(6);
        System.out.println("原始树：" + root);
        String result = serialize(root);
        System.out.println("序列化结果：" + result);
        TreeNode<Integer> deserializeRoot = deserialize(result);
        System.out.println("反序列后的树：" + deserializeRoot);
    }
}
