package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 旋转矩阵
 * @date: 2024/8/22 下午2:08
 */
public class LeetCode_Rotate {

    public void rotate(int[][] matrix) {
        if(matrix == null) {
            return;
        }
        //获取行数列数
        int m = matrix.length;
        int n = matrix[0].length;
        //先按照对折线进行旋转
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //对折线上以及左边无需无需旋转结束
                if(i <= j) {
                    continue;
                }
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //再按照垂直线旋转
        int mid = m%2 == 0 ? m/2-1 : m/2;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                //垂直线上以及左边无需无需旋转结束
                if(j <= mid) {
                    continue;
                }
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    public static void main(String[] args) {
        LeetCode_Rotate rotate = new LeetCode_Rotate();
        int [][] a = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        rotate.rotate(a);
        System.out.println(a);
    }
}
