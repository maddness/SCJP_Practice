package com.maddness.collections;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * Created by maddness on 15/03/2016.
 */
public class Base {
    public static void main(String[] args) {


        navigableSetMagic();

    }

    public static void navigableSetMagic() {
        NavigableSet<Integer> set = new TreeSet<>();

        IntStream.range(1, 11).forEach(set::add);

        // element that lower/higher
        System.out.println(set.lower(5));
        System.out.println(set.higher(5));

        // collections that lower/higher
        System.out.println(set.headSet(5));
        System.out.println(set.tailSet(5));

    }

    public static Integer getNextElem(Integer elem, SortedSet set) {
        Iterator<Integer> iterator = set.iterator();

        while (iterator.hasNext()) {
            Integer next = iterator.next();
            if (next.equals(elem)) {
                return iterator.hasNext() ? iterator.next() : -1;
            }
        }
        return -1;
    }

}
