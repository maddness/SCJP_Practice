package com.maddness.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static com.maddness.concurrent.Account.newAccount;

/**
 * Created by maddness on 14/03/2016.
 */
public class OperationsExecutors {

    private static final Logger LOG = LogManager.getLogger(OperationsExecutors.class);

    public static ThreadLocal<Integer> taskId = new ThreadLocal<Integer>() {
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) throws InterruptedException {

        final Account a = newAccount(1500);
        final Account b = newAccount(0);


        // Scheduled executors
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.scheduleAtFixedRate(() -> LOG.info("=== Fails in A: " + a.getFailCounter()), 0, 1, TimeUnit.SECONDS);

        // Executor pool
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        List<Transfer> transfers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            transfers.add(new Transfer(a, b, new Random().nextInt(20)));
        }

        List<Future<Boolean>> results = Collections.emptyList();
        try {
            results = executorService.invokeAll(transfers);

        } catch (InterruptedException e) {
            LOG.error("Main thread is interrupted!");
            e.printStackTrace();
        }

        // always run! - closing new tasks addition
        executorService.shutdown();


//        only for submits()
//        executorService.awaitTermination(60, TimeUnit.SECONDS);

        LOG.info(a.toString());
        LOG.info(b.toString());

        for (Future<Boolean> result: results) {
            try {
                LOG.info(result.get());
            } catch (InterruptedException | ExecutionException e) {
                LOG.error("No funds!");
            }
        }

        scheduledExecutor.shutdown();

    }

}
