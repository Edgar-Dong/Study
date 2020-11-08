package com.java.example.concurrent.demo02;

/**
 * @author:無忌
 * @date:2020/9/17
 * @description:synchronized不可中断性演示
 */
public class SynchronizedNonInterruptible {
    static Object obj = new Object();
    static Runnable runnable = () -> {
        synchronized (obj){
            System.out.println(Thread.currentThread().getName()+"进入同步代码块");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String[] args) {
        Thread thread = new Thread(runnable);
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(runnable);
        thread2.start();

        System.out.println("停止线程前");
        thread2.interrupt();
        System.out.println("停止线程后");

        System.out.println(thread.getState());
        System.out.println(thread2.getState());
    }
}
