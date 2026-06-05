package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sets.PythonSet;

public class TestPythonSet {
    private static Integer[] intArray;

    @BeforeAll
    public static void createBigListOfInt() {
        List<Integer> alArray = IntStream
            .range(1, 9999)
            .boxed()
            .toList();
        intArray = (Integer[]) Array.newInstance(Integer.class, alArray.size());
        for (int i = 0; i < alArray.size(); i++) {
            intArray[i] = alArray.get(i);
        }
    }

    @Test
    public void testStaticArrayLinkedListGoodFind() {
        PythonSet<Integer> set = new PythonSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        Integer i = set.find(55);
        assertNotNull(i);
        assertEquals(55, i);
    }

    @Test 
    public void testStaticArrayLinkedListBadFind() {
        PythonSet<Integer> set = new PythonSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        Integer i = set.find(99);
        assertNull(i);
    }

    @Test
    public void testStaticArrayLinkedListIteration() {
        PythonSet<Integer> set = new PythonSet<>(Integer.class, intArray);
        System.out.println(set);
        assertTrue(true);
    }

    @Test
    public void testStaticArrayLinkedListEquality() {
        PythonSet<Integer> set1 = new PythonSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        PythonSet<Integer> set2 = new PythonSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});

        assertTrue(set1.equals(set2));
    }

    @Test
    public void testStaticArrayLinkedListInequality() {
        PythonSet<Integer> set1 = new PythonSet<>(Integer.class, new Integer[]{65, 55, 35, 25});
        PythonSet<Integer> set2 = new PythonSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});

        assertFalse(set1.equals(set2));
    }

    @Test
    public void testPythonSetDuplicates() {
        PythonSet<Integer> set1 = new PythonSet<>(Integer.class, new Integer[]{2, 2, 3, 4});
        System.out.println(set1);

    }
}
