package org.yimon.tool.algo.sort;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSortTest {

    @Test
    public void testBubbleSort() {
        System.out.println(Arrays.toString(BubbleSort.bubbleSort(new int[]{3, 2, 4, 5, 8, 1, 7, 7})));
    }
}
