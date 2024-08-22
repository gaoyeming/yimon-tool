package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: Z 字形变换
 * @date: 2024/7/25 上午11:26
 */
public class LeetCode_006_Convert {

    public static void main(String[] args) {
        LeetCode_006_Convert convert = new LeetCode_006_Convert();
        System.out.println(convert.convert2("PAYPALISHIRING", 3));
    }

    public String convert1(String s, int numRows) {
        if (s == null || numRows < 2 || s.length() <= numRows) {
            return s;
        }

        //排序后按照行进行结果记录
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        int row = 0;
        boolean down = false; // 方向标志，true向下移动；false向上移动
        for (int i = 0; i < s.length(); i++) {
            rows[row].append(s.charAt(i));
            if (row == 0 || row == numRows - 1) {
                down = !down;
            }
            //向下移动row+1；向上移动row-1
            row = down ? row + 1 : row - 1;
        }

        // 合并所有行，构建最终的字符串
        StringBuilder result = new StringBuilder();
        for (StringBuilder one : rows) {
            result.append(one);
        }

        return result.toString();
    }

    public String convert2(String s, int numRows) {
        if (s == null || numRows < 2 || s.length() <= numRows) {
            return s;
        }

        //排序后按照行进行结果记录
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }
        for (int i = 0; i < s.length(); ) {
            for (int j = 0; j < numRows && i < s.length(); j++) {
                rows[j].append(s.charAt(i++));
            }
            for (int j = numRows - 2; j >= 1 && i < s.length(); j--) {
                rows[j].append(s.charAt(i++));
            }
        }

        // 合并所有行，构建最终的字符串
        StringBuilder result = new StringBuilder();
        for (StringBuilder one : rows) {
            result.append(one);
        }

        return result.toString();
    }
}
