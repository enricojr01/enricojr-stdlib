package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.LinkedList;

public class TestLinkedList {

    @Test
    public void testLinkedListCreation() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        assertEquals(5, lli.len());
    }

    @Test
    public void testLinkedListGetAtZero() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        assertEquals(1, lli.getAt(0));

    }

    @Test
    public void testLinkedListGetAtEnd() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        assertEquals(5, lli.getAt(4));
    }

    @Test
    public void testLinkedListGetAtOOB() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lli.getAt(5));
    }

    @Test
    public void testLinkedListInsertAt() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        lli.insertAt(3, 1024);
        // System.out.println("testLinkedListInsertAt: " + lli);
        assertEquals(1024, lli.getAt(3));
    }
    @Test
    public void testLinkedListInsertAtOOB() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lli.insertAt(7, 123));
        lli.insertAt(3, 1024);
    }

    @Test
    public void testLinkedListDeleteAt() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        lli.deleteAt(3);
        assertEquals(5, lli.getAt(3));
        assertEquals(4, lli.len());
    }

    @Test
    public void testLinkedListDeleteAtOOB() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lli.deleteAt(7));
    }

    @Test
    public void testLinkedListIteration() {
        LinkedList<Integer> lli = new LinkedList<>(new Integer[]{1, 2, 3, 4, 5});
        ArrayList<Integer> temp = new ArrayList<>();

        for (int item: lli) {
            temp.add(item);
        }

        // i.e temp should have 5 items
        assertEquals(5, temp.size());
    }

    @Test
    public void testLinkedListFromScratch() {
        LinkedList<Integer> lli = new LinkedList<>();
        IntStream.range(1, 15).forEach(a -> lli.insertLast(a));

        assertEquals(1, lli.getFirst());
        assertEquals(3, lli.getAt(2));
        assertEquals(14, lli.getLast());
    }

    @Test
    public void testLinkedListInsertFirstEmpty() {
        LinkedList<Integer> lli = new LinkedList<>();
        lli.insertFirst(324);
        assertEquals(324, lli.getFirst());
        assertEquals(324, lli.getLast());
    }

    @Test
    public void testLinkedListInsertLastEmpty() {
        LinkedList<Integer> lli = new LinkedList<>();
        lli.insertLast(123);
        lli.insertLast(514);
        lli.insertLast(3932);
        lli.insertLast(2345);
        assertEquals(2345, lli.getLast());
        assertEquals(123, lli.getFirst());
        assertEquals(514, lli.getAt(1));
    }

    @Test
    public void testLinkedListInsertFirstRepeatedly() {
        LinkedList<Integer> lli = new LinkedList<>();
        // list should look like 999, 500, 424;
        lli.insertFirst(424);
        lli.insertFirst(500);
        lli.insertFirst(999);
        assertEquals(999, lli.getFirst());
        assertEquals(500, lli.getAt(1));
        assertEquals(424, lli.getLast());
    }

    @Test
    public void testLinkedListEquality() {
        LinkedList<Integer> lli1 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 24, 99});
        LinkedList<Integer> lli2 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 24, 99});

        assertEquals(lli2, lli1);
    }

    @Test
    public void testLinkedListInequality() {
        LinkedList<Integer> lli1 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 24, 99});
        LinkedList<Integer> lli2 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 55});

        assertNotEquals(lli1, lli2);
    }

    @Test
    public void testLinkedListHashCodeInequality() {
        LinkedList<Integer> lli1 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 24, 99});
        LinkedList<Integer> lli2 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 55});

        assertNotEquals(lli1.hashCode(), lli2.hashCode());

    }

    @Test
    public void testLinkedListHashCodeEquality() {
        LinkedList<Integer> lli1 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 24, 99});
        LinkedList<Integer> lli2 = new LinkedList<>(new Integer[]{12, 34, 55, 64, 24, 99});

        assertEquals(lli2.hashCode(), lli1.hashCode());
    }
}
