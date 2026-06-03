package com.enricojr.stdlib.sets;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import com.enricojr.stdlib.interfaces.SetInterface;
import com.enricojr.stdlib.sequences.LinkedList;
import com.enricojr.stdlib.sequences.LinkedListNode;

/**
 * TODO: Revisit this whole class - a "set" as defined in the material is _ordered_ but this
 * can't have order on it's own because hashing distributes values into buckets in a random, 
 * unpredictable fashion.
 * 
 * Order needs to be determined and maintained some other way (radix sort?)
 * 
 * This resembles Python's set() which is just a dictionary with only keys, and the only real
 * advantage that it offers is O(1) lookup time, and O(1) insert.
 */
public class HashedSet<T extends Comparable<T>> 
    implements SetInterface<T>, Iterable<LinkedList<T>> {
    private LinkedList<T>[] internal;

    // Both are completely arbitrary values right now, can't see any reason to make one or the other
    // a specific size.
    private final int defaultLength = 16;
    // private final int maxChainLength = 7;

    // as in, the number of items in the set
    private int itemCount;
    // as in, the number of chains that have been instantiated and have one or more in it.
    private int chainCount;

    /**
     * HashedSet is an unordered collection of items, based on some property of the item. All items 
     * must implement Comparable<T> to be used with this data structure.
     */
    @SuppressWarnings("unchecked")
    public HashedSet(Class<T> type) {
        this.internal = (LinkedList<T>[]) Array.newInstance(LinkedList.class, this.defaultLength);
    }

    @SuppressWarnings("unchecked")
    public HashedSet(Class<T> type, T[] args) {
        this.internal = (LinkedList<T>[]) Array.newInstance(LinkedList.class, args.length);
        for (T item : args) {
            this.insert(item);
        }
    }

    // TODO: some of the items returned here end up being null. fix/dont fix? idk
    public Iterator<LinkedList<T>> iterator() {
        // NOTE: am I just overcomplicating this? Maybe not work so late.
        return new Iterator<LinkedList<T>>() {
            private int idxPtr = 0;

            @Override
            public boolean hasNext() {
                if (this.idxPtr != HashedSet.this.internal.length) {
                    return true;
                }
                return false;
            }

            @Override
            public LinkedList<T> next() {
                LinkedList<T> chain = null;
                try {
                    chain = HashedSet.this.internal[idxPtr];
                } catch (NullPointerException e) {
                    // do nothing?
                }
                this.idxPtr += 1;
                return chain;
            }
        };
    }

    @Override
    public T delete(T item) {
        int bucketNumber = this.universalHash(item.hashCode(), this.internal.length);
        LinkedList<T> chain = this.internal[bucketNumber];

        int ptr = 0;
        LinkedListNode<T> node = chain.getHead();
        while (ptr != chain.len()) {
            if (item.equals(node.getItem())) {
                break;
            }
            node = node.getNext();
            ptr += 1;
        }

        chain.deleteAt(ptr);
        return node.getItem();
    }

    /** 
     * Returns the item from the set matching the one provided, or null. Semantically similar to a 
     * "contains" operation. 
     * 
     * Runs in O(1) time amortized because we hash to get the bucket and walk a chain to find the
     * item.
     */
    @Override
    public T find(T item) {
        int bucketNumber = Objects.hashCode(item) % this.internal.length;
        T target = null;
        try {
            LinkedList<T> chain = this.internal[bucketNumber];
            for (T obj : chain) {
                if (obj.equals(item)) {
                    target = obj;
                }
            } 
        } catch (NullPointerException e) {
            // do nothing
        }
        return target;
    }

    // TODO: re-think the interface. This is basically MapInterface with <K> instead of <K, V>
    @Override
    public T findMax() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T findMin() {
        throw new UnsupportedOperationException();
    }

    @Override
    public T findNext(T item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T findPrev(T item) {
        throw new UnsupportedOperationException();
    }

    // NOTE: The .hashCode() of an integer is the integer itself.
    // NOTE: int % 5 will be 0 for everything that ends in 5. >_<
    /**
     * Inserts an item into the set. Runs in O(1) time.
     */
    @Override
    public void insert(T item) {
        int bucketNumber = this.universalHash(item.hashCode(), this.internal.length);
        LinkedList<T> chain;
        try {
            chain = this.internal[bucketNumber];
            chain.insertLast(item);
            this.itemCount += 1;
        } catch (NullPointerException e) {
            LinkedList<T> newChain = new LinkedList<T>();
            newChain.insertLast(item);
            this.internal[bucketNumber] = newChain;
            this.itemCount += 1;
            this.chainCount += 1;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("lol wtf happened here?");
            e.printStackTrace();
        }
    }

    @Override
    public int len() {
        return this.itemCount;
    }

    public String toString() {
        String indent = "-";
        StringBuilder sb = new StringBuilder();
        sb.append("HashedSet[\n");
        for (LinkedList<T> chain : this) {
            if (chain != null) {
                sb.append(indent.repeat(2) + chain + "\n");
            } else {
                sb.append("<null> \n");
            }
        }

        sb.append("]");
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(internal);
        result = prime * result + defaultLength;
        result = prime * result + itemCount;
        result = prime * result + chainCount;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        HashedSet<T> other = (HashedSet<T>) obj;
        if (!Arrays.equals(internal, other.internal))
            return false;
        if (defaultLength != other.defaultLength)
            return false;
        if (itemCount != other.itemCount)
            return false;
        if (chainCount != other.chainCount)
            return false;
        return true;
    }

    /** 
     * An approximation of a universal hash function. Just using item.hashCode() is not sufficient 
     * because for stuff like Integers the hashCode() of an Integer is the Integer itself, and that 
     * does not make for an even distribution across buckets.
     * 
     * The test `TestHashedSet.testStaticLinkedListArrayIteration()` instantiates an array of 9999
     * integers and inserts them into a HashedSet, the resulting distribution basically has each    * element in its own bucket (i.e. 9999 buckets each with 1 element). I think that means it's 
     * working.
     */
    private int universalHash(int itemHashCode, int length) {
        final int prime1 = 31;
        final int prime2 = 47;
        final int prime3 = Integer.MAX_VALUE;
        int result = 1;
        result = ((prime1 * itemHashCode + prime2) % prime3) % length;
        return result;
    }
}
