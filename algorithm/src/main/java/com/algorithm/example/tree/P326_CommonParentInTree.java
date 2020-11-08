package com.algorithm.example.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author:無忌
 * @date:2020/10/19
 * @description:68.树中两个节点的最低公共祖先
 */
class P326_CommonParentInTree {
    static Map<Integer, TreeNode<Integer>> parent = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();

    /**
     * 存储父节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            } else {
                q = parent.get(q.val);
            }
        }
        return null;
    }

    public static void dfs(TreeNode<Integer> root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

}
