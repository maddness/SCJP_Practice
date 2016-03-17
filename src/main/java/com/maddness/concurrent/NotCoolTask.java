package com.maddness.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * Created by maddness on 17/03/2016.
 */
public class NotCoolTask {
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        IntStream.range(1, 102).forEach(list::add);

        System.out.println(countSum(list, 9));
    }

    public static int countSum(List<Integer> list, int threads) {
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("List can't be null or empty");
        }

        int listSize = list.size();
        int range = listSize / threads;

        if (range == 0) {
            throw new IllegalArgumentException("Threads count can't be more than list elements");
        }

        List<Callable<Integer>> tasks = new ArrayList<>();

        int startIndex = 0;
        int endIndex = range;

        while (endIndex < listSize) {
            tasks.add(new Counter(list, startIndex, endIndex));
            startIndex = endIndex;
            endIndex += range;
        }

        tasks.add(new Counter(list, startIndex, listSize));

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        int result = 0;

        try {
            List<Future<Integer>> futures = executorService.invokeAll(tasks);

            for (Future<Integer> future : futures) {
                result += future.get();
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        executorService.shutdown();

        return result;
    }
}

class Counter implements Callable<Integer> {
    private final List<Integer> list;
    private final int startIndex;
    private final int endIndex;

    public Counter(List<Integer> list, int startIndex, int endIndex) {
        this.list = list;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public Integer call() throws ExecutionException {
        System.out.println("Thread " + Thread.currentThread().getId() +
                ", elements: " + (endIndex - startIndex));

        int result = 0;

        for (int i = startIndex; i < endIndex; i++) {
            result += list.get(i);
        }

        return result;
    }
}


