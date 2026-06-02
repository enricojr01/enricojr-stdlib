package com.enricojr.stdlib.sets;

import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.iterators.StaticSequenceIterator;
import com.enricojr.stdlib.sequences.DynamicArray;

public class SetArray<T extends Comparable<T>> implements SetInterface<T>, Iterable<T> {
    private DynamicArray<T> internal;

    /**
     * Inefficient implementation of a simple Set based on the StaticArray. Done purely for 
     * educational purposes, please don't actually use this.
     * 
     * Works by pre-sorting the list on creation, and on every call to .insert().
     * 
     * Items in this set must implement the Comparable interface.
     * 
     * @param args
     */
    public SetArray(Class<T> type, T[] args) {
        this.internal = new DynamicArray<T>(type, args);
        this.sort();
    }

    /**
     * Returns an iterator of the internal array. Allows SetArray to be used with the
     * enhanced Java for syntax.
     * 
     * @return an instance of SequenceIterator<T>
     */
    public StaticSequenceIterator<T> iterator() {
        return this.internal.iterator();
    }

    /**
     * Standard implementation of selection sort. Sorts array in place and is called
     * as part of initialization and on every insert / remove. 
     * 
     * Runs in O(n^2) time.
     */
    public void sort() {
        // will this work!? who knows?
        for (int i = 0; i < this.internal.len() - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < this.internal.len(); j++) {
                T obj1 = this.internal.getAt(smallest);
                T obj2 = this.internal.getAt(j);

                if (obj2.compareTo(obj1) < 0) {
                    smallest = j;

                }
            }

            T temp = this.internal.getAt(smallest);
            this.internal.setAt(smallest, this.internal.getAt(i));
            this.internal.setAt(i, temp);
        }
    }

    /**
     * Helper method to see if the internal array is sorted. Used in testing, exposed to public 
     * just in case. 
     * 
     * Runs in O(n) time.
     * @return
     */
    public boolean isSorted() {
        for (int i = 0; i < this.internal.len() - 2; i++) {
            if (this.internal.getAt(i).compareTo(this.internal.getAt(i + 1)) > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public T delete(T item) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Returns the first element from the set matching `item`. Returns null if no match found.
     * 
     * Runs in O(n) time.
     */
    @Override
    public T find(T item) {
        for (T obj: this) {
            if (item.compareTo(obj) == 0) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Returns the largest element in the set. 
     * 
     * Runs in O(n) time.
     */
    @Override
    public T findMax() {
        T max = this.internal.getAt(0);
        for (T item : this) {
            if (max.compareTo(item) <= 0) {
                max = item;
            }
        }
        return max;
    }

    /**
     * Returns the smallest element in the set.
     * 
     * Runs in O(n) time.
     */
    @Override
    public T findMin() {
        T min = this.internal.getAt(0);
        for (T item : this) {
            if (min.compareTo(item) >= 0) {
                min = item;
            }
        }
        return min;
    }

    /**
     * Returns the first occurrence of `item` in this set. Returns null if no match found.
     * 
     * Runs in O(n) time.
     */
    @Override
    public T findNext(T item) {
        for (T obj : this) {
            if (obj.compareTo(item) > 0) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Returns the next smallest item in the set to `item`. Returns null if none found.
     * 
     * Runs in O(n) time.
     */
    @Override
    public T findPrev(T item) {
        for (T obj : this) {
            if (obj.compareTo(item) < 0) {
                return obj;
            }
        }
        return null;
    }

    /**
     * Inserts `item` into set at its sorted position. Works by inserting the element at the end, 
     * and calling .sort().
     * 
     * Runs in O(n^2) time because of the sort.
     */
    @Override
    public void insert(T item) {
        this.internal.insertLast(item);
        this.sort();
    }

    /**
     * Returns the number of items in the internal array.
     * 
     * Runs in O(1) time.
     */
    @Override
    public int len() {
        return this.internal.len();
    }

    /**
     * Returns a string representation of the set.
     * 
     * Runs in O(n) time.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetArray[");
        sb.append("(" + this.internal.len() + "/" + this.internal.getArraySize() + ")[");
        for (T item : this) {
            sb.append(item + ", ");
        }
        sb.append("]");
        return sb.toString();
    }

}
