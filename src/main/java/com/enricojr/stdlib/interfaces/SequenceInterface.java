package com.enricojr.stdlib.interfaces;

public interface SequenceInterface<T> {
    int len();
    T getAt(int x);
    void setAt(int x, T item);
    T getFirst();
    T getLast();
    void setFirst(T item);
    void setLast(T item);
}
