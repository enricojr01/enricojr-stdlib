package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TestLinkedList {

    @Test
    public void testLinkedListCreation() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertEquals(5, lli.len());
    }

    @Test
    public void testLinkedListInsertFirst() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.insertFirst(128);
        assertEquals(128, lli.getFirst());
    }

    @Test
    public void testLinkedListInsertLast() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.insertLast(256);
        assertEquals(256, lli.getLast());
    }

    @Test
    public void testLinkedListGetAtZero() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertEquals(1, lli.getAt(0));

    }

    @Test
    public void testLinkedListGetAtEnd() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertEquals(5, lli.getAt(4));
    }

    @Test
    public void testLinkedListGetAtOOB() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lli.getAt(5));
    }

    @Test
    public void testLinkedListInsertAt() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.insertAt(3, 1024);
        // System.out.println("testLinkedListInsertAt: " + lli);
        assertEquals(1024, lli.getAt(3));
    }
    @Test
    public void testLinkedListInsertAtOOB() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lli.insertAt(7, 123));
        lli.insertAt(3, 1024);
    }

    @Test
    public void testLinkedListDeleteFirst() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.deleteFirst();
        assertEquals(2, lli.getFirst());
        assertEquals(4, lli.len());
    }

    @Test
    public void testLinkedListDeleteLast() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.deleteLast();
        assertEquals(4, lli.getLast());
        assertEquals(4, lli.len());
    }

    @Test
    public void testLinkedListDeleteAt() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.deleteAt(3);
        assertEquals(5, lli.getAt(3));
        assertEquals(4, lli.len());
    }

    @Test
    public void testLinkedListDeleteAtOOB() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> lli.deleteAt(7));
    }

    @Test
    public void testLinkedListIteration() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        ArrayList<Integer> temp = new ArrayList<>();

        for (int item: lli) {
            temp.add(item);
        }

        // i.e temp should have 5 items
        assertEquals(5, temp.size());
    }
}
