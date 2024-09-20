package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 最长回文子串
 * @date: 2024/7/23 下午4:55
 */
public class LeetCode_005_LongestPalindrome {
    /*
     *暴力解法
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int left = 0; left < s.length(); left++) {
            for (int right = left + 1; right <= s.length(); right++) {
                String str = s.substring(left, right);
                if (valid(str)) {
                    longest = longest.length() > str.length() ? longest : str;
                }
            }
        }
        return longest;
    }

    private boolean valid(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /*
     * 中心扩散法
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            //从每个字符节点向左，向右，向两边进行扩散
            int left = i;
            while (left > 0) {//向左移动
                if (s.charAt(left - 1) != s.charAt(i)) {
                    break;
                }
                left--;
            }

            int right = i;
            while (right < s.length() - 1) {//向右移动
                if (s.charAt(right + 1) != s.charAt(i)) {
                    break;
                }
                right++;
            }

            while (left > 0 && right < s.length() - 1) {//两边移动
                if (s.charAt(left - 1) != s.charAt(right + 1)) {
                    break;
                }
                left--;
                right++;
            }

            if (right - left + 1 > longest.length()) {
                longest = s.substring(left, right + 1);
            }

        }
        return longest;
    }

    /*
    * 动态规划法, 其实就是暴力解法加个中间存储已经计算过的结果，从而减少判断次数
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        //记录i~j之间的字符串是否是回文字符串，并初始化值
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i]= true;
        }

        String longest = s.substring(0, 1);
        for (int left = 0; left < s.length(); left++) {
            for (int right = left+1; right < s.length(); right++) {
                //先从dp中获取是否是回文
                if (right - left + 1 > longest.length() && dp[left][right]) {
                    longest = s.substring(left, right + 1);
                } else if (right - left + 1 > longest.length()) {
                    dp[left][right] = valid(s.substring(left, right+1));
                    if (dp[left][right]) {
                        longest = s.substring(left, right + 1);
                    }
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        LeetCode_005_LongestPalindrome longestPalindrome = new LeetCode_005_LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome3("babad"));
    }

}
