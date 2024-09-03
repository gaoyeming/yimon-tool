package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 长度最小的子数组
 * @date: 2024/9/2 上午11:35
 */
public class LeetCode_209_MinSubArrayLen {

    public static void main(String[] args) {
        LeetCode_209_MinSubArrayLen minSubArrayLen = new LeetCode_209_MinSubArrayLen();
        System.out.println(minSubArrayLen.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
    }

    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //先求总和，如果小于目标值说明不存在（同时可以判断单个值是否大于等于目标值，是的话就是1了）
        int sum = 0;
        for (int num : nums) {
            if (num >= target) {
                return 1;
            }
            sum += num;
        }
        if (sum < target) {
            return 0;
        }
        int minLength = Integer.MAX_VALUE;
        int right = 0, left = 0;
        sum = 0;
        for (; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLength != Integer.MAX_VALUE ? minLength : 0;
    }
}
