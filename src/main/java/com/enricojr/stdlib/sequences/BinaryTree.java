package com.enricojr.stdlib.sequences;

import java.util.Arrays;
import java.util.Iterator;
import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.iterators.BinaryTreeIterator;
import com.enricojr.stdlib.sorters.InsertionSorter;

public class BinaryTree<T extends Comparable<T>> implements SetInterface<T>, Iterable<T> {
    // NOTE: The root of the tree lacks a parent.
    // NOTE: A node having no children is a leaf.
    private BinaryTreeNode<T> root;
    private Class<T> internalType;

    public BinaryTree(Class<T> type, T item) {
        this.root = new BinaryTreeNode<T>(type, item);
        this.internalType = type;
    }

    public BinaryTree(Class<T> type, T[] items) {
        InsertionSorter<T> sorter = new InsertionSorter<T>(items);
        this.internalType = type;

        T[] sorted = sorter.getInternal();
        int middleIndex = Math.floorDiv(sorted.length, 2);

        // slice it up
        T middleElement = sorted[middleIndex];
        T[] bigger = Arrays.copyOfRange(sorted, middleIndex, sorted.length - 1);
        T[] smaller = Arrays.copyOfRange(sorted, 0, middleIndex);

        // TODO: Build the tree, starting from the middle element.
        this.root = new BinaryTreeNode<T>(type, middleElement);
        for (T item : smaller) {
            this.insert(item);
        }
        for (T item : bigger) {
            this.insert(item);
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T find(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findMax() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findMin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findNext(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findPrev(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(T item) {
        // NOTE: insert should not only preserve traversal order but intrinsic order as well.
        // NOTE: items greater than or equal to a node will go on the right side
        BinaryTreeNode<T> newNode = new BinaryTreeNode<T>(this.internalType, item);
        this.treeInsert(this.root, newNode);
    }

    @Override
    public int len() {
        // TODO Auto-generated method stub
        return 0;
    }

    private void treeInsert(BinaryTreeNode<T> start, BinaryTreeNode<T> newNode) {
        if (start.getItem().compareTo(newNode.getItem()) < 0) {
            if (start.getLeftChild() != null) {
                this.treeInsert(start.getLeftChild(), newNode);
            } else {
                start.subtreeInsertBefore(newNode);
            }
        } else {
            if (start.getRightChild() != null) {
                this.treeInsert(start.getRightChild(), newNode);
            } else {
                start.subtreeInsertAfter(newNode);
            }
        }
    }

    // META
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryTree[");
        for (T item : this) {
            sb.append(item + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}

