package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 二叉树的最近公共祖先
 * @date: 2024/9/2 下午3:27
 */
public class LeetCode_236_LowestCommonAncestor {
    public static void main(String[] args) {
        LeetCode_236_LowestCommonAncestor lowestCommonAncestor = new LeetCode_236_LowestCommonAncestor();
        TreeNode p = new TreeNode(5);
        TreeNode q = new TreeNode(1);
        System.out.println(lowestCommonAncestor.lowestCommonAncestor1(new TreeNode(3, p, q), p, q));
    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        // 1. 先看根节点是不是祖先
        if (root == null || root == p || root == q) {
            return root;
        }

        // 2. 如果根节点是祖先，有没有更近的祖先呢
        // 看看左子树
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        // 看看右子树
        TreeNode right = lowestCommonAncestor1(root.right, p, q);

        // 3. 如果有的话显然只会在一侧 判断一下
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        // 4. 如果没有更近的，默认还是返回root
        return root;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int x, TreeNode left, TreeNode right) {
            this.val = x;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
