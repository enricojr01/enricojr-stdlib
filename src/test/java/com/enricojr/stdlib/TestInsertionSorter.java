package com.enricojr.stdlib;

import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sorters.InsertionSorter;

public class TestInsertionSorter {
    @Test
    public void testInsertionSorter() {
        Integer[] numbers = new Integer[]{17, 6, 25, 32, 14, 99, 12};
        InsertionSorter<Integer> sorter = new InsertionSorter<Integer>(numbers);
        sorter.sort();

        Integer[] sortedNumbers = sorter.getInternal();
        for (Integer num : sortedNumbers) {
            System.out.print(num + ", ");
        }
    }
}
