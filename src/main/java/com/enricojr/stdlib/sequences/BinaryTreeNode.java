package com.enricojr.stdlib.sequences;

public class BinaryTreeNode<T> {

    // the number of nodes in this node's subtree.
    private int size;
    private BinaryTreeNode<T> parent;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;
    private T item;
    private Class<T> internalType;

    public BinaryTreeNode(Class<T> type, T item) {
        this.item = item;
        this.internalType = type;
    }

    public BinaryTreeNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryTreeNode<T> parent) {
        this.parent = parent;
    }

    public BinaryTreeNode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTreeNode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTreeNode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTreeNode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public Class<T> getInternalType() {
        return this.internalType;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BinaryTreeNode[");
        sb.append(
            this.parent.toString() + ", " + 
            this.leftChild.toString() + ", " + 
            this.rightChild.toString()
        );
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        return result;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        // TODO: fix this unchecked cast;
        BinaryTreeNode<T> other = (BinaryTreeNode<T>) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        return true;
    }
}
