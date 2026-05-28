package com.enricojr.stdlib;

import java.util.Iterator;
import com.enricojr.stdlib.interfaces.DynamicArrayInterface;
import com.enricojr.stdlib.interfaces.SequenceInterface;
import com.enricojr.stdlib.iterators.LinkedListIterator;

public class LinkedList<T> implements SequenceInterface<T>, DynamicArrayInterface<T>, Iterable<T> {
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int count = 0;

    public LinkedList() {}

    public LinkedList(T... args) {
        // first will double as the cursor.
        LinkedListNode<T> first = new LinkedListNode<>(args[0]);

        // set the head of the list to first because its first.
        this.head = first;

        // increment manually because we add this one outside the loop
        this.count += 1;
        for (int i = 1; i < args.length; i++) {
            // create a new node containing args[i]
            LinkedListNode<T> node = new LinkedListNode<>(args[i]);

            // set node to be the next in the chain
            first.setNext(node);

            // set prev on the node to point back to first
            node.setPrev(first);

            // set first to node
            first = node;

            // set tail to node. I figure if I do this every loop by the time it terminates it will
            // be correctly set.
            this.tail = node;

            // don't forget to increment the count 
            this.count += 1;
        }
    }

    public LinkedList(LinkedListNode<T> head) {
        this.head = head;
        this.tail = head;
        this.count += 1;
    }

    public LinkedList(LinkedListNode<T> head, LinkedListNode<T> tail) {
        this.head = head;
        this.tail = tail;
        this.count += 2;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    public LinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(LinkedListNode<T> head) {
        this.head = head;
    }

    public LinkedListNode<T> getTail() {
        return tail;
    }

    public void setTail(LinkedListNode<T> tail) {
        this.tail = tail;
    }

    /** 
     * Returns the item from the LinkedListNode at position x. Runs in O(n) time because it
     * has to walk the list one item at a time.     
     */
    @Override
    public T getAt(int idx) {
        LinkedListNode<T> current = this.head;
        int counter = 0;
        T result = null;
        while (counter != idx) {
            counter += 1;
        }

        return result;
    }

    /**
     * Returns the first item in the linked list. Runs in O(1) time because we augmeted the
     * LinkedList to store the head, and update it whenever the list changes.
     */
    @Override
    public T getFirst() {
        return head.getItem();
    }

    /**
     * Returns the last item in the linked list. Runs in O(1) time because we augmented the
     * LinkedList to store the tail, and update it whenever the list changes.
     */
    @Override
    public T getLast() {
        return tail.getItem();
    }

    /**
     * Returns the number of items in the linked list. Runs in O(1) time because we augmented
     * the LinkedList to store the number of items, and update it whenever the list changes.
     */
    @Override
    public int len() {
        return this.count;
    }

    /**
     * Wraps a call to insertAt(). Unlike the StaticArray, this will not replace the item position x
     * but will instead be inserted alongside it.
     */
    @Override
    public void setAt(int x, T item) {
        this.insertAt(x, item);
    }

    /**
     * Wraps a call to insertFirst(). Does not replace the head, but instead is inserted before it, 
     * and promoted.
     */
    @Override
    public void setFirst(T item) {
        this.insertFirst(item);
    }

    /**
     * Wraps a call to insertLast(). Does not replace the tail, but is added after it and promoted.
     */
    @Override
    public void setLast(T item) {
        this.insertLast(item);
    }

    /**
     * Removes the node at position idx from the list. Runs in around O(n) time on average I 
     * suppose because we walk the list. Can't use the iterator here because we need to 
     * touch the actual node :-(
     */
    @Override
    public void deleteAt(int idx) {
        int counter = 0;
        LinkedListNode<T> current = this.head;

        while (counter != idx) {
            if (counter == idx) {
                LinkedListNode<T> a = current.getPrev();
                LinkedListNode<T> b = current.getNext();
                // these two operations should leave the node orphaned, and the GC should sweep
                // it away.
                a.setNext(b);
                b.setPrev(a);
                break;
            }
            current = current.getNext(); 
            counter += 1;
        }
        this.count -= 1;
    }

    /** 
     * Removes the node at the front of the list, and promotes the 2nd node to head. Runs in O(1) 
     * time because we had the foresight to store a reference to the front of the list. 
     */
    @Override
    public void deleteFirst() {
        this.head = this.head.getNext();
        this.count -= 1;
    }

    /** 
     * Removes the node at the back of the list, and promotes the second-to-last node to tail. Runs 
     * in O(1) time because we had the foresight to store a reference to the last node in the list. 
     */
    @Override
    public void deleteLast() {
        this.tail = this.tail.getPrev();
        this.count -= 1;
    }

    /**
     * Creates a new node containing item and stores it at position idx within the LinkedList. Does 
     * not replace the node at that position, but rather moves it down the list. Runs in O(n) time 
     * because we have to walk the list. The actual insert consists of three O(1) operations and 
     * are therefore insignificant here.
     */
    @Override
    public void insertAt(int idx, T item) {
        LinkedListNode<T> node = new LinkedListNode<T>(item);
        LinkedListNode<T> current = this.head;

        int counter = 0;
        while (counter != idx) {
            // If I did this right this should shove the new node right between two existing ones.
            if (counter == idx) {
                LinkedListNode<T> a = current.getPrev();
                node.setPrev(a);
                node.setNext(current);
                this.count += 1;
            }
            counter += 1;
        }
    }

    /** 
     * Creates a new node containing item and puts it at the front of the list. Runs in O(1) time 
     * because we don't need to walk the list.
     */
    @Override
    public void insertFirst(T item) {
        LinkedListNode<T> node = new LinkedListNode<T>(item);
        node.setNext(this.head);
        this.head.setPrev(node);
        this.head = node;
        this.count += 1;
    }

    /** 
     * Creates a new node containing item and puts it at the back of the list. Runs in O(1) time 
     * because we don't need to walk the list.
     */
    @Override
    public void insertLast(T item) {
        LinkedListNode<T> node = new LinkedListNode<T>(item);
        node.setPrev(this.tail);
        this.tail.setNext(node);
        this.tail = node;
        this.count += 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList[");
        for (T item : this) {
            sb.append(item + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
