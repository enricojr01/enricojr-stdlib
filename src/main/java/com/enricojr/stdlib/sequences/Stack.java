package com.enricojr.stdlib.sequences;

public class Stack<T> {
    private DynamicArray<T> internal;

    public Stack(Class<T> type) {
        this.internal = new DynamicArray<T>(type);
    }

    public Stack(Class<T> type, T item) {
        this.internal = new DynamicArray<>(type);
        this.push(item);
    }

    public int len() {
        return this.internal.len();
    }

    public void push(T item) {
        this.internal.insertLast(item);
    }
    
    public T pop() {
        T item = this.internal.getLast();
        this.internal.deleteLast();
        return item;
    }

    public T peek() {
        T item = this.internal.getLast();
        return item;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stack[");
        for (T item : this.internal) {
            sb.append(item + ", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
