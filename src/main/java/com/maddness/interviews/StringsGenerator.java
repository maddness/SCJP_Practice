package com.maddness.interviews;

/**
 * Created by maddness on 18/03/2016.
 */
public class StringsGenerator {

    public static void main(String[] args) {
        StringsGenerator gen = new StringsGenerator();
        gen.gimmeStrings("erf", "yuhj");
    }

    public void gimmeStrings(String s1, String s2) {
        if (compareStrins(s1, s2) >= 0) {
            System.out.println("Nothing!");

        } else {
            String newString = nextString(s1);
            while (compareStrins(newString, s2) < 0) {
                System.out.println(newString);
                newString = nextString(newString);
            }
        }
    }

    public String nextString(String s) {
        if (s.matches("z+")) {
            return s.replaceAll("z", "a") + "a";

        } else {
            char[] arr = s.toCharArray();
            for (int i = s.length() - 1; i >= 0; i--) {
                if (arr[i] == 'z') {
                    arr[i] = 'a';
                } else {
                    arr[i] += 1;
                    break;
                }
            }
            return new String(arr);
        }
    }

    public int compareStrins(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return s1.length() - s2.length();
        } else {
            return s1.compareTo(s2);
        }
    }

}
