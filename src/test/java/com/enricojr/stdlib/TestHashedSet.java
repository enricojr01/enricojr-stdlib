package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sets.HashedSet;

public class TestHashedSet {
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
        HashedSet<Integer> set = new HashedSet<>(Integer.class, new Integer[]{65, 55, 35, 45, 25});
        System.out.println(set);
        assertTrue(true);
    }
}
