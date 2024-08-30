package org.yimon.tool.algo.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: ym.gao
 * @description: 最长连续序列
 * @date: 2024/8/23 下午2:19
 */
public class LeetCode_128_LongestConsecutive {

    public static void main(String[] args) {
        LeetCode_128_LongestConsecutive longestConsecutive = new LeetCode_128_LongestConsecutive();
        System.out.println(longestConsecutive.longestConsecutive2(new int[]{100, 4, 200, 1, 3, 2}));
    }

    public int longestConsecutive1(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        if (nums.length == 1) {
            return 1;
        }
        Arrays.sort(nums);
        int count = 1;
        int max = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                count++;
            } else if (nums[i] - nums[i - 1] == 0) {

            } else {
                count = 1;

            }
            max = Math.max(max, count);
        }
        return max;
    }

    public int longestConsecutive2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //先去重
        Set<Integer> notRepeat = new HashSet<>();
        for (int j : nums) {
            notRepeat.add(j);
        }
        if (notRepeat.size() == 1) {
            return 1;
        }
        //遍历无重复元素集合
        int max = 0;
        for (Integer num : notRepeat) {
            int count = 1;
            int currentNum = num;
            while (notRepeat.contains(++currentNum)) {
                count++;
            }
            max = Math.max(max, count);
        }
        return max;
    }
}
