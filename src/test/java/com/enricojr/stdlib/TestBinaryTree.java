package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.BinaryTree;

public class TestBinaryTree {
    @Test
    public void testBinaryTreeCreation() {
        Integer[] numbers = new Integer[]{12, 13, 14, 15, 16};
        BinaryTree<Integer> bti = new BinaryTree<>(Integer.class, numbers);

        System.out.println(bti);
    }

    @Test
    public void testBinaryTreeIteration() {
        Integer[] numbers = new Integer[]{12, 13, 14, 15, 16, 17, 18, 19, 20, 21};
        BinaryTree<Integer> bti = new BinaryTree<>(Integer.class, numbers);

        int prev = 0;
        for (Integer i : bti) {
            if (prev > i) { 
                fail();
            }
            prev = i; 
        }

        assertTrue(true);
    }
}
