package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 最长回文子串
 * @date: 2024/7/23 下午4:55
 */
public class LeetCode_005_LongestPalindrome {

    public static void main(String[] args) {
        LeetCode_005_LongestPalindrome longestPalindrome = new LeetCode_005_LongestPalindrome();
        System.out.println(longestPalindrome.longestPalindrome3("cbbd"));
    }

    /*
    中心扩散法
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            int rightIndex = i;
            int leftIndex = i;
            //向右偏移
            while (rightIndex < s.length() - 1) {
                if (s.charAt(i) == s.charAt(rightIndex + 1)) {
                    rightIndex++;
                } else {
                    //不匹配直接跳出循环
                    break;
                }
            }
            //向左偏移
            while (leftIndex > 0) {
                if (s.charAt(leftIndex - 1) == s.charAt(i)) {
                    leftIndex--;
                } else {
                    //不匹配直接跳出循环
                    break;
                }
            }
            //向两边偏移
            while (leftIndex > 0 && rightIndex < s.length() - 1) {
                if (s.charAt(leftIndex - 1) == s.charAt(rightIndex + 1)) {
                    rightIndex++;
                    leftIndex--;
                } else {
                    //不匹配直接跳出循环
                    break;
                }
            }
            //进行判断获取最大长度
            String plain = s.substring(leftIndex, rightIndex + 1);
            if (plain.length() > longest.length()) {
                longest = plain;
            }
        }
        return longest;
    }

    /*
    暴力解法
     */
    public String longestPalindrome2(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int left = 0; left < s.length() - 1; left++) {
            for (int right = 1; right < s.length(); right++) {
                if (right - left + 1 > longest.length() && valid(s, left, right)) { //当前字符串长度超过已知最大回文字符串长度，则进行回文判断
                    longest = s.substring(left, right + 1);
                }
            }
        }
        return longest;
    }

    /*
    动态规划法, 其实就是暴力解法加个中间存储已经计算过的结果，从而减少判断次数
     */
    public String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }
        boolean[][] dp = new boolean[s.length()][s.length()];
        String longest = s.substring(0, 1);
        for (int left = 0; left < s.length() - 1; left++) {
            for (int right = 1; right < s.length(); right++) {
                //先从dp中获取是否是回文
                if (right - left + 1 > longest.length() && dp[left][right]) {
                    longest = s.substring(left, right + 1);
                } else if (right - left + 1 > longest.length()) {
                    dp[left][right] = valid(s, left, right);
                    if (dp[left][right]) {
                        longest = s.substring(left, right + 1);
                    }
                }
            }
        }
        return longest;
    }

    /**
     * 判断字符串对应左右位置是否是 回文字符串
     *
     * @param s     需要判断的字符串
     * @param left  左边索引位置
     * @param right 右边索引位置
     * @return 是否是回文字符串
     */
    private boolean valid(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
