package org.yimon.tool.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ym.gao
 * @description: 两数之和
 * @date: 2024/7/22 下午5:27
 */
public class LeetCode_001_TwoSum {

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(targetMap.get(target-nums[i]) != null) {
                return new int[]{targetMap.get(target-nums[i]), i};
            } else {
                targetMap.put(nums[i], i);
            }
        }
        return new int[]{-1, -1};
    }
}
