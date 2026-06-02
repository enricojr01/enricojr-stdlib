package com.enricojr.stdlib.sets;

import java.util.Objects;
import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.sequences.LinkedList;
import com.enricojr.stdlib.sequences.StaticArray;

public class HashedSet<T extends Comparable<T>> implements SetInterface<T> {
    private StaticArray<LinkedList<T>> internal;
    private int itemCount = 0;

    /**
     * HashedSet is an unordered collection of items, based on some property of the item. All items 
     * must implement Comparable<T> to be used with this data structure.
     */
    public HashedSet(Class<T> type) {
    }
    
    public HashedSet(Class<T> type, T[] args) {
    }

    // public StaticSequenceIterator<T> iterator() {
    //     return new StaticSequenceIterator<T>(this.internal);
    // }

    @Override
    public T delete(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T find(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findMax() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findMin() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findNext(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public T findPrev(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insert(T item) {
        int bucket = Objects.hashCode(item) % this.internal.len();
        if (this.internal.getAt(bucket) == null) {
            LinkedList<T> chain = new LinkedList<T>();
            chain.insertLast(item);
            this.internal
                .setAt(bucket, chain);
        } else {
            this.internal
                .getAt(bucket)
                .insertLast(item);
        }
        this.itemCount += 1;
    }

    @Override
    public int len() {
        // TODO Auto-generated method stub
        return this.itemCount;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HashedSet[");
        // for (T item : this) {

        // }

        sb.append("]");
        return sb.toString();
    }

}
