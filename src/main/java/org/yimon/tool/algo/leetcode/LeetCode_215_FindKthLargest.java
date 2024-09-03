package org.yimon.tool.algo.leetcode;

import java.util.Arrays;

/**
 * @author: ym.gao
 * @description: 数组中的第K个最大元素
 * @date: 2024/9/3 上午11:18
 */
public class LeetCode_215_FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        return nums[len - k];
    }
}
