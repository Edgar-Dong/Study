package com.java.example.concurrent.threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:無忌
 * @date:2020/11/18
 * @description:
 */
public class ThreadPoolTest {
    static class MyTask implements Runnable {
        private int number;

        public MyTask(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
            long tId = Thread.currentThread().getId();
            String tName = Thread.currentThread().getName();
            String beginMsg = String.format(dtFormatter.format(LocalDateTime.now()) + " 线程[%d:%s]-----任务[%d] begin >>>>>", tId, tName, number);
            System.out.println(beginMsg);
            try {
                Thread.sleep(3000);
                String endMsg = String.format(dtFormatter.format(LocalDateTime.now()) + " 线程[%d:%s]-----任务[%d] end <<<<<", tId, tName, number);
                System.out.println(endMsg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        test05();
    }

    private static void test01() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyTask(i));
        }
    }

    private static void test02() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyTask(i));
        }
    }

    private static void test03() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyTask(i));
        }
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyTask(i));
        }
    }

    private static void test04() {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        for (int i = 0; i < 5; i++) {
            executorService.schedule(new MyTask(i), 2, TimeUnit.SECONDS);
        }
    }

    private static void test05() {
        AtomicInteger number = new AtomicInteger(0);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                System.out.println("init thread:" + number.get());
                return new Thread(r, String.valueOf(number.incrementAndGet()));
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                if (r instanceof MyTask) {
                    MyTask myTask = (MyTask) r;
                    System.out.println("拒绝任务:" + myTask.number);
                }
            }
        });
        for (int i = 0; i < 8; i++) {
            executor.execute(new MyTask(i));
        }
    }
}