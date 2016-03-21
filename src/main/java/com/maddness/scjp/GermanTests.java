package com.maddness.scjp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maddness on 20/03/2016.
 */
public class GermanTests {
    public static void main(String[] args) {
        double d = 10.0 / 0.0;
        System.out.println(Math.sqrt(-1));      // Infinity

        Double a = null;
        double d2 = 10.0 / a;
        System.out.println(d2);     // NPE

        int i = 10 / 0;
        System.out.println(i);      // ArithmeticException

        double d3 = 10 / 0;
        System.out.println(d3);     // ArithmeticException

    }

    private static void testArrays() {
        Object[] o = new Object[10];
        String[] s = new String[10];

        o = s;
//        s = o;        // incompatible types
    }

    private static void testGenerics() {
        List<? extends Object> o = new ArrayList<>();
        List<String> s = new ArrayList<>();

//        o = s;
//        s = o;
    }
}
