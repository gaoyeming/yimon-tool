package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 对称二叉树
 * @date: 2024/8/21 下午3:08
 */
public class LeetCode_101_IsSymmetric {
    public static void main(String[] args) {
        LeetCode_101_IsSymmetric isSymmetric = new LeetCode_101_IsSymmetric();
        TreeNode root = new TreeNode(3, new TreeNode(9, new TreeNode(8), new TreeNode(11)), new TreeNode(9, new TreeNode(11), new TreeNode(8)));
        System.out.println(isSymmetric.isSymmetric(root));
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return symmetric(root.left, root.right);
    }

    private boolean symmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return symmetric(left.left, right.right) && symmetric(left.right, right.left);
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
