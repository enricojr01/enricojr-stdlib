package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.sequences.DynamicArray;
import com.enricojr.stdlib.sets.BinaryTreeNode;

public class BinaryTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private BinaryTreeNode<T> start;
    private DynamicArray<T> stack;
    private int pointer;

    public BinaryTreeIterator(Class<T> type, BinaryTreeNode<T> start) {
        this.start = start;
        this.stack = new DynamicArray<>(type);
        this.walk(this.start);
    }

    @Override
    public boolean hasNext() {
        if (this.pointer < this.stack.len()) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        T item = this.stack.getAt(this.pointer);
        this.pointer += 1;
        return item;
    }

    private void walk(BinaryTreeNode<T> start) {
        if (start.getLeftChild() != null) {
            this.walk(start.getLeftChild());
        }

        this.stack.insertLast(start.getItem());

        if (start.getRightChild() != null) {
            this.walk(start.getRightChild());
        }
    }
}
