package com.enricojr.stdlib;

import java.util.Iterator;
import com.enricojr.stdlib.interfaces.SequenceInterface;
import com.enricojr.stdlib.iterators.SequenceIterator;

public class StaticArray<T> implements SequenceInterface<T>, Iterable<T> {
    private T[] internal;

    public StaticArray(T... args) {
        this.internal = (T[]) new Object[args.length];

        for (int i = 0; i < args.length; i++) {
            this.internal[i] = args[i];
        }
    }

    public StaticArray(int length) {
        this.internal = (T[]) new Object[length];
    }

    @Override
    public Iterator<T> iterator() {
        return new SequenceIterator<T>(this);
    }

    @Override
    public T getAt(int x) {
        return this.internal[x];
    }

    @Override
    public void setAt(int x, T item) {
        this.internal[x] = item;
    }

    @Override
    public T getFirst() {
        return this.internal[0];
    }

    @Override
    public T getLast() {
        return this.internal[this.internal.length - 1];
    }

    @Override
    public void setFirst(T item) {
        this.setAt(0, item);
    }

    @Override
    public void setLast(T item) {
        this.setAt(this.internal.length - 1, item);
    }

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
