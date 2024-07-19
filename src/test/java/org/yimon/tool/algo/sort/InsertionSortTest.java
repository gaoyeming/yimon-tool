package org.yimon.tool.algo.sort;

import org.junit.Test;

import java.util.Arrays;

public class InsertionSortTest {

    @Test
    public void testInsertionSort() {
        System.out.println(Arrays.toString(InsertionSort.insertionSort(new int[]{3, 2, 4, 5, 8, 1, 7, 7})));
    }
}
