package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sequences.BinaryTreeNode;

public class TestBinaryTreeNode {
    @Test
    public void testBinaryTreeNodeCreation() {
        BinaryTreeNode<Integer> btni = new BinaryTreeNode<>(Integer.class, 256);
        assertEquals(256, btni.getItem());
    }

    @Test
    public void testBinaryTreeNodeInsertBefore() {
        BinaryTreeNode<Integer> btni1 = new BinaryTreeNode<>(Integer.class, 128);
        BinaryTreeNode<Integer> btni2 = new BinaryTreeNode<>(Integer.class, 256);

        btni2.subtreeInsertBefore(btni1);

        assertEquals(128, btni2.getLeftChild().getItem());
    }

    @Test
    public void testBinaryTreeNodeInsertAfter() {
        BinaryTreeNode<Integer> btni1 = new BinaryTreeNode<>(Integer.class, 128);
        BinaryTreeNode<Integer> btni2 = new BinaryTreeNode<>(Integer.class, 256);

        btni1.subtreeInsertAfter(btni2);

        assertEquals(256, btni1.getRightChild().getItem());
    }

    @Test
    public void testBinaryTreeInsertionLogic() {
        // NOTE: enforcment of the BST property comes from outside the node. 
        // The logic is replicated here to test the node's implementation.
        // NOTE: given any node, nodes that come before in traversal order go to the left subtree
        // while nodes that are equal to or greater than the node go to the right subtree.
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        System.out.println(root);
    }

    private void binaryTreeInsert(BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> item) {
        Integer val1 = root.getItem();
        Integer val2 = item.getItem();
        if (val2.compareTo(val1) < 0) {
            if (root.getLeftChild() != null) {
                this.binaryTreeInsert(root.getLeftChild(), item);
            } else {
                root.subtreeInsertBefore(item);
            }
        } else {
            if (root.getRightChild() != null) {
                this.binaryTreeInsert(root.getRightChild(), item);
            } else {
                root.subtreeInsertAfter(item);
            }
        }
    }
}
