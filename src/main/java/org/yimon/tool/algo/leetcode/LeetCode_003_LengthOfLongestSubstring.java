package org.yimon.tool.algo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ym.gao
 * @description: 无重复字符的最长字串
 * @date: 2024/7/22 下午7:38
 */
public class LeetCode_003_LengthOfLongestSubstring {

    public static void main(String[] args) {
        LeetCode_003_LengthOfLongestSubstring lengthOfLongestSubstring = new LeetCode_003_LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }
        Set<Character> charSet = new HashSet<>();
        int maxSize = 0;
        for (int right = 0; right < s.length(); right++) {
            if (right > 0) { //右指针向右移动一位，也即把charSet左右边字符去除即可
                charSet.remove(s.charAt(right - 1));
            }
            for (int left = right + charSet.size(); left < s.length(); left++) {//左指针从right + charSet.size()开始
                if (charSet.contains(s.charAt(left))) {
                    break;
                } else {
                    charSet.add(s.charAt(left));
                }
            }
            if (charSet.size() > maxSize) {
                maxSize = charSet.size();
            }
        }
        return maxSize;
    }
}
