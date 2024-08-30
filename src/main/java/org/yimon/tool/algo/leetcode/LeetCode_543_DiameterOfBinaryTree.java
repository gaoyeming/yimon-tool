package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 二叉树的直径
 * @date: 2024/8/28 上午10:07
 */
public class LeetCode_543_DiameterOfBinaryTree {

    public static void main(String[] args) {
        LeetCode_543_DiameterOfBinaryTree diameterOfBinaryTree = new LeetCode_543_DiameterOfBinaryTree();
        System.out.println(diameterOfBinaryTree.diameterOfBinaryTree(new TreeNode(1, new TreeNode(2, new TreeNode(4), new TreeNode(5)), new TreeNode(3))));
    }

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return dfs(root.left) + dfs(root.right);
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int df = Math.max(dfs(root.left), dfs(root.right));
        return df + 1;
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
