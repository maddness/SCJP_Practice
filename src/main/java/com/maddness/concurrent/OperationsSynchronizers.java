package com.maddness.concurrent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static com.maddness.concurrent.Account.newAccount;

public class OperationsSynchronizers {

    private static Logger LOG = LogManager.getLogger(OperationsSynchronizers.class);

    private static void useFutureTask() {
        Account a = newAccount(500);
        Account b = newAccount(0);

        FutureTask<Boolean> task = new FutureTask<>(new Transfer(a, b, 50));
        task.run();

        try {
            LOG.info(task.get());

        } catch (InterruptedException | ExecutionException e) {
            LOG.error("Main thread interrupted: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

//        useFutureTask();

    }

}
