package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 买卖股票最佳时机Ⅱ
 * @date: 2024/8/21 下午6:39
 */
public class LeetCode_122_MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        //dp[i][j]表示第i天股票持有/卖出 手上的利润
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }
}
