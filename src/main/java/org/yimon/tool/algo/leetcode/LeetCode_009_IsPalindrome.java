package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 字符串转证书
 * @date: 2024/7/25 下午7:10
 */
public class LeetCode_009_IsPalindrome {

    public static void main(String[] args) {
        LeetCode_009_IsPalindrome isPalindrome = new LeetCode_009_IsPalindrome();
        System.out.println(isPalindrome.isPalindrome(121));
    }

    public boolean isPalindrome(int x) {
        if (x >= 0 && x < 10) {
            return true;
        }
        if (x < 0) {
            return false;
        }

        int old = x;
        int result = 0;
        while (x != 0) {
            //长度溢出判断
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return false;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return result == old;
    }
}
