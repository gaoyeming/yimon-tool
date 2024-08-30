package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 买卖股票的最佳时机
 * @date: 2024/8/21 下午6:23
 */
public class LeetCode_121_MaxProfit {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 1) {
            return 0;
        }
        int minBuy = prices[0];
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < minBuy) {
                minBuy = prices[i];
            }
            maxProfit = Math.max(prices[i] - minBuy, maxProfit);
        }
        return Math.max(maxProfit, 0);

    }
}
