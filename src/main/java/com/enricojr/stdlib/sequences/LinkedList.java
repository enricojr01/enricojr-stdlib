package com.enricojr.stdlib.sequences;

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
        LinkedListNode<T> current = null;
        for (T item : args) {
            if (this.head == null) {
                LinkedListNode<T> node = new LinkedListNode<>(item);
                current = node;
                this.head = current;
                this.count += 1;
            } else {
                LinkedListNode<T> node = new LinkedListNode<>(item);
                current.setNext(node);
                node.setPrev(current);
                current = node;
                this.tail = current;
                this.count += 1;
            }
        }
    }

    /**
     * Returns an iterator over the Linked List. This is mainly to support the "enhanced" for loop
     * i.e. `for (T item : myLinkedList) { ... }` 
     */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(this);
    }

    /**
     * Returns the head of the linked list. Functionally identical to .getFirst(), but provided
     * separately here in case you'd like to refer to it as the 'head' rather than the 'first'.
     * Runs in O(1) time because we store a reference to the head and return it.
     */

    public LinkedListNode<T> getHead() {
        return head;
    }
    
    /**
     * Sets the head of the linked list to a `head`. Used mainly for constructing the initial list
     * and doesn't have any special uses otherwise. Runs in O(1) time.
     * @param head
     */
    public void setHead(LinkedListNode<T> head) {
        this.head = head;
    }

    /**
     * Returns the tail of the linked list. Functionally identical to .getLast() but with different
     * semantics. Runs in O(1) time because we store a reference to the tail and simply return it.
     */
    public LinkedListNode<T> getTail() {
        return tail;
    }

    /**
     * Sets the tail of the list, and like setHead() is really only used for constructing the 
     * initial list.
     * @param tail
     */
    public void setTail(LinkedListNode<T> tail) {
        this.tail = tail;
    }

    /** 
     * Returns the item from the LinkedListNode at position x. Runs in O(n) time because it
     * has to walk the list one item at a time.     
     */
    @Override
    public T getAt(int idx) {
        if (idx > this.count - 1) {
            throw new ArrayIndexOutOfBoundsException();
        } else if (idx == this.count - 1) {
            this.getLast();
        } else if (idx == 0) {
            this.getFirst();
        }

        int counter = 0;
        LinkedListNode<T> current = this.head;

        // NOTE: kinda unintuitive but it won't capture the final element if you're using
        // the typical while (current.getNext() != null) condition check.
        // incrementing idx by one and pulling the result AFTER the loop will solve this problem
        while (counter != idx) {
            current = current.getNext();
            counter += 1;
        }

        return current.getItem();
    }

    /**
     * Returns, but does not delete, the first item in the linked list. Runs in O(1) time because 
     * we augmeted the LinkedList to store the head, and update it whenever the list changes.
     */
    @Override
    public T getFirst() {
        return head.getItem();
    }

    /**
     * Returns, but odes not delete, the last item in the linked list. Runs in O(1) time because we 
     * augmented the LinkedList to store the tail, and update it whenever the list changes.
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
        if (idx > this.count - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (idx == 0) {
            this.deleteFirst();
        }
        if (idx == this.count - 1) {
            this.deleteLast();
        }

        int counter = 0;
        LinkedListNode<T> current = this.head;

        // move first
        while (counter != idx) {
            current = current.getNext();
            counter += 1;
        }

        // then delete
        // prev - current - next
        // prev - next
        LinkedListNode<T> previous = current.getPrev();
        LinkedListNode<T> next = current.getNext();
        previous.setNext(next);
        next.setPrev(previous);

        // decrement internal item count
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
        // check if index OOB and throw exception, Linked Lists here are 0-based.
        if (idx > this.count - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        // if index == 0, just call this.insertFirst() no need to repeat the logic here
        if (idx == 0) {
            this.insertFirst(item);
        }
        // ditto with index == this.count - 1 (i.e. last) no need to repeat that logic here.
        if (idx == this.count - 1) {
            this.insertLast(item);
        }

        // we start at the head, of course.
        LinkedListNode<T> current = this.head;

        // move first, THEN act.
        int counter = 0;
        while (counter != idx) {
            current = current.getNext();
            counter += 1;
        }

        // build the node and insert it:
        // previous - current - next
        // previous - NEW - current - next
        LinkedListNode<T> currentPrev = current.getPrev();
        LinkedListNode<T> node = new LinkedListNode<T>(item);

        // set previous node to point to new node 
        currentPrev.setNext(node);

        // set new node
        node.setNext(current);
        node.setPrev(currentPrev);

        // set current node prev to point to new node
        current.setPrev(node);
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
        sb.append("LinkedList");
        sb.append("(" + this.count + ")[");
        for (T item : this) {
            sb.append(item + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
