package com.maddness.concurrent;

import java.util.Iterator;

/**
 * @author aostrikov
 */
public class EvenIterator implements Iterator<Integer>{

    private final Iterator<Integer> iterator;

    private Integer cachedVal = null;

    public EvenIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    public synchronized Integer next() {
        if (hasNext()) {
            Integer tmp = cachedVal;
            cachedVal = null;
            return tmp;
        } else {
            return iterator.next();
        }
    }

    public synchronized boolean hasNext() {
        if (cachedVal != null) {
            return true;
        }
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next % 2 == 0) {
                cachedVal = next;
                return true;
            }
        }
        return false;
    }
}
