package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.sequences.LinkedList;
import com.enricojr.stdlib.sequences.LinkedListNode;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedList<T> internal;
    private LinkedListNode<T> current;
    private int counter = 0 ;

    public LinkedListIterator(LinkedList<T> list) {
        this.internal = list;
        this.current = this.internal.getHead();
    }

    @Override
    public boolean hasNext() {
        // I have a feeling this will come back to bite me. 
        if (this.counter != this.internal.len()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        T item = this.current.getItem();
        this.current = current.getNext();
        this.counter += 1;
        return item;
    }

}
