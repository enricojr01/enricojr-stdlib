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

    public int getHeight() {
        return this.height;
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
        while (
            current.getParent() != null 
            && current.getParent().getRightChild() != null 
            && current.getParent().getRightChild().equals(this)
        ) {
            current = current.getParent();
        }

        return current.getParent();
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
        while (
            current.getParent() != null 
            && current.getParent().getLeftChild() != null 
            && current.getParent().getLeftChild().equals(this)
        )  {
            current = current.getParent();
        }

        return current.getParent();
    }

    // SEARCH
    // NOTE: Successor and Predecessor are not necessarily the next / previous nodes numerically!
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

    public BinaryTreeNode<T> subtreeNext(T item) {
        if (this.getItem().compareTo(item) <= 0) {
            if (this.getRightChild() != null) {
                return this.getRightChild().subtreeNext(item);
            } else {
                return null;
            }
        } else if (this.getLeftChild() != null) {
            BinaryTreeNode<T> target = this.getLeftChild().subtreeNext(item);
            if (target != null) {
                return target;
            } 
        } 
        return this;
    }

    public BinaryTreeNode<T> subtreePrev(T item) {
        if (this.getItem().compareTo(item) >= 0) {
            if (this.getLeftChild() != null)  {
                return this.getLeftChild().subtreePrev(item);
            } else {
                return null;
            }
        } else if (this.getRightChild() != null) {
            BinaryTreeNode<T> target = this.getRightChild().subtreePrev(item);
            if (target != null) {
                return target;
            } 
        } 
        return this;
    }

    public BinaryTreeNode<T> subtreeAt(int index) {
        if (index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }
        int leftSize = 0;
        if (index < leftSize) {
            return this.getLeftChild().subtreeAt(index);
        } else if (index > leftSize) {
            return this.getRightChild().subtreeAt(index - leftSize - 1);
        } else {
            return this;
        }
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
        // this.maintain();
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
        // this.maintain();
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

            this.getParent().maintain();
        }
        return this;
    }

    // UPDATE / MAINTENANCE
    public void subtreeRotateRight() {
        if (this.getLeftChild() == null) {
            // TODO: make a proper exception and use it here 
            throw new ArrayIndexOutOfBoundsException();
        }

        BinaryTreeNode<T> left = this.getLeftChild();
        BinaryTreeNode<T> leftRightNode = left.getRightChild();
        this.setLeftChild(leftRightNode);

        if (leftRightNode != null) {
            leftRightNode.setParent(this);
        }

        BinaryTreeNode<T> currentParent = this.getParent();
        left.setParent(currentParent);

        if (currentParent != null && currentParent.getLeftChild().equals(this)) {
            this.getParent().setLeftChild(left);
        } else if (currentParent != null && currentParent.getRightChild().equals(this)) {
            this.getParent().setRightChild(left);
        }

        left.setRightChild(this);
        this.setParent(left);

    }

    public void subtreeRotateLeft() {
        if (this.getRightChild() == null) {
            throw new ArrayIndexOutOfBoundsException();
        }

        // turn right child left subtree into current node's right subtree
        BinaryTreeNode<T> right = this.getRightChild();
        BinaryTreeNode<T> rightLeftNode = right.getLeftChild();
        this.setRightChild(rightLeftNode);

        // if right child left subtree isn't null
        if (rightLeftNode != null) {
            rightLeftNode.setParent(this);
        }

        // current node becomes right child's parent
        BinaryTreeNode<T> currentParent = this.getParent();
        right.setParent(currentParent);

        if (currentParent != null && this.getParent().getLeftChild().equals(this)) {
            this.getParent().setLeftChild(right);
        } else if (currentParent != null && this.getParent().getRightChild().equals(this)) {
            this.getParent().setRightChild(right);
        }

        right.setLeftChild(this);
        this.setParent(right);
    }

    private void subtreeUpdate() {
        int leftHeight;
        int rightHeight;

        if (this.getRightChild() == null) {
            rightHeight = -1;
        } else {
            rightHeight = this.getRightChild().getHeight();
        }

        if (this.getLeftChild() == null) {
            leftHeight = -1;
        } else {
            leftHeight = this.getLeftChild().getHeight();
        }

        this.height = 1 + Math.max(leftHeight, rightHeight);

        if (this.getLeftChild() != null) {
            this.size += this.getLeftChild().getSize();
        }
        if (this.getRightChild() != null) {
            this.size += this.getRightChild().getSize();
        }
    }

    public int skew() {
        // kinda messy but
        int rightHeight;
        int leftHeight;

        if (this.getRightChild() != null) {
            rightHeight = this.getRightChild().getHeight();
        } else {
            rightHeight = 0;
        }

        if (this.getLeftChild() != null) {
            leftHeight = this.getLeftChild().getHeight();
        } else {
            leftHeight = 0;
        }
        return rightHeight - leftHeight;
    }

    private void maintain() {
        this.rebalance();
        this.subtreeUpdate();
        if (this.getParent() != null) {
            this.getParent().maintain();
        }
    }

    private void rebalance() {
        if (this.skew() == 2) {
            if (this.getRightChild() != null && this.getRightChild().skew() < 0) {
                this.getRightChild().subtreeRotateRight();
            }
            this.subtreeRotateLeft();
        } else if (this.skew() == -2) {
            if (this.getLeftChild() != null && this.getLeftChild().skew() > 0) {
                this.getLeftChild().subtreeRotateLeft();
            }
            this.subtreeRotateRight();
        }
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
            this.getRightChild().print(buffer, childrenPrefix + "|_ " , childrenPrefix + "   ");
        }
    }
}
