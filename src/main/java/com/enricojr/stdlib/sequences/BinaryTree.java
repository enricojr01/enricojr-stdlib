package com.enricojr.stdlib.sequences;

import java.util.Iterator;

public class BinaryTree<T> {
    // NOTE: The root of the tree lacks a parent.
    // NOTE: A node having no children is a leaf.
    private BinaryTreeNode<T> root;

    public BinaryTree(BinaryTreeNode<T> root) {
        this.root = root;
    }

    public Iterator<BinaryTreeNode<T>> iterator() { 
        return null;
    }

    public BinaryTreeNode<T> getRoot() {
        return this.root;
    }
}
