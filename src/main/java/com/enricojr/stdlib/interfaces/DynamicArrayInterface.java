package com.enricojr.stdlib.interfaces;

public interface DynamicArrayInterface<T> {
    void insertAt(int idx, T item);
    void deleteAt(int idx);
    void insertFirst(T item);
    void insertLast(T item);
    void deleteFirst();
    void deleteLast();
}
