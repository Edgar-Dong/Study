package com.java.example.concurrent.demo02;

import java.util.concurrent.TimeUnit;

/**
 * @author:無忌
 * @date:2020/9/16
 * @description:可见性演示
 */
class SynchronizedVisibility {

    public static boolean flag = true;

    public static Object obj = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (flag) {
                synchronized (obj){

                }
            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            flag = false;
            System.out.println(Thread.currentThread().getName() + ":共享变量flag已修改为false");
        }).start();
    }
}
