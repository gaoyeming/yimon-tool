package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 二叉树的层序遍历
 * @date: 2024/8/20 下午3:45
 */
public class LeetCode_102_LevelOrder {

    public static void main(String[] args) {
        LeetCode_102_LevelOrder levelOrder = new LeetCode_102_LevelOrder();
        TreeNode root = new TreeNode(3, new TreeNode(9, new TreeNode(8), new TreeNode(11)), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(levelOrder.levelOrder(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        preorder(root, result, 0);
        return result;
    }

    public void preorder(TreeNode root, List<List<Integer>> result, int level) {
        if (root == null) {
            return;
        }
        if (result.size() - 1 < level) {
            List<Integer> levelResult = new ArrayList<>();
            levelResult.add(root.val);//按层遍历
            result.add(levelResult);
        } else {
            result.get(level).add(root.val);
        }
        level++;//按层遍历，左右节点属于同一层
        preorder(root.left, result, level);
        preorder(root.right, result, level);
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
