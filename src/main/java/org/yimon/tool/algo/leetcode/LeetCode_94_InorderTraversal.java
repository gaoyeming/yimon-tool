package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 二叉树的中序遍历 左节点->根节点->右节点
 * @date: 2024/8/20 下午3:26
 */
public class LeetCode_94_InorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preorder(root, result);
        return result;
    }

    public void preorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        preorder(root.left, res);//左节点
        res.add(root.val);//根节点
        preorder(root.right, res);//右节点
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
