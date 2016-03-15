package com.maddness.concurrent.tasks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.Semaphore;

/**
 * Created by maddness on 15/03/2016.
 */
public class SemaphoreTask implements Runnable {

    private static final Logger LOG = LogManager.getLogger(SemaphoreTask.class);

    private final Semaphore semaphore;

    public SemaphoreTask(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        try {

            semaphore.acquire();

            LOG.info("Thread " + Thread.currentThread().getId() + " is running...");
            Thread.sleep(5000);
            LOG.info("Thread " + Thread.currentThread().getId() + " is done.");


        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            semaphore.release();
        }
    }
}
