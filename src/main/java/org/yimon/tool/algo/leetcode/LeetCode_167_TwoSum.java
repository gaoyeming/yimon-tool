package org.yimon.tool.algo.leetcode;

import java.util.Arrays;

/**
 * @author: ym.gao
 * @description: 两数之和Ⅱ-输入有序数组
 * @date: 2024/8/29 下午7:33
 */
public class LeetCode_167_TwoSum {

    public static void main(String[] args) {
        LeetCode_167_TwoSum twoSum = new LeetCode_167_TwoSum();
        System.out.println(Arrays.toString(twoSum.twoSum(new int[]{3, 24, 50, 79, 88, 150, 345}, 200)));
    }

    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length == 1) {
            return new int[]{-1, -1};
        }
        int left = 1, right = numbers.length;
        while (left < right) {
            if (numbers[left - 1] + numbers[right - 1] == target) {
                return new int[]{left, right};
            } else if (numbers[left - 1] + numbers[right - 1] < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{-1, -1};
    }
}
