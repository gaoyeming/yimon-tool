package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 最小路径和
 * @date: 2024/9/2 下午5:28
 */
public class LeetCode_064_MinPathSum {

    public static void main(String[] args) {
        LeetCode_064_MinPathSum minPathSum = new LeetCode_064_MinPathSum();
        System.out.println(minPathSum.minPathSum(new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}));
    }

    public int minPathSum(int[][] grid) {
        if (grid == null) {
            return 0;
        }
        //获取行列
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        //只有一行
        if (m == 1) {
            return dp[0][n - 1];
        }
        //只有一列
        if (n == 1) {
            return dp[m - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
            }
        }
        return dp[m - 1][n - 1];
    }
}
