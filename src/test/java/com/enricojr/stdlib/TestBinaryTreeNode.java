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
    public void testBinaryTreeNodeDeepPredecessor() {
        Integer[] testArray = new Integer[]{7, 15, 39, 28, 31, 48};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 25);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> predecessor = root.predecessor();
        assertEquals(15, predecessor.getItem());
    }

    @Test
    public void testBinaryTreeNodeDeepPredecessorAgain() {
        Integer[] testArray = new Integer[]{7, 15, 39, 28, 31, 48};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 25);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> predecessor = root.subtreeFind(28).predecessor();
        assertEquals(25, predecessor.getItem());
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
    public void testBinaryTreeNodeDeepSuccessor() {
        Integer[] testArray = new Integer[]{256, 1024, 128, 64, 2048, 4096};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 512);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> newRoot = root.subtreeFind(2048);
        BinaryTreeNode<Integer> successor = newRoot.successor();
        assertEquals(4096, successor.getItem());

    }

    @Test
    public void testBinaryTreeNodeDeepSuccessorAgain() {
        Integer[] testArray = new Integer[]{7, 15, 39, 28, 31, 48};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 25);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<Integer>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        BinaryTreeNode<Integer> newRoot = root.subtreeFind(15);
        BinaryTreeNode<Integer> successor = newRoot.successor();
        assertEquals(25, successor.getItem());

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

    @Test
    public void testBinaryTreeRotateLeft() {
        Integer[] testArray = new Integer[]{4, 3, 2, 6, 11, 18, 19, 22, 9, 14, 17, 20, 12};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 7);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        System.out.println(root);
        BinaryTreeNode<Integer> target = root.subtreeFind(11);
        target.subtreeRotateLeft();
        System.out.println(root);

        BinaryTreeNode<Integer> newTarget = root.subtreeFind(18);
        assertEquals(root, newTarget.getParent());
        assertEquals(target, newTarget.getLeftChild());
    }

    @Test
    public void testBinaryTreeRotateLeftOnRoot() {
        Integer[] testArray = new Integer[]{4, 3, 2, 6, 11, 18, 19, 22, 9, 14, 17, 20, 12};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 7);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        // System.out.println(root);
        // NOTE: following a rotate the root is no longer a root but a subtree instead.
        // this is probably why most tree data structures keep an explicit reference to the root,
        // and make sure to update it as we go along.
        root.subtreeRotateLeft();
        // System.out.println(root.getParent());

        BinaryTreeNode<Integer> newTarget = root.getParent();
        assertEquals(null, newTarget.getParent());
        assertEquals(root, newTarget.getLeftChild());
    }

    @Test
    public void testBinaryTreeRotateRight() {
        Integer[] testArray = new Integer[]{4, 3, 2, 6, 11, 18, 19, 22, 9, 14, 17, 20, 12};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 7);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<>(Integer.class, i);
            this.binaryTreeInsert(root, value);
        }

        System.out.println(root);
        BinaryTreeNode<Integer> target = root.subtreeFind(11);
        target.subtreeRotateRight();
        System.out.println(root);

        BinaryTreeNode<Integer> newTarget = root.subtreeFind(9);
        assertEquals(root, newTarget.getParent());
        assertEquals(target, newTarget.getRightChild());
    }

    @Test
    public void testBinaryTreeBalancing() {
        Integer[] testArray = new Integer[]{4, 3, 2, 6, 11, 18, 19, 22, 9, 14, 17, 20, 12};
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(Integer.class, 7);

        for (Integer i : testArray) {
            BinaryTreeNode<Integer> value = new BinaryTreeNode<>(Integer.class, i);
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
