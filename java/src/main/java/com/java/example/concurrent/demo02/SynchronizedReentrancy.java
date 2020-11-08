package com.java.example.concurrent.demo02;

/**
 * @author:無忌
 * @date:2020/9/17
 * @description:synchronized可重入性演示
 */
public class SynchronizedReentrancy {

    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
    }


    static class MyThread extends Thread {
        @Override
        public void run() {
            super.run();
            synchronized (MyThread.class) {
                System.out.println(getName() + "进入同步代码块1");
                SynchronizedReentrancy.test();
            }
        }
    }

    static void test(){
        synchronized (MyThread.class) {
            System.out.println(Thread.currentThread().getName() + "进入同步代码块2");
        }
    }
}
