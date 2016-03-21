package com.maddness.concurrent;

import com.maddness.concurrent.tasks.InsufficientFundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.Lock;

import static com.maddness.concurrent.Account.newAccount;
import static java.util.concurrent.TimeUnit.*;

/**
 * Created by maddness on 14/03/2016.
 */
public class OperationsStoneAge {

    private static final Logger LOG = LogManager.getLogger(OperationsStoneAge.class);

    public static void transfer(Account acc1, Account acc2, int amount) throws InsufficientFundsException, InterruptedException {
        if (acc1.getBalance() < amount) {
            throw new InsufficientFundsException();
        }

        Lock lock1 = acc1.getLock();
        Lock lock2 = acc2.getLock();

        if (lock1.tryLock(30, SECONDS)) {
            try {
                Thread.sleep(2000);

                if (lock2.tryLock(30, SECONDS)) {
                    try {

                        acc1.withdraw(amount);
                        acc2.deposit(amount);

                        LOG.info("Transfer of " + amount + " completed from " + acc1.getId() + " to " + acc2.getId());

                    } finally {
                        lock2.unlock();
                    }
                } else {
                    acc2.incrementFailCounter();
                    LOG.error("cant lock on account2");
                }

            } finally {
                lock1.unlock();
            }
        } else {
            acc1.incrementFailCounter();
            LOG.error("cant lock on account1");
        }




    }

    public static void main(String[] args) {

        final Account a = newAccount(100);
        final Account b = newAccount(100);

        new Thread(() -> {
            try {
                transfer(b, a, 50);

            } catch (InsufficientFundsException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {

            try {
                transfer(a, b, 50);

            } catch (InsufficientFundsException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
