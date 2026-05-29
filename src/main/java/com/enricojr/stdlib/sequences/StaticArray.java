package com.enricojr.stdlib.sequences;

import java.util.Iterator;
import com.enricojr.stdlib.interfaces.SequenceInterface;
import com.enricojr.stdlib.iterators.SequenceIterator;

public class StaticArray<T> implements SequenceInterface<T>, Iterable<T> {
    private T[] internal;

    /**
     * A simple wrapper around Java's built-in static arrays. The wrapper implements the provided 
     * SequenceInterface, and implements Iterable for quality-of-life.
     * 
     * Constructor takes an array of T items as varargs and prepopulates the the array. 
     * @param args
     */
    public StaticArray(T... args) {
        this.internal = (T[]) new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            this.internal[i] = args[i];
        }
    }

    /**
     * Alternate constructor that allows you to create an empty array matching a desired length. 
     * Operates by creating an Object[] array and casting it to T[].
     * @param length
     */
    public StaticArray(int length) {
        this.internal = (T[]) new Object[length];
    }

    /**
     * Returns an iterator that allows StaticArray to be used with the enhanced "for" syntax 
     * provided by Java.
     */
    @Override
    public Iterator<T> iterator() {
        return new SequenceIterator<T>(this);
    }

    /**
     * Returns the item at position x in the array. Runs in O(1) time, as it is just wrapping Java's
     * built-in array operations.
     */
    @Override
    public T getAt(int x) {
        return this.internal[x];
    }

    /**
     * Inserts item at position x in the array, overwriting existing contents. Runs in O(1) time for
     * the same reason as getAt().
     */
    @Override
    public void setAt(int x, T item) {
        this.internal[x] = item;
    }

    /**
     * Returns the item at the beginning of the array. Runs in O(1) time.
     */
    @Override
    public T getFirst() {
        return this.internal[0];
    }

    /**
     * Returns the item at the end of the array. Runs in O(1) time.
     */
    @Override
    public T getLast() {
        return this.internal[this.internal.length - 1];
    }

    /**
     * Places item at the beginning of the array, overwriting existing contents. Runs in O(1) time.
     */
    @Override
    public void setFirst(T item) {
        this.setAt(0, item);
    }

    /**
     * Places item at the end of the array, overwriting existing contents. Runs in O(1) time.
     */
    @Override
    public void setLast(T item) {
        this.setAt(this.internal.length - 1, item);
    }

    /**
     * Returns the length of the array. Runs in O(1) time, as the length is stored in the array 
     * itself and simply returned.
     */
    @Override
    public int len() {
        return this.internal.length;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StaticArray[");
        for (T item : this.internal) {
            sb.append(item.toString() + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
