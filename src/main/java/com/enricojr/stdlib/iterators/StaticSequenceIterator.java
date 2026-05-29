package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.interfaces.StaticSequenceInterface;

public class StaticSequenceIterator<T> implements Iterator<T> {
    private StaticSequenceInterface<T> sequence;
    private int index;

    public StaticSequenceIterator(StaticSequenceInterface<T> seq) {
        this.sequence = seq;
    }

    @Override
    public boolean hasNext() {
        if (this.index < this.sequence.len()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        T item = this.sequence.getAt(this.index);
        this.index += 1;
        return item;
    }


}
