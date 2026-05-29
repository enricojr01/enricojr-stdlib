package com.enricojr.stdlib.sets;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.sequences.StaticArray;

public class SetArray<T extends Comparable<T>> implements SetInterface<T>, Iterable<T> {

    private StaticArray<T> internal;

    /**
     * Inefficient implementation of a simple Set based on the StaticArray. Done purely for 
     * educational purposes, please don't actually use this.
     * 
     * @param args
     */
    public SetArray(T... args) {
        this.internal = new StaticArray<T>(args);
    }

    /**
     * Returns an iterator of the internal StaticArray<T>. Allows SetArray to be used with the
     * enhanced Java for syntax.
     * 
     * @return an instance of SequenceIterator<T>
     */
    public Iterator<T> iterator() {
        List<T> thing = Arrays.asList(this.internal.getArray());
        return thing.iterator();
    }

    /**
     * Removes item from the array.
     * 
     * Runs in O(n) time, as we walk through the list until we find the item.
     */
    @Override
    public T delete(T item) {
        int targetIdx = -1;
        for (int i = 0; i < internal.len(); i++) {
            if (this.internal.getAt(i).equals(item)) {
                targetIdx = i;
            }
        }
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public int len() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void sort() {
        // TODO Auto-generated method stub
        
    }


}
