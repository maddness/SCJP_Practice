package com.maddness.concurrent;

/**
 * Created by maddness on 17/03/2016.
 */
public class PingPong {
    public static void main(String[] args) {

        Object lock = new Object();
        Gamer one = new Gamer("ping", lock);
        Gamer two = new Gamer("pong", lock);

        new Thread(one).start();
        new Thread(two).start();

    }
}

class Gamer implements Runnable {
    private String text;
    private Object lock;

    public Gamer(String text, Object lock) {
        this.text = text;
        this.lock = lock;
    }

    public void run() {
        while (true) {
            synchronized (lock) {
                try {

                    lock.notify();
                    lock.wait();
                    System.out.println(text);

                } catch (InterruptedException e) {
                    System.out.println(text + " is dead, damn...");
                }
            }
        }
    }
}