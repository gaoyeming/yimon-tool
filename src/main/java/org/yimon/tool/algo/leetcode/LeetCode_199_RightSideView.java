package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 二叉树的右视图
 * @date: 2024/9/2 下午6:42
 */
public class LeetCode_199_RightSideView {

    public static void main(String[] args) {
        LeetCode_199_RightSideView rightSideView = new LeetCode_199_RightSideView();
        System.out.println(rightSideView.rightSideView(new TreeNode(1, new TreeNode(2), null)));
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        dfs(root, result, 0);//最右边记录
        return result;
    }

    private void dfs(TreeNode root, List<Integer> result, int level) {
        if (root == null) {
            return;
        }
        if (result.size() - 1 < level) {
            result.add(root.val);//按层遍历
        }
        level++;//按层遍历，左右节点属于同一层
        dfs(root.right, result, level);
        dfs(root.left, result, level);
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
