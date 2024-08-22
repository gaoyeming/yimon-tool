package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 二叉树的最大深度
 * @date: 2024/8/21 下午3:44
 */
public class LeetCode_104_MaxDepth {

    public static void main(String[] args) {
        LeetCode_104_MaxDepth maxDepth = new LeetCode_104_MaxDepth();
        TreeNode root = new TreeNode(1, null, new TreeNode(2));
        System.out.println(maxDepth.maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return depth(root, 0);
    }

    private int depth(TreeNode root, int level) {
        if (root == null) {
            return level;
        }
        level++;
        return Math.max(depth(root.left, level), depth(root.right, level));
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
