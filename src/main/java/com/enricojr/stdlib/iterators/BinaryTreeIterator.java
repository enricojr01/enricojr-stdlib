package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.sequences.BinaryTree;
import com.enricojr.stdlib.sequences.BinaryTreeNode;
import com.enricojr.stdlib.sequences.DynamicArray;

public class BinaryTreeIterator<T> implements Iterator<T> {
    private BinaryTree<T> tree;
    private DynamicArray<T> stack;
    private int pointer;

    public BinaryTreeIterator(BinaryTree<T> tree) {
        this.tree = tree;
        this.stack = new DynamicArray<>(this.tree.getRoot().getInternalType());
        this.treeWalk(this.tree.getRoot());
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        if (this.pointer < this.stack.len()) {
            return true;
        }
        return false;
    }

    @Override
    public T next() {
        // TODO Auto-generated method stub
        T item = this.stack.getAt(this.pointer);
        this.pointer += 1;

        return item;
    }

    private void treeWalk(BinaryTreeNode<T> start) {
        if (start.getLeftChild() != null) {
            this.treeWalk(start.getLeftChild());
        }
        this.stack.insertLast(start.getItem());
        if (start.getRightChild() != null) {
            this.treeWalk(start.getRightChild());
        }
    }

}
