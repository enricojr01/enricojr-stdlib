package com.enricojr.stdlib.sequences;

import java.lang.reflect.Array;
import com.enricojr.stdlib.interfaces.StaticSequenceInterface;
import com.enricojr.stdlib.iterators.StaticSequenceIterator;

/**
 * NOTE: Casting Object[] to T[] is unsafe because there's no way for compiler to guarantee
 * that the Object[] actually contains T[]. The correct way to do this is to create an array
 * of the correct type via reflection:
 * 
 *     Array.newInstance(String.class, size)
 * 
 * Where String.class is replaced with the appropriate class.
 * 
 * T[] is erased at runtime by the compiler and becomes Object[], thus casting Object[] to T[]
 * will succeed regardless of whatever is in the Object[] array, and errors will only appear
 * at runtime when elements are accessed. There is no way for the compiler to guarantee that
 * an Object[] actually contains anything of type T.
 */
/**
 * NOTE: the Java primitives int, char, float, boolean are not "Objects" in the Java sense
 * of the word, they're "raw" i.e. have no identity / hashcode / members / methods. They represent
 * near-literally the values that a CPU would work with. Most of the time, especially with respect
 * to Java's type system and with this particular project, we want to be working with the boxed 
 * versions of the primitive type i.e. Integer, Float, Boolean, etc.
 * 
 * There is a performance penalty to using boxed over primitive, but that can be dealt with later
 * and is arguably worth paying when you consider the type safety you get in return.
 */

public class StaticArray<T> implements StaticSequenceInterface<T>, Iterable<T> {
    private T[] internal;

    /**
     * A simple wrapper around Java's built-in static arrays. The wrapper implements the provided 
     * SequenceInterface, and implements Iterable for quality-of-life.
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
