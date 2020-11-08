package com.java.example.concurrent.demo02;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:無忌
 * @date:2020/9/17
 * @description:Lock可中断性及不可中断性演示
 */
class LockInterruptible {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
//        test01();

        test02();
    }

    /**
     * Lock不可中断演示
     */
    public static void test01() {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println(name + "获得锁，进入锁执行");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + "释放锁");
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(runnable);
        thread2.start();

        System.out.println("停止thread2线程前");
        thread2.interrupt();
        System.out.println("停止thread2线程后");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(thread.getState());
        System.out.println(thread2.getState());
    }

    /**
     * Lock可中断演示
     */
    public static void test02() {
        Runnable runnable = () -> {
            String name = Thread.currentThread().getName();
            boolean b = false;
            try {
                b = lock.tryLock(3, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + "获得锁，进入锁执行");
                    Thread.sleep(10000);
                } else {
                    System.out.println(name + "指定时间内没有得到锁做其他操作");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    lock.unlock();
                    System.out.println(name + "释放锁");
                }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(runnable);
        thread2.start();
    }
}
