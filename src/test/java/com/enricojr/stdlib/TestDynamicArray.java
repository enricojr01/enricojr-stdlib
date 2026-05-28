package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class TestDynamicArray {

    @Test
    public void testDynamicArrayCreate() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        assertEquals(5, dai.len());
        assertEquals(8, dai.getArraySize());
        System.out.println("testDynamicArrayCreate: " + dai);
    }

    @Test
    public void testMinSize8() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5, 6, 7);
        assertEquals(8,  dai.getArraySize());
        System.out.println("testMinSize8: " + dai);
    }

    @Test
    public void testMinSize8Again() {
        DynamicArray<Integer> dai = new DynamicArray<>(1);
        assertEquals(8,  dai.getArraySize());
        System.out.println("testMinSize8Again: " + dai);
    }

    @Test
    public void testGrow() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5, 6, 7);
        assertEquals(8, dai.getArraySize());

        dai.insertLast(8);
        assertEquals(16, dai.getArraySize());
        System.out.println("testGrow: " + dai);
    }

    @Test
    public void testShrink() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(16, dai.getArraySize());

        // DynamicArray should shrink when itemCount is 25% of arraySize.
        // This means when I get it to 4 items it should shrink to 8.

        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();

        assertEquals(8, dai.getArraySize());
        assertEquals(4, dai.len());
        System.out.println("testShrink: " + dai);
    }

    @Test
    public void testShrink2() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5, 6, 7, 8, 9);
        assertEquals(16, dai.getArraySize());

        // DynamicArray should shrink when itemCount is 25% of arraySize.
        // This means at 5 items, arraySize should still be 16.

        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();
        dai.deleteLast();

        assertEquals(16, dai.getArraySize());
        assertEquals(5, dai.len());
        System.out.println("testShrink2: " + dai);
    }

    @Test
    public void testDynamicArrayInsertFirst() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        dai.insertFirst(57);

        assertEquals(57, dai.getFirst());
        assertEquals(5, dai.getLast());
        System.out.println("testDynamicArrayInsertFirst: " + dai);
    }

    @Test
    public void testDynamicArrayInsertLast() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        dai.insertLast(225);

        assertEquals(225, dai.getLast());
        assertEquals(1, dai.getFirst());
        System.out.println("testDynamicArrayInsertLast: " + dai);
    }

    @Test
    public void testDynamicArrayDeleteAt() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        dai.deleteAt(2);

        assertEquals(4, dai.getAt(2));
        System.out.println("testDynamicArrayDeleteAt: " + dai);
    }

    @Test
    public void testDynamicArrayGetFirst() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        int res = dai.getFirst();

        assertEquals(1, res);
        System.out.println("testDynamicArrayGetFirst: " + dai);
    }

    @Test
    public void testDynamicArrayGetLast() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        int res = dai.getLast();

        assertEquals(5, res);
        System.out.println("testDynamicArrayGetLast: " + dai);
    }

    @Test
    public void testDynamicArrayIterator() {
        DynamicArray<Integer> dai = new DynamicArray<>(1, 2, 3, 4, 5);
        System.out.print("testDynamicArrayIterator: ");
        for (int i : dai) {
            System.out.print("i = " + i + ", ");
        }
        System.out.println();
        assertTrue(true);
    }
}
