package org.yimon.tool.algo.leetcode;

import java.util.Arrays;

/**
 * @author: ym.gao
 * @description: 轮转数组
 * @date: 2024/8/23 下午4:10
 */
public class LeetCode_189_Rotate {

    public static void main(String[] args) {
        LeetCode_189_Rotate rotate = new LeetCode_189_Rotate();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
//        rotate.reverse(nums,0, nums.length-1);
//        System.out.println(Arrays.toString(nums));
        rotate.rotate2(nums, 3);
        System.out.println(Arrays.toString(nums));
    }

    public void rotate1(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length == 1) {
            return;
        }
        int tail = nums.length - 1;
        for (int i = 0; i < k; i++) {
            //整体向后移动
            int temp = nums[tail];
            for (int j = tail - 1; j >= 0; j--) {
                nums[j + 1] = nums[j];
            }
            nums[0] = temp;
        }
    }

    public void rotate2(int[] nums, int k) {
        if (k < 1 || nums == null || nums.length == 1) {
            return;
        }
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[end];
            nums[end--] = nums[start];
            nums[start++] = temp;
        }
    }
}
