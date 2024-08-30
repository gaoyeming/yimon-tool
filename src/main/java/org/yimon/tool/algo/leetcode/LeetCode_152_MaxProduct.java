package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 乘积最大子数组
 * @date: 2024/8/29 下午6:38
 */
public class LeetCode_152_MaxProduct {

    public static void main(String[] args) {
        LeetCode_152_MaxProduct maxProduct = new LeetCode_152_MaxProduct();
        System.out.println(maxProduct.maxProduct(new int[]{2, -1, 1, 1}));
    }

    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int max = nums[0];
        //分别记录最大值与最小值（因为有负数的情况）
        int[] maxDp = new int[nums.length], minDp = new int[nums.length];
        maxDp[0] = minDp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxDp[i] = Math.max(nums[i], Math.max(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            minDp[i] = Math.min(nums[i], Math.min(maxDp[i - 1] * nums[i], minDp[i - 1] * nums[i]));
            max = Math.max(max, maxDp[i]);
        }
        return max;
    }
}
