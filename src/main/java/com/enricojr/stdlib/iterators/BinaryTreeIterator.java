package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.sequences.DynamicArray;
import com.enricojr.stdlib.sequences.Stack;
import com.enricojr.stdlib.sets.BinaryTreeNode;

public class BinaryTreeIterator<T extends Comparable<T>> implements Iterator<T> {
    private BinaryTreeNode<T> start;
    private Stack<T> stack;

    public BinaryTreeIterator(Class<T> type, BinaryTreeNode<T> start) {
        this.start = start;
        this.stack = new Stack<>(type);
        this.walk(this.start);
    }

    @Override
    public boolean hasNext() {
        if (this.stack.len() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        return this.stack.pop();
    }

    private void walk(BinaryTreeNode<T> start) {
        if (start.getLeftChild() != null) {
            this.walk(start.getLeftChild());
        }

        this.stack.push(start.getItem());

        if (start.getRightChild() != null) {
            this.walk(start.getRightChild());
        }
    }
}
