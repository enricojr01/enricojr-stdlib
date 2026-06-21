package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.DynamicArray;

public class TestDynamicArray {

    @Test
    public void testDynamicArrayCreate() {
        DynamicArray<Integer> dai = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4, 5}
        );
        assertEquals(5, dai.len());
    }

    @Test
    public void testDynamicArrayCreateEmpty() {
        DynamicArray<Integer> dai = new DynamicArray<>(Integer.class);
        assertEquals(16, dai.getArraySize());
    }

    @Test
    public void testDynamicArrayMinSize() {
        DynamicArray<Integer> dai = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4, 5, 6, 7}
        );
        assertEquals(16, dai.getArraySize());
    }

    @Test
    public void testDynamicArrayMinSizeAgain() {
        DynamicArray<Integer> dai = new DynamicArray<>(Integer.class, new Integer[]{1});
        assertEquals(16, dai.getArraySize());
    }

    @Test
    public void testDynamicArrayGrow() {
        DynamicArray<Integer> dai = 
            new DynamicArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        assertEquals(16, dai.getArraySize());
        dai.insertLast(17);
        assertEquals(dai.len() * 2, dai.getArraySize());
    }

    @Test
    public void testDynamicArrayShrink() {
        DynamicArray<Integer> dai = new DynamicArray<>(
            Integer.class, 
            new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18});
        assertEquals(36, dai.getArraySize());

        IntStream
            .range(0, 12)
            .forEach(x -> dai.deleteLast());

        assertEquals(((25.0f / 100.0f) * 36) * 2, dai.getArraySize());
    }

    @Test
    public void testDynamicArrayInsertAt() {
        DynamicArray<Integer> dai = new DynamicArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        dai.insertAt(3, 56);

        int res = dai.getAt(3);

        assertEquals(56, res);
        assertEquals(4, dai.getAt(4));

    }

    @Test
    public void testDynamicArrayDeleteAt() {
        DynamicArray<Integer> dai = new DynamicArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        dai.deleteAt(2);

        assertEquals(4, dai.getAt(2));
    }

    @Test
    public void testDynamicArrayGetAt() {
        DynamicArray<Integer> dai = new DynamicArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        int res = dai.getAt(2);
        assertEquals(3, res);
    }

    @Test
    public void testDynamicArrayIterator() {
        DynamicArray<Integer> dai = new DynamicArray<>(Integer.class, new Integer[]{1, 2, 3, 4, 5});
        ArrayList<Integer> results = new ArrayList<>();
        for (int i: dai) {
            System.out.print(i + ", ");
            results.add(i);
        }
        assertEquals(results.size(), dai.len());
    }

    @Test
    public void testDyanmicArrayEquals() {
        DynamicArray<Integer> dai1 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4, 5}
        );
        DynamicArray<Integer> dai2 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4, 5}
        );

        assertTrue(dai2.equals(dai1));
    }

    @Test
    public void testDynamicArrayNotEquals() {
        DynamicArray<Integer> dai1 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4}
        );
        DynamicArray<Integer> dai2 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4, 5}
        );

        assertFalse(dai2.equals(dai1));
    }

    @Test
    public void testDynamicArrayNotEqualsNull() {
        DynamicArray<Integer> dai1 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4}
        );

        assertFalse(dai1.equals(null));
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void testDynamicArrayNotEqualsOther() {
        DynamicArray<Integer> dai1 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4}
        );

        String testStr = "this is not an array";
        assertFalse(dai1.equals(testStr));
    }

    @Test
    public void testDynamicArrayInsertLast() {
        DynamicArray<Integer> dai1 = new DynamicArray<>(
            Integer.class, new Integer[]{1, 2, 3, 4}
        );

        dai1.insertLast(25);
        dai1.insertLast(27);
        dai1.insertLast(29);


        System.out.println(dai1);
    }

}
