package com.enricojr.stdlib.sets;

import java.util.Iterator;
import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.iterators.BinaryTreeIterator;
import com.enricojr.stdlib.sequences.StaticArray;
import com.enricojr.stdlib.sorters.InsertionSorter;

public class BinarySearchTree<T extends Comparable<T>> implements SetInterface<T>, Iterable<T> {
    // NOTE: The root of the tree lacks a parent.
    // NOTE: A node having no children is a leaf.
    // NOTE: Should I consider differentiating between a simple binary tree that doesn't impose 
    // order and one that does? 
    private BinaryTreeNode<T> root;
    private Class<T> internalType;
    private int itemCount;

    public BinarySearchTree(Class<T> type, T item) {
        this.root = new BinaryTreeNode<T>(type, item);
        this.internalType = type;
    }

    public BinarySearchTree(Class<T> type, T[] items) {
        this.internalType = type;

        // NOTE: Step 1 - find the mean of the array
        // TODO: this could be better optimized, maybe consider adding a sorter to the base array
        // TODO: rotations can change the root and I need a way to keep track of those changes.
        StaticArray<T> temp = new StaticArray<>(type, items);
        InsertionSorter<T> is = new InsertionSorter<>(items);
        is.sort();
        StaticArray<T> sorted = new StaticArray<>(type, is.getInternal());

        // NOTE: Step 2 - extract the middle element and make it the root.
        int middleIdx = Math.floorDiv(sorted.len(), 2);
        BinaryTreeNode<T> midElement = new BinaryTreeNode<T>(type, sorted.getAt(middleIdx));
        this.root = midElement;

        for (int i = 0; i < sorted.len(); i++) {
            if (i == middleIdx) {
                continue;
            } else {
                this.insert(sorted.getAt(i));
            }
        }
    }

    public Iterator<T> iterator() { 
        return new BinaryTreeIterator<T>(this.internalType, this.root);
    }

    // GETTERS AND SETTERS
    public BinaryTreeNode<T> getRoot() {
        return this.root;
    }

    public Class<T> getInternalType() {
        return this.internalType;
    }

    // SET INTERFACE METHODS
    @Override
    public T delete(T item) {
        BinaryTreeNode<T> node = this.root.subtreeFind(item);
        BinaryTreeNode<T> result = null;
        if (node != null) {
            result = node.subtreeDelete();
        }
        return result.getItem();
    }

    @Override
    public T find(T item) {
        // return this.treeFind(this.root, item).getItem();
        BinaryTreeNode<T> node = this.root.subtreeFind(item);
        if (node != null)  {
            return node.getItem();
        } else {
            return null;
        }
    }

    @Override
    public T findMax() {
        return this.root.subtreeLast().getItem();
    }

    @Override
    public T findMin() {
        return this.root.subtreeFirst().getItem();
    }

    @Override
    public T findNext(T item) {
        BinaryTreeNode<T> target = this.root.subtreeNext(item);
        if (target != null) {
            return target.getItem();
        } else {
            return null;
        }
    }

    @Override
    public T findPrev(T item) {
        BinaryTreeNode<T> target = this.root.subtreePrev(item);
        if (target != null) {
            return target.getItem();
        } else {
            return null;
        }
    }

    @Override
    public void insert(T item) {
        // NOTE: insert should not only preserve traversal order but intrinsic order as well.
        // NOTE: items greater than or equal to a node will go on the right side
        BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(this.internalType, item);
        this.treeInsert(this.root, newNode);
        this.itemCount += 1;
    }

    @Override
    public int len() {
        return this.itemCount;
    }

    private void treeInsert(BinaryTreeNode<T> start, BinaryTreeNode<T> newNode) {
        T val1 = start.getItem();
        T val2 = newNode.getItem();
        if (val2.compareTo(val1) < 0) {
            if (start.getLeftChild() != null) {
                this.treeInsert(root.getLeftChild(), newNode);
            } else {
                start.subtreeInsertBefore(newNode);
                start.maintain();
            }
        } else {
            if (start.getRightChild() != null) {
                this.treeInsert(start.getRightChild(), newNode);
            } else {
                start.subtreeInsertAfter(newNode);
                start.maintain();
            }
        }
    }

    // META
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryTree[\n");
        sb.append(this.root.toString());
        sb.append("]");
        return sb.toString();
    }
}

