package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.sequences.DynamicArray;
import com.enricojr.stdlib.sequences.Stack;
import com.enricojr.stdlib.sets.BinaryTreeNode;

public class BinaryTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private BinaryTreeNode<T> start;
    private DynamicArray<T> internal;
    private int index = 0;

    public BinaryTreeIterator(Class<T> type, BinaryTreeNode<T> start) {
        this.start = start;
        this.internal = new DynamicArray<>(type);
        this.walk(this.start);
    }

    @Override
    public boolean hasNext() {
        if (this.index <= this.internal.len() - 1) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        T item = this.internal.getAt(this.index);
        this.index += 1;
        return item;
    }

    private void walk(BinaryTreeNode<T> start) {
        if (start.getLeftChild() != null) {
            this.walk(start.getLeftChild());
        }

        this.internal.insertLast(start.getItem());

        if (start.getRightChild() != null) {
            this.walk(start.getRightChild());
        }
    }
}
