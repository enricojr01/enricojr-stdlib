package com.enricojr.stdlib.iterators;

import java.util.Iterator;
import com.enricojr.stdlib.interfaces.SequenceInterface;

public class SequenceIterator<T> implements Iterator<T> {
    private SequenceInterface<T> sequence;
    private int index;

    public SequenceIterator(SequenceInterface<T> seq) {
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
