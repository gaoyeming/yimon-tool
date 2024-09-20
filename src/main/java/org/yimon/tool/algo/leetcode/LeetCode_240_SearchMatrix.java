package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 搜索二维矩阵Ⅱ
 * @date: 2024/8/23 下午4:55
 */
public class LeetCode_240_SearchMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length < 1) {
            return false;
        }
        //获取行数列数
        int m = matrix.length;
        int n = matrix[0].length;
        if (matrix[0][0] > target || matrix[m - 1][n - 1] < target) {
            return false;
        }
        if (matrix[0][0] == target) {
            return true;
        }
        //先获取行位置
        int mid = m / 2;
        while (mid > 0 && mid < m - 1) {
            if (matrix[mid][0] > target) {
                mid--;
            } else if (matrix[mid][n - 1] < target) {
                mid++;
            } else if (matrix[mid][0] < target && matrix[mid][n - 1] > target) {
                break;
            } else {
                return true;
            }
        }
        //定位到数据所在的行之后，再按照列进行二分
        int low = 0;
        int height = n - 1;
        while (low < height) {
            int nid = (height - low) / 2 + low;
            if (matrix[mid][nid] == target) {
                return true;
            } else if (matrix[mid][nid] < target) {
                low = mid;
            } else {
                height = mid;
            }
        }

        return false;
    }
}
