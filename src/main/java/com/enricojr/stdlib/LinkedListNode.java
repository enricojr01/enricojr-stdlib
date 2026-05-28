package com.enricojr.stdlib;

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
}
