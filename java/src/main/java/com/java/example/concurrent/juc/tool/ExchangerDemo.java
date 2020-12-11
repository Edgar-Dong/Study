package com.java.example.concurrent.juc.tool;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * @author:無忌
 * @date:2020/12/10
 * @description: Exchanger 是 JDK 1.5 开始提供的一个用于两个工作线程之间交换数据的封装工具类，
 * 简单说就是一个线程在完成一定的事务后想与另一个线程交换数据，则第一个先拿出数据的线程会一直等待第二个线程，
 * 直到第二个线程拿着数据到来时才能彼此交换对应数据。
 */
class ExchangerDemo {
    static class Task implements Runnable {
        private Exchanger<Integer> exchanger;
        private int data;
        private int start;

        public Task(Exchanger exchanger, int start) {
            this.exchanger = exchanger;
            this.start = start;
        }

        @Override
        public void run() {
            for (int i = start; i < start + 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    data = i;
                    System.out.println(Thread.currentThread().getName() + "交换前:" + data);
                    data = exchanger.exchange(data);
                    System.out.println(Thread.currentThread().getName() + "交换后:" + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        new Thread(new Task(exchanger, 0), "Producer").start();
        new Thread(new Task(exchanger, 10), "Consumer").start();
    }
}
