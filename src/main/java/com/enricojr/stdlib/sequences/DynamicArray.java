package com.enricojr.stdlib.sequences;

import com.enricojr.stdlib.interfaces.DynamicSequenceInterface;
import com.enricojr.stdlib.interfaces.StaticSequenceInterface;
import com.enricojr.stdlib.iterators.StaticSequenceIterator;

public class DynamicArray<T> implements 
    StaticSequenceInterface<T>, DynamicSequenceInterface<T>, Iterable<T> {
    private float growFactor = 0.75f;
    private float shrinkFactor = 0.25f;

    // how many items are actually in it
    private int itemCount;

    // the array itself
    private StaticArray<T> internal;


    /**
     * The dynamic array is an array that automatically resizes itself as it fills up. It 
     * overallocates on initialization to enable O(1) .insertAt and .insertLast(), and when enough
     * items have been removed from the array, it will shrink to maintain space efficiency.<br/>
     * 
     * On initialization, if args.length is less than 16, an internal array with 16 slots will be 
     * created. This is the minimum number of slots a dynamic array can have and it will go no 
     * lower.<br/>
     *
     * Each time an insert / remove is performed, the loadFactor will be calculated (according to 
     * this.itemCount / this.internal.len()) and checked against the growFactor (default 0.75f) or 
     * shrinkFactor (default 0.25f) respectively. itemCount is incremented pre-emptively, and if 
     * the resulting loadFactor meets and exceeds or meets and falls below the respective 
     * growFactor/shrinkFactor, a resize is triggered.<br/>
     * 
     * On resize, a new internal array will be allocated with (this.itemCount * 2) spaces. 
     * Depending on when this is called it will result in an array that is larger than or smaller 
     * than the current.<br/>
     * 
     * @param args
     */
    public DynamicArray() {
        // shoudl have no fewer than 16 slots
        this.internal = new StaticArray<T>(16);
    }

    public DynamicArray(T... args) {
        int totalSpaces = 0;
        if (args.length < 16) {
            // no fewer than 16 slots.
            totalSpaces = 16;
        } else {
            // otherwise, allocate double args.length;
            totalSpaces = args.length * 2;
        }

        this.internal = new StaticArray<T>(totalSpaces);

        for (int i = 0; i < args.length; i++) {
            this.internal.setAt(i, args[i]);
        }

        this.itemCount = args.length;
    }

    /**
     * Returns the size of the internal static array. Will always be greater than the value 
     * returned by len(). Made available only for debugging / inspection purposes shouldn't need to 
     * be used otherwise.
     * 
     * @return integer representing the size of the internal array
     */
    public int getArraySize() {
        return this.internal.len();
    }

    /**
     * Returns an iterator that makes these arrays usable with Java's "enhanced" for syntax.
     */
    @Override
    public StaticSequenceIterator<T> iterator() {
        return new StaticSequenceIterator<>(this);
    }

    /**
     * Returns the number of items stored in the array. Will always be less than the number 
     * returned by getArraySize().
     * 
     * @return integer, representing the number of items in the array.
     */
    @Override
    public int len() {
        return this.itemCount;
    }

    /**
     * Returns the item at position x in the array. Will throw an ArrayIndexOutOfBoundsException if 
     * x is greater than this.itemCount - 1, as the internal array is larger than the number of 
     * items stored.
     */
    @Override
    public T getAt(int x) {
        if (x > this.itemCount - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return this.internal.getAt(x);
    }

    /**
     * Inserts 'item' at position 'x' in the array, overwriting the original. 
     */
    @Override
    public void setAt(int x, T item) {
        this.internal.setAt(x, item);
    }

    /**
     * Inserts 'item' at position 'idx' within the array. Will call insertFirst() or insertLast()
     * instead based on the value of 'idx'. Will trigger an array resize if the (itemCount + 1 / 
     * this.internal.len()) load factor is greater than or equal to the grow factor.
     */
    @Override
    public void insertAt(int idx, T item) {
        this.itemCount += 1;
        this.grow();
        this.shiftForward(idx);
        this.internal.setAt(idx, item);
    }

    /**
     * Inserts item at the beginning of the array i.e. index 0. Shifts all items forwards by one. 
     * Will trigger a resize if the resulting load factor would exceed the configured grow factor.
     */
    @Override
    public void insertFirst(T item) {
        this.insertAt(0, item);
    }

    /**
     * Inserts item at the end of the array, i.e. array[this.itemCount - 1]. Note that because the 
     * dynamic array overallocates, (this.itemCount - 1) will not be the logical end of the array.
     */
    @Override
    public void insertLast(T item) {
        this.insertAt(this.itemCount, item);
    }

    /**
     * Removes item at position idx in array, shifting all elements backwards by one space. Will 
     * trigger a resize if the total number of items remaining is equal to or below the specified 
     * shrinkFactor.
     */
    @Override
    public void deleteAt(int idx) {
        this.shiftBackward(idx);
        this.itemCount -= 1;
        this.shrink();
    }

    /**
     * Removes item at the beginning of the array, shifting all remaining elements back by one 
     * space. Wraps a call to this.deleteAt(0), and will behave the same way.
     */
    @Override
    public void deleteFirst() {
        this.deleteAt(0);
    }

    /**
     * Removes item at the logical end of the array. Wraps a call to this.deleteAt(this.itemCount - 
     * 1), and will behave the same way.
     */
    @Override
    public void deleteLast() {
        // special case: final element doesn't have anything past it that needs shifting back
        this.deleteAt(this.itemCount - 1);
    }

    /**
     * Called by methods that add to the array. This will check the load factor and trigger a 
     * resize appropriately.
     */
    private void grow() {
        // NOTE: It completely slipped my mind that integer division that results in a value
        //       < 1 will get truncated to 0, so I need to cast to float() to get any meaningful
        //       result.
        float loadFactor = (float) this.itemCount / (float) this.internal.len();
        if (loadFactor >= growFactor) {
            this.resize();
        }
    }

    /**
     * Called by methods that delete from the array. This will check the load factor and trigger a 
     * resize appropriately.
     */
    private void shrink() {
        float loadFactor = (float) this.itemCount / (float) this.internal.len();
        if (loadFactor <= shrinkFactor) {
            this.resize();
        }
    }

    /**
     * Resizes dynamic array by creating a new internal array whose size is equal to itemCount * 2.
     * Depending on when this is called, it will result in an array that is either larger than or 
     * smaller than the current. 
     */
    private void resize() {
        int newArraySize = this.itemCount * 2;

        // hard lower bound on the size of the array because I'm pretty sure performance will 
        // degrade if its too small (resizes will occur too often).
        if (newArraySize < 16) {
            newArraySize = 16;
        }
    
        // TODO: Find a way to guard against this, otherwise suppress the warning.
        StaticArray<T> newArray = new StaticArray(newArraySize);

        for (int i = 0; i < this.itemCount; i++) {
            newArray.setAt(i, this.internal.getAt(i));
        }

        this.internal = newArray;
    }

    private void shiftForward(int startingIdx) {
        for (int i = this.internal.len() - 2; i >= 0; i--) {
            this.internal.setAt(i + 1, this.internal.getAt(i));
        }
    }

    private void shiftBackward(int startingIdx) {
        for (int i = startingIdx; i < itemCount; i++) {
            this.internal.setAt(i, this.internal.getAt(i + 1));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("DynamicArray");
        sb.append("(" + this.itemCount + "/" + this.internal.len() + ")");
        sb.append("[");
        for (int i = 0; i < this.itemCount; i++) {
            sb.append(this.internal.getAt(i).toString());
            sb.append(", ");
        }
        sb.append("]");

        return sb.toString();
    }
}
