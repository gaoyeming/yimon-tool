package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 不同路径
 * @date: 2024/8/20 下午4:50
 */
public class LeetCode_062_UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m == 1 || n == 1) {//只有一行或者只有一列，这种情况下只有一种走法
            return 1;
        }
        //记录第i行第j列不同的路径数量
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
