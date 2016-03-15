package com.maddness.concurrent.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by maddness on 15/03/2016.
 */
public class BarrierTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(BarrierTask.class);

    private final CyclicBarrier barrier;

    public BarrierTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public void run() {
        try {
            while (true) {
                barrier.await();

                LOG.info("Thread " + Thread.currentThread().getId() + " is running...");
                Thread.sleep(new Random().nextInt(5000));
                LOG.info("Thread " + Thread.currentThread().getId() + " is done.");

            }
        } catch (InterruptedException | BrokenBarrierException e) {
            LOG.error("I was killed!");
        }

    }
}
