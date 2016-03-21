package com.maddness.scjp;

/**
 * Created by maddness on 11/03/2016.
 */
 class Statics {

    static void check(Animal a) {
        System.out.println("in animal");
    }

    static void check(Dog a) {
        System.out.println("in dog");
    }

    public static void main(String[] args) {

        Dog a = new Dog();

        check(a);

    }
}

class Dog extends Animal {}

class Cat extends Animal {}

class Animal {}
