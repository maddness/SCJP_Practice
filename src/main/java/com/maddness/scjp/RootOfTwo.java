package com.maddness.scjp;

/**
 * Created by maddness on 20/03/2016.
 */
public class RootOfTwo {
    public static void main(String[] args) throws InterruptedException {

        root(1,25);

    }

    public static void root(double k, double N) throws InterruptedException {
        double step = k;
        while (true) {
            step = (step + N/step) / 2;
            System.out.println(step);
            Thread.sleep(100);
        }

    }
}
