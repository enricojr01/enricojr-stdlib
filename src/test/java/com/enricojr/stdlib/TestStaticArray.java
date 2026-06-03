package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

    @Test
    public void testStaticArrayEquals() {
        StaticArray<Integer> sai1 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        StaticArray<Integer> sai2 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});

        assertTrue(sai1.equals(sai2));
    }

    @Test
    public void testStaticArrayEqualsItself() {
        StaticArray<Integer> sai1 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});

        assertTrue(sai1.equals(sai1));
    }

    @Test
    public void testStaticArrayEqualsNull() {
        StaticArray<Integer> sai1 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        assertFalse(sai1.equals(null));
    }

    @Test
    public void testStaticArrayEqualsOtherClass() {
        StaticArray<Integer> sai1 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        assertFalse(sai1.equals(new int[]{1, 2, 4, 2}));
    }

    @Test
    public void testStaticArrayNotEquals() {
        StaticArray<Integer> sai1 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        StaticArray<Integer> sai2 = new StaticArray<>(Integer.class, new Integer[]{1, 2, 3, 4});

        assertFalse(sai1.equals(sai2));
    }
}
