package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 不同路径Ⅱ
 * @date: 2024/8/20 下午5:16
 */
public class LeetCode_063_UniquePathsWithObstacles {

    public static void main(String[] args) {
        LeetCode_063_UniquePathsWithObstacles uniquePathsWithObstacles = new LeetCode_063_UniquePathsWithObstacles();
        System.out.println(uniquePathsWithObstacles.uniquePathsWithObstacles(new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}));
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        //获取行数列数
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        //特殊情况，障碍物在起点或者终点
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        //记录第i行第j列不同的路径数量
        int[][] dp = new int[m][n];
        //按行循环
        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            }
            if (obstacleGrid[i][0] == 1) {//行中存在障碍物，则障碍物位置及后面都是0，无需循环
                break;
            }
        }
        //按列循环
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            }
            if (obstacleGrid[0][i] == 1) {//列中存在障碍物，则障碍物位置及后面都是0，无需循环
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 1) { //表示障碍物就在目的地，无法到达
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
