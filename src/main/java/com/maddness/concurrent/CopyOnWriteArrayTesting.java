package com.maddness.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.System.nanoTime;
import static java.util.Collections.synchronizedList;
import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.stream.IntStream.range;
import static utils.TextUtils.print;

public class CopyOnWriteArrayTesting {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ArrayList<Integer> base = new ArrayList<>(10000);
        fillList(base);

        List<Integer> syncList = synchronizedList(base);
        List<Integer> copyOnWriteList = new CopyOnWriteArrayList<>();
        copyOnWriteList.addAll(base);

        testCollection(copyOnWriteList, "CopyOnWrite");
        testCollection(syncList, "Synchronized");
    }

    private static void testCollection(List<Integer> list, String listType) throws InterruptedException, ExecutionException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService service = newFixedThreadPool(2);
        Future<Long> result1 = service.submit(new ListChecker(list, 0, 5000, latch));
        Future<Long> result2 = service.submit(new ListChecker(list, 5000, 10000, latch));

        print("starting for " + listType + " ...");
        latch.countDown();

        print("thread1 duration: " + (result1.get() / 1000) + " mcs");
        print("thread2 duration: " + (result2.get() / 1000) + " mcs");
        print((result1.get() + result2.get()) / 1000 + " mcs");

        service.shutdown();
        print("");
    }

    private static void fillList(List<Integer> list) {
        range(1, 10001).forEach(list::add);
    }
}

class ListChecker implements Callable<Long> {
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
    public Long call() throws Exception {
        latch.await();

        long timeStart = nanoTime();
        for (int i = startIndex; i < endIndex; i++) {
            list.get(i);
        }
        return nanoTime() - timeStart;
    }
}




