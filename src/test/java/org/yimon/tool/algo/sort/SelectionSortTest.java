package org.yimon.tool.algo.sort;

import org.junit.Test;

import java.util.Arrays;

public class SelectionSortTest {

    @Test
    public void testSelectionSort() {
        System.out.println(Arrays.toString(SelectionSort.selectionSort(new int[]{3, 2, 4, 5, 8, 1, 7, 7})));
    }
}
