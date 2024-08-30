package org.yimon.tool.algo.leetcode;

import java.util.*;

/**
 * @author: ym.gao
 * @description: 三数之和
 * @date: 2024/8/30 下午4:36
 */
public class LeetCode_015_ThreeSum {

    public static void main(String[] args) {
        LeetCode_015_ThreeSum threeSum = new LeetCode_015_ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-2, 0, 1, 1, 2}));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        //记录已添加的索引，主要目的是为了去重
        Set<String> index = new HashSet<>();
        //判断元素是否重复
        Set<String> element = new HashSet<>();

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //因为是排序过的,所以有元素>0,从前往后找就找不到
            if (nums[i] > 0) break;

            int left = i;
            int right = nums.length - 1;

            while (left < right) {
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }
                if (nums[i] + nums[left] + nums[right] == 0 && !index.contains("" + i + left + right) && !element.contains("" + nums[i] + nums[left] + nums[right])) {
                    index.add("" + i + left + right);
                    element.add("" + nums[i] + nums[left] + nums[right]);
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}
