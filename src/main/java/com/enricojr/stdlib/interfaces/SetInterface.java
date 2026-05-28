package com.enricojr.stdlib.interfaces;

public interface SetInterface<T extends Comparable<T>> {
    int len();
    T find(T item);
    void insert(T item);
    T delete(T item);
    T findMin();
    T findMax();
    T findNext(T item);
    T findPrev(T item);
    void sort();
}
