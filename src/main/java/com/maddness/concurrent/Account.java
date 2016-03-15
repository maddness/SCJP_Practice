package com.maddness.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by maddness on 14/03/2016.
 */
public class Account {
    private static int idCount = 1;

    private final Lock lock = new ReentrantLock();
    private final int id;

    private int balance;
    private AtomicInteger failCounter = new AtomicInteger(0);

    private Account(int balance) {
        this.balance = balance;
        this.id = idCount;
        idCount++;
    }

    public static Account newAccount(int amount) {
        return new Account(amount);
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    public int getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }

    public int getId() {
        return id;
    }

    public AtomicInteger getFailCounter() {
        return failCounter;
    }

    public int incrementFailCounter() {
        return failCounter.incrementAndGet();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", failCounter=" + failCounter +
                '}';
    }
}
