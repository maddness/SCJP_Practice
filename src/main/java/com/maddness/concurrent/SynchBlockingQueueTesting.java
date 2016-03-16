package com.maddness.concurrent;

import java.util.Random;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;
import static utils.TextUtils.print;

public class SynchBlockingQueueTesting {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> queue = new SynchronousQueue<>();

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(new Producer(queue, "producer"));
        service.submit(new Consumer(queue, "consumer"));
        service.shutdown();

        Thread.sleep(10 * 1000);
        service.shutdownNow();
    }
}

class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final String name;

    Producer(BlockingQueue<Integer> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                int value = new Random().nextInt(10);
                sleep(500);
                print("Transfering value " + value);
                queue.put(value);
            }
        } catch (InterruptedException e) {
            print("Thread [" + name + "] was interrupted.");
        }
    }

}

class Consumer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final String name;

    Consumer(BlockingQueue<Integer> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Integer value = queue.take();
                print("Got the value " + value);
            }
        } catch (InterruptedException e) {
            print("Thread [" + name + "] was interrupted.");
        }
    }
}