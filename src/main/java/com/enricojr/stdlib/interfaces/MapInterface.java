package com.enricojr.stdlib.interfaces;

public interface MapInterface<K, V> {
    void len();
    V find(K key);
    void insert(K key, V value);
    void delete(K key, V value);
}
