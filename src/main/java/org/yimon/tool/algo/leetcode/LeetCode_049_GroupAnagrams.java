package org.yimon.tool.algo.leetcode;

import java.util.*;

/**
 * @author: ym.gao
 * @description: 字母异位词分组
 * @date: 2024/8/23 下午1:52
 */
public class LeetCode_049_GroupAnagrams {

    public static void main(String[] args) {
        LeetCode_049_GroupAnagrams groupAnagrams = new LeetCode_049_GroupAnagrams();
        System.out.println(groupAnagrams.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        if (strs == null) {
            return result;
        }
        if (strs.length == 1) {
            List<String> group = new ArrayList<>();
            group.add(strs[0]);
            result.add(group);
            return result;
        }
        Map<String, List<String>> anagrams = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String sort = new String(charArray);
            if (anagrams.get(sort) == null) {
                List<String> group = new ArrayList<>();
                group.add(str);
                anagrams.put(sort, group);
            } else {
                anagrams.get(sort).add(str);
            }
        }
        return new ArrayList<>(anagrams.values());

    }
}
