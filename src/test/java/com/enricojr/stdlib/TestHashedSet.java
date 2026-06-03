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
import com.enricojr.stdlib.sets.HashedSet;

public class TestHashedSet {
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
        HashedSet<Integer> set = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        Integer i = set.find(55);
        assertNotNull(i);
        assertEquals(55, i);
    }

    @Test 
    public void testStaticArrayLinkedListBadFind() {
        HashedSet<Integer> set = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        Integer i = set.find(99);
        assertNull(i);
    }

    @Test
    public void testStaticArrayLinkedListIteration() {
        HashedSet<Integer> set = new HashedSet<>(Integer.class, this.intArray);
        System.out.println(set);
        assertTrue(true);
    }

    @Test
    public void testStaticArrayLinkedListEquality() {
        HashedSet<Integer> set1 = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        HashedSet<Integer> set2 = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});

        assertTrue(set1.equals(set2));
    }

    @Test
    public void testStaticArrayLinkedListInequality() {
        HashedSet<Integer> set1 = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 25});
        HashedSet<Integer> set2 = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});

        assertFalse(set1.equals(set2));
    }
}
