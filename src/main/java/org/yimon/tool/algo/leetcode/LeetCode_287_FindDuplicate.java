package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 寻找重复数
 * @date: 2024/9/3 上午11:27
 */
public class LeetCode_287_FindDuplicate {

    public int findDuplicate1(int[] nums) {
        if(nums == null || nums.length == 1){
            return -1;
        }
        for (int num : nums) {
            int index = Math.abs(num)-1;
            nums[index] = - nums[index];
            if(nums[index] > 0) {//大于0说明数值发生变化，从负数变成正数，代表重复
                return index+1;
            }
        }
        return -1;
    }

    public int findDuplicate2(int[] nums) {
        if(nums == null || nums.length == 1){
            return -1;
        }
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        LeetCode_287_FindDuplicate findDuplicate = new LeetCode_287_FindDuplicate();
        System.out.println(findDuplicate.findDuplicate2(new int[]{1,3,4,2,2}));
    }
}
