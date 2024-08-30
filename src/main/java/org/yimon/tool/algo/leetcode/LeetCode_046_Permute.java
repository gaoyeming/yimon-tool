package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 全排列
 * @date: 2024/8/22 上午10:10
 */
public class LeetCode_046_Permute {

    public static void main(String[] args) {
        LeetCode_046_Permute permute = new LeetCode_046_Permute();
        System.out.println(permute.permute(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backTracking(nums, used, path, result);
        return result;
    }

    private void backTracking(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backTracking(nums, used, path, result);
            used[i] = false;
            path.removeLast();
        }

    }
}
