package com.enricojr.stdlib.interfaces;

public interface SetInterface<T> {
    T find(T item);
    void insert(T item);
    T delete(T item);
    T find_min();
    T find_max();

}
