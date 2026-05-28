package com.enricojr.stdlib;

import com.enricojr.stdlib.interfaces.DynamicArrayInterface;
import com.enricojr.stdlib.interfaces.SequenceInterface;

public class DynamicArray<T> implements SequenceInterface<T>, DynamicArrayInterface<T> {
    // I don't really know how big to make these so I'm just picking a number out of thin air.
    private int chunkSize = 8;

    // how big the array is, i.e how much space allocated
    private int arraySize;

    // how many items are actually in it
    private int itemCount;

    // the array itself
    private StaticArray<T> internal;

    public DynamicArray(T... args) {
        int totalSpaces = args.length + this.chunkSize - (args.length % this.chunkSize);
        // NOTE: should have no fewer than 8 slots.
        if (totalSpaces < 8) {
            totalSpaces = 8;
        }
        this.internal = new StaticArray<T>(totalSpaces);

        for (int i = 0; i < args.length; i++) {
            this.internal.setAt(i, args[i]);
        }

        this.itemCount = args.length;
        this.arraySize = totalSpaces;
    }

    public int getArraySize() {
        return this.arraySize;
    }

    public int getChunkSize() {
        return this.chunkSize;
    }

    public int getInternalArrayLength() {
        return this.internal.len();
    }

    @Override
    public int len() {
        return this.itemCount;
    }

    @Override
    public T getAt(int x) {
        return this.internal.getAt(x);
    }

    @Override
    public void setAt(int x, T item) {
        throw new UnsupportedOperationException("Call insertAt() instead!");
    }

    @Override
    public void setFirst(T item) {
        this.setAt(0, item);
        
    }

    @Override
    public void setLast(T item) {
        this.setAt(this.itemCount - 1, item);
    }

    @Override
    public void insertFirst(T item) {
        // check to see if we have room i.e. if adding 1 to this array forces us to resize
        if (this.itemCount + 1 >= this.internal.len() - 1) {
            this.resize();
        }

        // move everything up by one
        this.shiftForward(1);

        // insert item into index 0, overwriting its contents.
        this.internal.setAt(0, item);

        // increment itemCount to reflect newly added item.
        this.itemCount += 1;
    }

    @Override
    public void insertLast(T item) {
        // check to see if adding 1 to this array requires that we resize.
        if (this.itemCount + 1 >= this.internal.len()) {
            this.resize();
        } 

        // nothing needs to be shifted forward or back because we are adding to the end
        this.internal.setAt(this.itemCount, item);
        this.itemCount += 1;
    }

    @Override
    public void insertAt(int idx, T item) {
        if (this.itemCount + 1 >= this.internal.len() - 1) {
            this.resize();
        }

        this.shiftForward(idx);
        this.internal.setAt(idx, item);
        this.itemCount += 1;
    }

    @Override
    public T getFirst() {
        return this.getAt(0);
    }

    @Override
    public T getLast() {
        return this.getAt(this.itemCount - 1);
    }

    @Override
    public void deleteFirst() {
        this.deleteAt(0);
    }

    @Override
    public void deleteLast() {
        // special case: final element doesn't have anything past it that needs shifting back
        this.deleteAt(itemCount);
    }


    @Override
    public void deleteAt(int idx) {
        // move all elements from idx onwards back one space, thereby erasing this.internal[idx].
        this.shiftBackward(idx);
        this.itemCount -= 1;

        // resize the array if the total itemCount is 25% of the current arraySize
        if (this.itemCount <= Math.floorDiv(this.arraySize, 4)) {
            this.resize();
        }
    }

    private void resize() {
        // SPEC: for growing - grow to next multiple of chunkSize whenever internal is full.
        // SPEC: for shrinking - whenever itemCount is 25% of arraySize, resize to the next highest
        //       multiple of 8 based on itemCount, thereby shrinking the array.
        // SPEC: Both of the above will be checked outside the call to resize()
        int newArraySize = (this.itemCount + 1) + this.chunkSize - ((this.itemCount + 1) % this.chunkSize);

        // TODO: Find a way to guard against this, otherwise suppress the warning.
        StaticArray<T> newArray = new StaticArray(newArraySize);

        for (int i = 0; i < this.itemCount; i++) {
            newArray.setAt(i, this.internal.getAt(i));
        }

        this.arraySize = newArraySize;
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
        sb.append("(" + this.itemCount + "/" + this.arraySize + ")");
        sb.append("[");
        for (int i = 0; i < this.itemCount; i++) {
            sb.append(this.internal.getAt(i).toString());
            sb.append(", ");
        }
        sb.append("]");

        return sb.toString();
    }
}
