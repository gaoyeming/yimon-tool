package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 整数反转
 * @date: 2024/7/25 下午5:37
 */
public class LeetCode_007_Reverse {

    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            //长度溢出判断
            if(result>Integer.MAX_VALUE/10 || result<Integer.MIN_VALUE/10) {
                return 0;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }

        return result;
    }

    public static void main(String[] args) {
        LeetCode_007_Reverse reverse = new LeetCode_007_Reverse();
        System.out.println(reverse.reverse(-12511));
    }
}

