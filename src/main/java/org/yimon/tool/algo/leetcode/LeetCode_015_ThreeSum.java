package org.yimon.tool.algo.leetcode;

import java.util.*;

/**
 * @author: ym.gao
 * @description: 三数之和
 * @date: 2024/8/30 下午4:36
 */
public class LeetCode_015_ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums == null || nums.length<3){
            return result;
        }
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-1; i++) {
            int left = i+1;
            int right = nums.length-1;
            while (left<right) {
                if(nums[left] + nums[right] + nums[i] == 0) {
                    List<Integer> one = new ArrayList<>();
                    one.add(nums[i]);
                    one.add(nums[left]);
                    one.add(nums[right]);
                    result.add(one);
                } else if(nums[left] + nums[right] + nums[i] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        LeetCode_015_ThreeSum threeSum = new LeetCode_015_ThreeSum();
        System.out.println(threeSum.threeSum(new int[]{-1,0,1,2,-1,-4}));
    }
}
