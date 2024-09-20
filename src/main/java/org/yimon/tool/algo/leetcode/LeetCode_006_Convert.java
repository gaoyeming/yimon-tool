package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: Z 字形变换
 * @date: 2024/7/25 上午11:26
 */
public class LeetCode_006_Convert {

    public String convert1(String s, int numRows) {
        if(s == null ||  s.length()<=numRows || numRows ==1){
            return s;
        }
        StringBuilder[] rows = new StringBuilder[numRows];//记录每一行字符串并初始化值
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();//初始化每个行
        }
        //当前需要写入的行
        int row = 0;
        //当前是否向下写入，true为向下写 行+1；false向上写 行-1
        boolean down = false;
        for (int i = 0; i < s.length(); i++) {
            rows[row].append(s.charAt(i));
            if(row == 0 || row==numRows-1) {//当写入到第一行或者最后一行的时候进行转向
                down = !down;
            }
            row = down ? row+1 : row-1;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder r: rows) {
            result.append(r);
        }
        return result.toString();
    }


    public static void main(String[] args) {
        LeetCode_006_Convert convert = new LeetCode_006_Convert();
        System.out.println(convert.convert1("PAYPALISHIRING", 3));
    }
}
