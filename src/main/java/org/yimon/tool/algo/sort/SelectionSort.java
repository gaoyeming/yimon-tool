package org.yimon.tool.algo.sort;

/**
 * @author: ym.gao
 * @description: 排序算法之选择排序
 * 工作原理：开启一个循环，每轮从未排序区间选择最小的元素，将其放到已排序区间的末尾
 * 工作步骤：
 * 1、初始状态下，所有元素未排序，即未排序（索引）区间为[0,n-1]
 * 2、选取区间[0,n-1]中的最小元素，将其与索引0处的元素交换。完成后，数组前 1 个元素已排序
 * 3、选取区间[1,n-1]中的最小元素，将其与索引1处的元素交换。完成后，数组前 2 个元素已排序
 * 4、以此类推。经过 n-1轮选择与交换后，数组前 n-1个元素已排序
 * 5、仅剩的一个元素必定是最大元素，无须排序，因此数组排序完成
 * 参照地址：<a href="https://www.hello-algo.com/chapter_sorting/selection_sort/">...</a>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * @date: 2024/7/19 下午2:10
 */
public class SelectionSort {

    public static int[] selectionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        //外排序,[0, n-1) 最后一位无需比较
        for (int i = 0; i < nums.length - 1; i++) {
            // 内循环：找到未排序区间内的最小元素
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            //数据交换
            int temp = nums[i];
            nums[i] = nums[minIndex];
            nums[minIndex] = temp;
        }
        return nums;
    }
}
