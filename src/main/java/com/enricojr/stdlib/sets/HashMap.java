package com.enricojr.stdlib.sets;

import java.util.Objects;
import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.sequences.LinkedList;
import com.enricojr.stdlib.sequences.StaticArray;

public class HashMap<K, T extends Comparable<T>> implements SetInterface<T> {
    private StaticArray<LinkedList<T>> internal = new StaticArray<>(16);
    private int numberOfBuckets = 0;
    private float loadFactor = 0.75f;

    public HashMap() {}

    @Override
    public T delete(T item) {
        return null;
    }

    @Override
    public T find(T item) {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public T findNext(T item) {
        return null;
    }

    @Override
    public T findPrev(T item) {
        return null;
    }

    @Override
    public void insert(T item) {
        int bucketNo = Objects.hashCode(item) % this.internal.len();
        LinkedList<T> bucket = this.internal.getAt(bucketNo);
        if (bucket == null) {
            LinkedList<T> newBucket = new LinkedList<T>();
            newBucket.insertFirst(item);
            this.internal.setAt(bucketNo, newBucket);
            this.numberOfBuckets += 1;
        } else {
            bucket.insertLast(item);
        }
    }

    @Override
    public int len() {
        return 0;
    }

    @Override
    public void sort() {
        
    }

    private void grow() {
        if ((float)this.internal.len() / (float)(this.numberOfBuckets) >= 0.75) {
            this.resize();
            this.rehash();
        }
    } 

    private void resize() {}
    private void rehash() {}

}
