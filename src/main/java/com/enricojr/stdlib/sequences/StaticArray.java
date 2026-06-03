package com.enricojr.stdlib.sequences;

import java.lang.reflect.Array;
import java.util.Arrays;
import com.enricojr.stdlib.interfaces.StaticSequenceInterface;
import com.enricojr.stdlib.iterators.StaticSequenceIterator;

public class StaticArray<T> implements StaticSequenceInterface<T>, Iterable<T> {
    private T[] internal;

    /**
     * A simple wrapper around Java's built-in static arrays. The wrapper implements the provided 
     * SequenceInterface, and implements Iterable<T> ease-of-use.
     * 
     * Constructor takes an array of T items as varargs and prepopulates the the array. 
     * @param args
     */
    @SuppressWarnings("unchecked")
    public StaticArray(Class<T> type, T[] args) {
        this.internal = (T[]) Array.newInstance(type, args.length);

        for (int i = 0; i < args.length; i++) {
            this.internal[i] = args[i];
        }
    }

    /**
     * Alternate constructor that allows you to create an empty array matching a desired length. 
     * Operates by creating an Object[] array and casting it to T[].
     * @param length
     */
    @SuppressWarnings("unchecked")
    public StaticArray(Class<T> type, int length) {
        this.internal = (T[]) Array.newInstance(type, length);
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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StaticArray[");
        for (T item : this.internal) {
            sb.append(item.toString() + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.deepHashCode(internal);
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
        // TODO: Another unchecked cast that I can't seem to get around, find a fix
        StaticArray<T> other = (StaticArray<T>) obj;
        if (!Arrays.deepEquals(internal, other.internal))
            return false;
        return true;
    }
}
