package org.yimon.tool.algo.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ym.gao
 * @description: 括号生成
 * @date: 2024/9/2 下午6:35
 */
public class LeetCode_022_GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result;
        }
        if (n == 1) {
            result.add("()");
        } else {
            backtrack(result, new StringBuilder(),0, 0, n);
        }
        return result;
    }

    private void backtrack(List<String> ans, StringBuilder cur, int left, int right, int max) {
        if (cur.length() == 2 * max) {
            ans.add(cur.toString());
            return;
        }
        if (left < max) {
            cur.append('(');
            backtrack(ans, cur, left + 1, right, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (right < left) {
            cur.append(')');
            backtrack(ans, cur, left, right + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

    public static void main(String[] args) {
        LeetCode_022_GenerateParenthesis generateParenthesis = new LeetCode_022_GenerateParenthesis();
        System.out.println(generateParenthesis.generateParenthesis(3));
    }
}
