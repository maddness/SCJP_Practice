package com.maddness.collections;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.WeakHashMap;

/**
 * Created by maddness on 15/03/2016.
 */
public class HashMapTest {
    @Test
    public void testOrderByHashCode() throws Exception {
        Map<Integer, String> map = Maps.newHashMap();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");

        System.out.println(map);
    }

    @Test
    public void testWeakHashMap() throws Exception {
        Map<Object, String> map = new WeakHashMap<>();
        Object o = new Object();
        map.put(o, "Hello");
        System.out.println("before GC " + map);

        o = null;
        System.gc();
        System.out.println("after GC " + map);
    }

    @Test
    public void testQueueEvenFirst() throws Exception {
        Queue<Integer> queue = new PriorityQueue<>((x, y) -> x % 2 - y % 2);
        queue.offer(2);
        queue.offer(1);
        queue.offer(7);
        queue.offer(4);
        queue.offer(3);
        queue.offer(44);
        queue.offer(9);

        System.out.println(queue);

    }
}