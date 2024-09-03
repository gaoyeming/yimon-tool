package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ym.gao
 * @description: TODO
 * @date: 2024/9/2 下午5:03
 */
public class LeetCode_103_ZigzagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        bfs(root, result, true, 0);
        return result;
    }

    private void bfs(TreeNode root, List<List<Integer>> result, boolean left2right, int level) {
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
        if (left2right) {
            bfs(root.right, result, false, level);
            bfs(root.left, result, false, level);
        } else {
            bfs(root.left, result, true, level);
            bfs(root.right, result, true, level);
        }

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
