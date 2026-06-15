package com.enricojr.stdlib;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.enricojr.stdlib.sets.BinaryTreeNode;

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

        assertTrue(true);
    }

    @Test
    public void testBinaryTreeNodePredecessor() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> predecessor = root.predecessor();
        assertEquals(256, predecessor.getItem());
    }

    @Test
    public void testBinaryTreeNodeSuccessor() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> successor = root.successor();
        assertEquals(1024, successor.getItem());
    }

    @Test
    public void testBinaryTreeSubtreeFirst() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> first = root.subtreeFirst();
        assertEquals(64, first.getItem());
    }

    @Test
    public void testBinaryTreeSubtreeLast() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> last = root.subtreeLast();
        assertEquals(4096, last.getItem());
    }

    @Test
    public void testBinaryTreeSubtreeNext() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> target = root.subtreeNext(64);
        assertNotNull(target);
        assertEquals(128, target.getItem());
    }

    @Test
    public void testBinaryTreeSubtreeNextFail() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> target = root.subtreeNext(4096);
        assertNull(target);
    }

    @Test
    public void testBinaryTreeSubtreePrev() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> target = root.subtreePrev(256);
        assertEquals(128, target.getItem());
    }

    @Test
    public void testBinaryTreeSubtreePrevFail() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> target = root.subtreePrev(64);
        assertNull(target);
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
