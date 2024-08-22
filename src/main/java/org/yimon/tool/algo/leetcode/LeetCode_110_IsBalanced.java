package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 平衡二叉树
 * @date: 2024/8/21 下午4:31
 */
public class LeetCode_110_IsBalanced {

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(maxDepth(root.left, 0) - maxDepth(root.right, 0)) > 1)
            return false;
        return isBalanced(root.left) && isBalanced(root.right);

    }

    private int maxDepth(TreeNode root, int level) {
        if (root == null) {
            return level;
        }
        level++;
        return Math.max(maxDepth(root.left, level), maxDepth(root.right, level));
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
