package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sets.SetArray;

public class TestSetArray {
    @Test
    public void testArrayActuallySorts() {
        SetArray<Integer> testArray = new SetArray<>(25, 43, 92, 58, 17);
        System.out.println(testArray);
        assertTrue(testArray.isSorted());
    }

    @Test
    public void testArrayFindNext() {
        SetArray<Integer> testArray = new SetArray<>(25, 43, 92, 58, 17);
        // should return 58
        int target = testArray.findNext(43);
        assertEquals(58, target);
    }

    @Test
    public void testArrayFindPrev() {
        SetArray<Integer> testArray = new SetArray<>(25, 43, 92, 58, 17);
        // should return 17
        int target = testArray.findPrev(25);
        assertEquals(17, target);
    }

    @Test
    public void testArrayFind() {
        SetArray<Integer> testArray = new SetArray<>(25, 43, 92, 58, 17);
        int target = testArray.find(92);
        assertEquals(92, target);
    }

    @Test
    public void testArrayFindMin() {
        SetArray<Integer> testArray = new SetArray<>(25, 43, 92, 58, 17);
        int target = testArray.findMin();
        assertEquals(17, target);
    }

    @Test
    public void testArrayFindMax() {
        SetArray<Integer> testArray = new SetArray<>(25, 43, 92, 58, 17);
        int target = testArray.findMax();
        assertEquals(92, target);
    }

}
