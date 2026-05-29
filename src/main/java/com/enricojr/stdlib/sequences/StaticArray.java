package com.enricojr.stdlib.sequences;

import com.enricojr.stdlib.interfaces.StaticSequenceInterface;
import com.enricojr.stdlib.iterators.StaticSequenceIterator;

public class StaticArray<T> implements StaticSequenceInterface<T>, Iterable<T> {
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
    public StaticSequenceIterator<T> iterator() {
        return new StaticSequenceIterator<T>(this);
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
     * Returns the length of the array. Runs in O(1) time, as the length is stored in the array 
     * itself and simply returned.
     */
    @Override
    public int len() {
        return this.internal.length;
    }

    /**
     * Returns the internal array of type T[] because I can't implement SetArray otherwise. Now 
     * that this no longer implements SequenceInterface I cant use the SequenceIterator for it. 
     * Don't quite know if that was the right choice or not.
     * 
     * @return
     */
    public T[] getArray() {
        return this.internal;
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
