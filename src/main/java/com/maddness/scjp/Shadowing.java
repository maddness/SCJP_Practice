package com.maddness.scjp;

/**
 * Created by maddness on 11/03/2016.
 */
public class Shadowing {
    int a = 5;

    public void foo(int a) {
//        int a = 6;
        System.out.println(a);
    }

    public static void main(String[] args) {
        new Shadowing().foo(7);
    }
}
