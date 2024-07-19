package org.yimon.tool.algo.sort;

/**
 * @author: ym.gao
 * @description: 排序算法之插入排序
 * 工作原理：在未排序区间选择一个基准元素，将该元素与其左侧已排序区间的元素逐一比较大小，并将该元素插入到正确的位置
 * 工作步骤：
 * 1、初始状态下，数组的第 1 个元素已完成排序
 * 2、选取数组的第 2 个元素作为 base ，将其插入到正确位置后，数组的前 2 个元素已排序
 * 3、选取第 3 个元素作为 base ，将其插入到正确位置后，数组的前 3 个元素已排序
 * 4、以此类推，在最后一轮中，选取最后一个元素作为 base ，将其插入到正确位置后，所有元素均已排序
 * 参照地址：<a href="https://www.hello-algo.com/chapter_sorting/insertion_sort/">...</a>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * @date: 2024/7/19 下午3:06
 */
public class InsertionSort {

    public static int[] insertionSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        // 外循环：已排序区间为 [0, i-1]
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i], j = i - 1;
            // 内循环：将 base 插入到已排序区间 [0, i-1] 中的正确位置
            while (j >= 0 && nums[j] > base) {
                nums[j + 1] = nums[j]; // 将 nums[j] 向右移动一位
                j--;
            }
            nums[j + 1] = base;        // 将 base 赋值到正确位置
        }
        return nums;
    }
}
