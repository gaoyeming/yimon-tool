package org.yimon.tool.algo.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSortTest {

    @Test
    public void testInsertionSort() {
        System.out.println(Arrays.toString(QuickSort.quickSort(new int[]{3, 2, 4, 5, 8, 1, 7, 7})));
    }

}