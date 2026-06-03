package com.enricojr.stdlib.sequences;

public class LinkedListNode<T> {
    private LinkedListNode<T> prev;
    private LinkedListNode<T> next;
    private T item;

    public LinkedListNode() {}

    public LinkedListNode(T item) {
        this.item = item;
    }

    public LinkedListNode(T item, LinkedListNode<T> next, LinkedListNode<T> prev) {
        this.item = item;
        this.next = next;
        this.prev = prev;
    }

    public LinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(LinkedListNode<T> prev) {
        this.prev = prev;
    }

    public LinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(LinkedListNode<T> next) {
        this.next = next;
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListNode(");
        sb.append(item.toString());
        sb.append(")");
        return sb.toString();
    }

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
        LinkedListNode<T> other = (LinkedListNode<T>) obj;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        return true;
    }
}
