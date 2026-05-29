package com.enricojr.stdlib.interfaces;

public interface SequenceInterface<T> {
    int len();

    // static 
    T getAt(int x);
    void setAt(int x, T item);

    // dynamic methods
    void insertAt(int idx, T item);
    void deleteAt(int idx);
    void insertFirst(T item);
    void insertLast(T item);
    void deleteFirst();
    void deleteLast();
}
