package com.java.example.concurrent.demo02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:無忌
 * @date:2020/9/16
 * @description:原子性
 */
class SynchronizedAtomicity {
    public static int number = 0;

    public static Object obj = new Object();

    public static Runnable runnable = () -> {
        for (int i = 0; i < 1000; i++) {
            synchronized (obj){
                number++;
            }
        }
    };

    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            list.add(thread);
        }

        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("number:" + number);
    }
}
