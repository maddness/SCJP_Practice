package utils;

import static java.lang.Thread.currentThread;

public class TextUtils {
    public static void print(String str) {
        System.out.println("[" + currentThread().getName() + "] " + str);
    }
}
