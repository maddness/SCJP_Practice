package com.maddness.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import static com.google.common.collect.Lists.newArrayList;
import static java.lang.System.currentTimeMillis;
import static java.util.Collections.synchronizedList;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.stream.IntStream.range;
import static utils.TextUtils.print;

public class CopyOnWriteArrayTesting {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<Integer> base = new ArrayList<>(100000);
        fillList(base);

        List<Integer> normalList = synchronizedList(base);
        List<Integer> synchList = new CopyOnWriteArrayList<>();
        synchList.addAll(base);

        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = newFixedThreadPool(2);
        service.submit(new ListChecker(normalList, 0, 50000, latch));
        service.submit(new ListChecker(normalList, 50000, 100000, latch));
        service.shutdown();

        print("starting...");
        long timeStart = currentTimeMillis();
        latch.countDown();
        service.awaitTermination(1, MINUTES);
        long duration = currentTimeMillis() - timeStart;

        print("Time for processing: " + duration + " ms");
    }

    private static void fillList(List<Integer> list) {
        range(1, 100001).forEach(list::add);
    }
}

class ListChecker implements Runnable {
    private final List<Integer> list;
    private final int startIndex;
    private final int endIndex;

    private final CountDownLatch latch;

    public ListChecker(List<Integer> list, int startIndex, int endIndex, CountDownLatch latch) {
        this.list = list;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();

            List<Integer> temp = newArrayList();
            range(startIndex, endIndex).forEach(i -> temp.add(list.get(i)));

        } catch (InterruptedException e) {
            print("Thread is interrupted.");
        }
    }
}




