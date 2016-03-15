package com.maddness.concurrent;

import com.maddness.concurrent.tasks.BarrierTask;
import com.maddness.concurrent.tasks.CountdownTask;
import com.maddness.concurrent.tasks.SemaphoreTask;
import com.maddness.concurrent.tasks.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static com.maddness.concurrent.Account.newAccount;

public class Synchronizers {

    private static Logger LOG = LogManager.getLogger(Synchronizers.class);

    private static final Account a = newAccount(100);
    private static final Account b = newAccount(0);

    public static void main(String[] args) {
        runFutureTask();
        runSemaphore();
        runCountdownLatch();
        runCycicBarrier();
    }

    private static void runCycicBarrier() {
        CyclicBarrier barrier = new CyclicBarrier(5);

        LOG.info("Race begins...");

        ExecutorService service = Executors.newFixedThreadPool(10);
        IntStream.range(0, 5).forEach(
                i -> service.submit(new BarrierTask(barrier))
        );
        service.shutdown();

        sleep(10);

        LOG.info("Bye bye!!!");
        service.shutdownNow();
    }

    private static void runCountdownLatch() {
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService service = Executors.newFixedThreadPool(10);

        IntStream.range(0, 5).forEach(
                i -> service.submit(new CountdownTask(latch))
        );

        service.shutdown();

        LOG.info("Waiting...");
        sleep(3);
        LOG.info("Starting!...");

        latch.countDown();

    }

    private static void runFutureTask() {

        FutureTask<Boolean> task = new FutureTask<>(new Transfer(a, b, 50));
        task.run();

        try {
            LOG.info(task.get());

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Main thread interrupted: " + e.getMessage());
        }
    }

    private static void runSemaphore() {

        Semaphore semaphore = new Semaphore(3);

        ExecutorService service = Executors.newFixedThreadPool(20);

        IntStream.range(0, 10).forEach(
                i -> service.submit(new SemaphoreTask(semaphore))
        );

        service.shutdown();

    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
