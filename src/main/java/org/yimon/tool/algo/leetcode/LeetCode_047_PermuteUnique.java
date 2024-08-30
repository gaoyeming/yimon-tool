package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 全排列Ⅱ
 * @date: 2024/8/22 上午11:23
 */
public class LeetCode_047_PermuteUnique {

    public static void main(String[] args) {
        LeetCode_047_PermuteUnique permuteUnique = new LeetCode_047_PermuteUnique();
        System.out.println(permuteUnique.permuteUnique(new int[]{1, 1, 3}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }
        List<Integer> path = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backTracking(nums, used, path, result);
        return result;
    }

    private void backTracking(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

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
