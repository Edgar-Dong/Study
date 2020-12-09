package com.java.example.concurrent.multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description:
 */
public enum Helper {
    instance;

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static String[] buildNoArr(int max) {
        String[] noArr = new String[max];
        for (int i = 0; i < max; i++) {
            noArr[i] = Integer.toString(i + 1);
        }
        return noArr;
    }

    public static String[] buildCharArr(int max) {
        String[] charArr = new String[max];
        int temp = 65;
        for (int i = 0; i < max; i++) {
            charArr[i] = String.valueOf((char) (temp + i));
        }
        return charArr;
    }

    public static void print(String... input) {
        if (input == null) {
            return;
        }
        for (String each : input) {
            System.out.print(each);
        }
    }

    public void run(Runnable runnable) {
        threadPool.execute(runnable);
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}
