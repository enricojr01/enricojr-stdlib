package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class TestLinkedList {

    @Test
    public void testLinkedListCreation() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        assertEquals(5, lli.len());
        System.out.println("testLinkedListCreation: " + lli);
    }

    @Test
    public void testLinkedListInsertFirst() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.insertFirst(128);
        assertEquals(128, lli.getFirst());
        System.out.println("testLinkedListInsertFirst: " + lli);
    }

    @Test
    public void testLinkedListInsertLast() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.insertLast(256);
        assertEquals(256, lli.getLast());
        System.out.println("testLinkedListInsertLast: " + lli);
    }

    @Test
    public void testLinkedListInsertAt() {
        LinkedList<Integer> lli = new LinkedList<>(1, 2, 3, 4, 5);
        lli.insertAt(3, 1024);
        assertEquals(9999, lli.getAt(3));
        System.out.println("testLinkedListInsertAt: " + lli);
    }
}
