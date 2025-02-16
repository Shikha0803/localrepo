package org.example;

import java.util.Arrays;

public class Logger {
    public static void debug(Object... args) {
        System.out.println(Arrays.toString(args));
    }

    public static void error(Exception e) {
        System.out.println("Logged Exception" + e.toString());
    }
}
