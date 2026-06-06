package com.enricojr.stdlib;

import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.BinaryTree;

public class TestBinaryTree {
    @Test
    public void testBinaryTreeCreation() {
        Integer[] numbers = new Integer[]{12, 13, 14, 15, 16};
        BinaryTree<Integer> bti = new BinaryTree<>(Integer.class, numbers);

        System.out.println(bti);
    }
}
