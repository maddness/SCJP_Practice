package com.maddness.scjp;

/**
 *  Fields are never inherited, they will save their values through references
 */
public class SuperClassCrazy {
    public static void main(String[] args) {

        A a = new B();

        B b = (B) a;

        a.a = 10;

        System.out.println(b.a);

    }
}

class A {
    int a = 5;
}

class B extends A {
    int a = 2;
}
