package com.enricojr.stdlib.sets;

public class BinaryTreeNode<T extends Comparable<T>> {

    // the number of nodes in this node's subtree.
    private int size;
    private int height;
    private BinaryTreeNode<T> parent;
    private BinaryTreeNode<T> leftChild;
    private BinaryTreeNode<T> rightChild;
    private T item;
    private Class<T> internalType;

    public BinaryTreeNode(Class<T> type, T item) {
        this.item = item;
        this.internalType = type;
    }

    // GETTERS AND SETTERS
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    // TRAVERSAL
    // NOTE: Seems like in-order traversal needs to be handled by some outside mechanism
    //       the method presented in the notes requires Python's "yield" which doesn't quite exist
    //       in Java.
    /**
     * Returns the node that occurs first in the tree's traversal order.
     * 
     * @return
     */
    public BinaryTreeNode<T> subtreeFirst() {
        if (this.getLeftChild() != null) {
            return this.getLeftChild().subtreeFirst();
        } else {
            // NOTE: will this even work?
            // NOTE: recursive stuff can be confusing
            return this;
        }
    }

    /**
     * Returns the node that occurs last in the tree's traversal order.
     * 
     * @return
     */
    public BinaryTreeNode<T> subtreeLast() {
        if (this.getRightChild() != null) {
            return this.getRightChild().subtreeLast();
        } else {
            return this;
        }
    }

    public BinaryTreeNode<T> subtreeFind(T item) {
        if (item.compareTo(this.getItem()) == 0) {
            return this;
        } else if (item.compareTo(this.getItem()) < 0) {
            if (this.getLeftChild() != null) {
                return this.getLeftChild().subtreeFind(item);
            }
        } else if (item.compareTo(this.getItem()) > 0) {
            if (this.getRightChild() != null) {
                return this.getRightChild().subtreeFind(item);
            }
        }
        return null;
    }

    /**
     * Returns the node that comes after this one in the traversal order.
     * 
     * @return
     */
    public BinaryTreeNode<T> successor() {
        // if the current node has a right child, its successor is the first node in traversal
        // order in the right subtree.
        if (this.getRightChild() != null) {
            return this.getRightChild().subtreeFirst();
        }

        // otherwise find the lowest ancestor of the current node such that the current node
        // is in the left subtree.
        // NOTE: I don't quite wrap my head around how this works but OK.
        BinaryTreeNode<T> current = this;
        while (current.getParent() != null && current.getParent().getRightChild().equals(this)) {
            current = current.getParent();
        }

        return current.parent;
    }

    /**
     * Returns the node that comes before this one in the traversal order.
     * 
     * @return
     */
    public BinaryTreeNode<T> predecessor() {
        if (this.getLeftChild() != null) {
            return this.getLeftChild().subtreeLast();
        }

        BinaryTreeNode<T> current = this;
        while (current.getParent() != null && current.getParent().getLeftChild().equals(this)) {
            current = current.getParent();
        }

        return current.getParent();
    }

    // INSERTION
    /**
     * Inserts node `item` before the current node, in traversal order.
     * 
     * @param item
     */
    public void subtreeInsertBefore(BinaryTreeNode<T> item) {
        if (this.getLeftChild() != null)  {
            BinaryTreeNode<T> last = this.getLeftChild().subtreeLast();
            item.setParent(last);
            last.setRightChild(item);
        } else {
            item.setParent(this);
            this.setLeftChild(item);
        }
    }

    /**
     * Inserts node `item` after the current node, in traversal order.
     * 
     * @param item
     */
    public void subtreeInsertAfter(BinaryTreeNode<T> item) {
        if (this.getRightChild() != null) {
            BinaryTreeNode<T> first = this.getRightChild().subtreeFirst();
            item.setParent(first);
            first.setLeftChild(item);
        } else {
            item.setParent(this);
            this.setRightChild(item);
        }
    }

    // DELETION
    public BinaryTreeNode<T> subtreeDelete() {
        // NOTE: Double check this later, but I think I got it right.
        // If the node is not a leaf - swap its contents with its successor / predecessor until its
        // in a leaf and delete.
        // The actual finding of the node is handled by the tree class 
        if (this.getLeftChild() != null || this.getRightChild() != null) {
            BinaryTreeNode<T> target = null;
            if (this.getLeftChild() != null) {
                target = this.predecessor();
            } else {
                target = this.successor();
            }
            T temp = this.getItem();
            this.setItem(target.getItem());
            target.setItem(temp);
            return target.subtreeDelete();
        }
        if (this.getParent() != null) {
            BinaryTreeNode<T> leftChild = this.getParent().getLeftChild();
            if (leftChild != null && leftChild.equals(this) ){
                this.getParent().setLeftChild(null);
            } else {
                this.getParent().setRightChild(null);
            }
            // call to maintain here
        }
        return this;
    }

    // META
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        return result;
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.print(sb, "", "");
        return sb.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.getItem());
        buffer.append("\n");

        if (this.getLeftChild() != null) {
            this.getLeftChild().print(buffer, childrenPrefix + "|--", childrenPrefix + "|  ");
        }

        if (this.getRightChild() != null) {
            this.getRightChild().print(buffer, childrenPrefix + "|_ " , childrenPrefix + "     ");
        }
    }
}
