package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 找到字符串中所有字母异位词
 * @date: 2024/8/23 下午3:36
 */
public class LeetCode_438_FindAnagrams {

    public static void main(String[] args) {
        LeetCode_438_FindAnagrams findAnagrams = new LeetCode_438_FindAnagrams();
        System.out.println(findAnagrams.findAnagrams("cbaebabacd", "abc"));
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        char[] charp = p.toCharArray();
        Arrays.sort(charp);
        String taget = new String(charp);

        for (int i = 0; i < s.length() - p.length() + 1; i++) {
            char[] sourcep = s.substring(i, p.length() + i).toCharArray();
            Arrays.sort(sourcep);
            String source = new String(sourcep);
            if (source.equals(taget)) {
                result.add(i);
            }
        }

        return result;
    }
}
