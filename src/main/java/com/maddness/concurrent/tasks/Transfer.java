package com.maddness.concurrent.tasks;

import com.maddness.concurrent.Account;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;

import static com.maddness.concurrent.ExecutorsTypes.taskId;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by maddness on 14/03/2016.
 */
public class Transfer implements Callable<Boolean> {

    private static final Logger LOG = LogManager.getLogger(Transfer.class);

    private static final int LOCK_WAIT_SEC = 6;
    private static final int SLEEP_SES = 1;

    private final Account a;
    private final Account b;
    private final int amount;

    public Transfer(Account a, Account b, int amount) {
        this.a = a;
        this.b = b;
        this.amount = amount;
    }

    public Boolean call() throws Exception {
        taskId.set((int) Thread.currentThread().getId());
        LOG.info("Started task " + taskId.get());

        Lock lock1 = a.getLock();
        Lock lock2 = b.getLock();

        if (lock1.tryLock(LOCK_WAIT_SEC, SECONDS)) {
            try {
                checkBalance();
                if (lock2.tryLock(LOCK_WAIT_SEC, SECONDS)) {
                    try {

                        a.withdraw(amount);
                        b.deposit(amount);

                        goToRandomSleep();

                        LOG.info("Transfer of " + amount + " completed from " + a.getId() + " to " + b.getId());

                    } finally {
                        lock2.unlock();
                    }
                } else {
                    b.incrementFailCounter();
                    LOG.error("cant lock on account " + b.getId());
                    return false;
                }

            } finally {
                lock1.unlock();
            }
        } else {
            a.incrementFailCounter();
            LOG.error("cant lock on account " + a.getId());
            return false;
        }


        return true;
    }

    private void checkBalance() throws InsufficientFundsException {
        if (a.getBalance() < amount) {
            LOG.error("No funds on account " + a.getId() + "!");
            throw new InsufficientFundsException();
        }
    }

    private void goToRandomSleep() {
        try {
            Thread.sleep(new Random().nextInt(SLEEP_SES + 1) * 100);
        } catch (InterruptedException e) {
            LOG.error("Thread " + Thread.currentThread().getName() + " is interrupted!");
        }
    }
}
