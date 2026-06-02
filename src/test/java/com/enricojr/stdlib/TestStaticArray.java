package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.StaticArray;

/**
 * Unit test for simple App.
 */
public class TestStaticArray {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testStaticArrayCreation() {
        StaticArray<Integer> sai = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        assertEquals(5, sai.len());
    }

    @Test
    public void testStaticArrayGetAt() {
        StaticArray<Integer> sai = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        int i = sai.getAt(1);
        assertEquals(2, i);
    }

    @Test
    public void testStaticArraySetAt() {
        StaticArray<Integer> sai = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        sai.setAt(3, 9999);
        assertEquals(9999, sai.getAt(3));
    }

    @Test
    public void testIterator() {
        StaticArray<Integer> sai = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        for (int i : sai) {
            System.out.print("i = " + i + ", ");
        }
        System.out.println();
        assertTrue(true);
    }
}
