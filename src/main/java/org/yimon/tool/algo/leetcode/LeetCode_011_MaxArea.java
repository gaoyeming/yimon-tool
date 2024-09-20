package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 盛最多水的容器
 * @date: 2024/7/25 下午9:03
 */
public class LeetCode_011_MaxArea {

    public int maxArea(int[] height) {
        if(height == null  || height.length<2) {
            return 0;
        }
        //双指针进行处理
        int left=0, right = height.length-1;
        int max = 0;//记录最大值
        while (left<right) {
            //求面积
            int are = (right-left)*Math.min(height[left], height[right]);
            max = Math.max(max, are);

            if(height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode_011_MaxArea maxArea = new LeetCode_011_MaxArea();
        System.out.println(maxArea.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }
}
