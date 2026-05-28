package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class TestStaticArray {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void testStaticArrayCreation() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        assertEquals(5, sai.len());
    }

    @Test
    public void testStaticArrayGetAt() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        int i = sai.getAt(1);
        assertEquals(2, i);
    }

    @Test
    public void testStaticArraySetAt() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        sai.setAt(3, 9999);
        assertEquals(9999, sai.getAt(3));
    }

    @Test
    public void testStaticGetFirst() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        int res = sai.getFirst();
        assertEquals(1, res);
    }

    @Test
    public void testStaticGetLast() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        int res = sai.getLast();
        assertEquals(5, res);
    }

    @Test
    public void testStaticSetFirst() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        sai.setFirst(5432);
        int res = sai.getFirst();
        assertEquals(5432, res);
    }

    @Test
    public void testStaticSetLast() {
        StaticArray<Integer> sai = new StaticArray<>(1, 2, 3, 4, 5);
        sai.setLast(1234);
        int res = sai.getLast();
        assertEquals(1234, res);
    }
}
