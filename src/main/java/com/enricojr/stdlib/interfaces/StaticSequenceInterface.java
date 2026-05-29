package com.enricojr.stdlib.interfaces;

public interface StaticSequenceInterface<T> {
    int len();

    T getAt(int idx);
    void setAt(int idx, T item);
}
