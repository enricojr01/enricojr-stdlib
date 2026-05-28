package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.LinkedList;
import com.enricojr.stdlib.LinkedListNode;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedList<T> internal;
    private LinkedListNode<T> current;

    public LinkedListIterator(LinkedList<T> list) {
        this.internal = list;
        this.current = this.internal.getHead();
    }

    @Override
    public boolean hasNext() {
        if (current.getNext() != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        T item = this.current.getItem();
        this.current = current.getNext();
        return item;
    }

}
