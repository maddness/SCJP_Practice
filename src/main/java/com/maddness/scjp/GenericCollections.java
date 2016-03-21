package com.maddness.scjp;

import java.util.ArrayList;
import java.util.List;

public class GenericCollections {
    public static void main(String[] args) {

        addAnimals(new ArrayList<Animal>());
//        addAnimals(new ArrayList<Object>());        // can't pass anything than Animal

        readSubtypes(new ArrayList<>());
        readSubtypes(new ArrayList<Dog>());
        readSubtypes(new ArrayList<Cat>());

//        addSupertypes(new ArrayList<Dog>());        // only Animals or higher
        addSupertypes(new ArrayList<Animal>());
        addSupertypes(new ArrayList<Object>());

    }

    private static void addAnimals(List<Animal> arr) {
        arr.add(new Cat());
        arr.add(new Dog());
    }

    private static void readSubtypes(List<? extends Animal> arr) {       // only get, nothing can't be added
//        arr.add(new Cat());     can't add - only read!
        System.out.println(arr.get(0));
    }

    private static void addSupertypes(List<? super Animal> arr) {       // can add Animals or lower
        arr.add(new Dog());
        arr.add(new Cat());
        arr.add(new Animal());
//        arr.add(new Object());      can add Animals or lower!
    }
}

