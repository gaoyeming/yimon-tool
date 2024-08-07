package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 盛最多水的容器
 * @date: 2024/7/25 下午9:03
 */
public class LeetCode_011_MaxArea {

    public int maxArea(int[] height) {
        if(height == null || height.length<2) {
            return 0;
        }
        int maxArea = 0;
        for (int i = 0; i < height.length-1; i++) {
            for (int j = i+1; j < height.length; j++) {
                maxArea = Math.max(maxArea, (j-i) * Math.min(height[i], height[j]));
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LeetCode_011_MaxArea maxArea = new LeetCode_011_MaxArea();
        System.out.println(maxArea.maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
