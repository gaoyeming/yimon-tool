package org.yimon.tool.algo.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: ym.gao
 * @description: 无重复字符的最长字串
 * @date: 2024/7/22 下午7:38
 */
public class LeetCode_003_LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() <= 1) {
            return s.length();
        }
        Set<Character> noDup = new HashSet<>();
        int max = 0;

        for (int left = 0; left < s.length(); left++) {
            if (left > 0) {
                noDup.remove(s.charAt(left-1));
            }
            for (int right = left + noDup.size(); right < s.length(); right++) {
                if (noDup.contains(s.charAt(right))) {
                    break;
                } else {
                    noDup.add(s.charAt(right));
                }
            }
            max = Math.max(noDup.size(), max);
        }
        return max;
    }

    public static void main(String[] args) {
        LeetCode_003_LengthOfLongestSubstring lengthOfLongestSubstring = new LeetCode_003_LengthOfLongestSubstring();
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring("pwwkew"));
    }
}
