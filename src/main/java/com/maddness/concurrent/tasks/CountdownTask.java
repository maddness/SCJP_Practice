package com.maddness.concurrent.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;

/**
 * Created by maddness on 15/03/2016.
 */
public class CountdownTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(CountdownTask.class);

    private final CountDownLatch latch;

    public CountdownTask(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {

            latch.await();

            LOG.info("Thread " + Thread.currentThread().getId() + " is running...");
            Thread.sleep(5000);
            LOG.info("Thread " + Thread.currentThread().getId() + " is done.");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
