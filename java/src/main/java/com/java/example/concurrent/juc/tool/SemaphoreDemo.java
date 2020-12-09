package com.java.example.concurrent.juc.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description:
 * Semaphore 只是对资源并发访问的线程数进行监控，并不会保证线程安全
 * 可用于流量控制，限制最大的并发访问数。
 */
class SemaphoreDemo {
    static class MyThread extends Thread {
        private DateTimeFormatter df;
        private Semaphore semaphore;
        private int time;

        public MyThread(Semaphore semaphore, int time, String name) {
            super(name);
            this.semaphore = semaphore;
            this.time = time;
            df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(df.format(LocalDateTime.now()) + ":" + getName() + " acquire ");
                Thread.sleep(time * 1000);
                semaphore.release();
                System.out.println(df.format(LocalDateTime.now()) + ":" + getName() + " release ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3,true);
        for (int i = 0; i < 10; i++) {
            new MyThread(semaphore, i, "子任务" + i).start();
        }
    }
}
