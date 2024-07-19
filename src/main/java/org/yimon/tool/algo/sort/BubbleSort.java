package org.yimon.tool.algo.sort;

/**
 * @author: ym.gao
 * @description: 排序算法之冒泡排序
 * 工作原理：通过连续地比较与交换相邻元素实现排序。这个过程就像气泡从底部升到顶部一样，因此得名冒泡排序
 * 工作步骤：
 * 1、首先，对 n 个元素执行“冒泡”，将数组的最大元素交换至正确位置
 * 2、接下来，对剩余 n-1 个元素执行“冒泡”，将第二大元素交换至正确位置
 * 3、以此类推，经过 n-1 轮“冒泡”后，前 n-1 大的元素都被交换至正确位置
 * 4、仅剩的一个元素必定是最小元素，无须排序，因此数组排序完成
 * 参照地址：<a href="https://www.hello-algo.com/chapter_sorting/bubble_sort/">...</a>
 * 时间复杂度：O(n^2)
 * 空间复杂度：O(1)
 * @date: 2024/7/19 下午2:35
 */
public class BubbleSort {

    public static int[] bubbleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        // 外循环：未排序区间为 [0, i]
        for (int i = nums.length - 1; i > 0; i--) {
            // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    //数据交换
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        return nums;
    }
}
