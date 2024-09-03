package org.yimon.tool.algo.leetcode;

import java.util.Stack;

/**
 * @author: ym.gao
 * @description: 有效括号
 * @date: 2024/9/3 上午10:52
 */
public class LeetCode_020_IsValid {

    public boolean isValid(String s) {
        if (s == null || s.length() % 2 != 0) {//奇数的情况下肯定是无效的
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char pop = stack.pop();
                if (c == ')' && pop != '(') {
                    return false;
                }
                if (c == '}' && pop != '{') {
                    return false;
                }
                if (c == ']' && pop != '[') {
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
}
