package com.java.example.concurrent.juc.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description:
 */
class CountDownLatchDemo {
    static class MyThread extends Thread {
        private DateTimeFormatter df;
        private CountDownLatch countDownLatch;
        private int time;

        public MyThread(CountDownLatch countDownLatch, String name, int time) {
            super(name);
            df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
            this.countDownLatch = countDownLatch;
            this.time = time;
        }

        @Override
        public void run() {
            System.out.println(df.format(LocalDateTime.now()) + ":" + getName() + "开始执行任务");
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(df.format(LocalDateTime.now()) + ":" + getName() + "任务执行完成");
            countDownLatch.countDown();
        }
    }

    public static void main(String[] args) {
        int count = 5;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        new MyThread(countDownLatch, "子任务" + 1, 5).start();
        new MyThread(countDownLatch, "子任务" + 2, 3).start();
        new MyThread(countDownLatch, "子任务" + 3, 4).start();
        new MyThread(countDownLatch, "子任务" + 4, 6).start();
        new MyThread(countDownLatch, "子任务" + 5, 2).start();

        try {
            System.out.println("等待子任务执行完成");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子任务执行完成，主线程继续执行");
    }
}
