package com.enricojr.stdlib.sorters;

public class InsertionSorter<T extends Comparable<T>> {
    private T[] internal;

    public InsertionSorter(T[] sequence) {
        this.internal = sequence;
        this.sort();
    }

    public T[] getInternal() {
        return this.internal;
    }

    public void sort() {
        for (int i = 0; i < this.internal.length - 1; i++) {
            int smallest = i;
            for (int j = i + 1; j < this.internal.length; j++) {
                T obj1 = this.internal[smallest];
                T obj2 = this.internal[j];

                if (obj2.compareTo(obj1) < 0) {
                    smallest = j;

                }
            }

            T temp = this.internal[smallest];
            this.internal[smallest] = this.internal[i];
            this.internal[i] = temp;
        }
    }
}
