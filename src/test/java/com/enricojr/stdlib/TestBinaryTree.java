package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sets.BinarySearchTree;

public class TestBinaryTree {
    @Test
    public void testBinaryTreeCreation() {
        Integer[] numbers = new Integer[]{12, 13, 14, 15, 16};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);

        System.out.println(bti);
    }

    @Test
    public void testBinaryTreeIteration() {
        Integer[] numbers = new Integer[]{12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);

        int prev = 0;
        for (Integer i : bti) {
            if (prev > i) { 
                fail();
            }
            prev = i; 
        }

        assertTrue(true);
    }

    @Test
    public void testBinaryTreeFindFail() {
        Integer[] numbers = new Integer[]{12, 13, 14};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);

        Integer i = bti.find(29);
        assertNull(i);
    }

    @Test
    public void testBinaryTreeFindSuccess() {
        Integer[] numbers = new Integer[]{12, 13, 14, 15, 16, 17, 18};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);

        Integer i = bti.find(17);
        assertEquals(17, i);
    }

    @Test
    public void testBinaryTreeFindPrevious() {
        Integer[] numbers = new Integer[]{15, 12, 22, 34, 26, 55};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);
        Integer i = bti.findPrev(34);
        assertEquals(26, i);
    }

    @Test
    public void testBinaryTreeFindPreviousFail() {
        Integer[] numbers = new Integer[]{15, 12, 22, 34, 26, 55};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);
        Integer i = bti.findPrev(12);
        assertNull(i);
    }

    @Test
    public void testBinaryTreeFindNext() {
        Integer[] numbers = new Integer[]{15, 12, 22, 34, 26, 55};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);
        Integer i = bti.findNext(34);
        assertEquals(55, i);
    }

    @Test
    public void testBinaryTreeFindNextFail() {
        Integer[] numbers = new Integer[]{15, 12, 22, 34, 26, 55};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);
        Integer i = bti.findNext(55);
        System.out.println(bti);
        assertEquals(9999, i);
    }

    @Test
    public void testBinaryTreeDelete() {
        Integer[] numbers = new Integer[]{49, 22, 48, 35, 16};
        BinarySearchTree<Integer> bti = new BinarySearchTree<>(Integer.class, numbers);
        Integer i = bti.delete(22);
        assertEquals(22, i);
        assertNull(bti.find(22));
    }
}

