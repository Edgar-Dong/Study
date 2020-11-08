package com.java.example.concurrent.demo02;

import java.util.Date;

/**
 * @author:無忌
 * @date:2020/9/15
 * @description:Synchronized基本使用
 */
class SynchronizedBasicUse {
    /**
     * 类锁
     * <p>
     * Thread-2 sleep times(5000) print:Tue Sep 15 11:18:13 CST 2020
     * Thread-2 print execute end
     * Thread-3 sleep times(3000) print:Tue Sep 15 11:18:18 CST 2020
     * Thread-3 print execute end
     * <p>
     * Thread-2 begin -> Thread-2 end -> Thread-3 begin -> Thread-3 end
     *
     * @param sleepTimes
     */
    public static synchronized void print(long sleepTimes) {
        String curThreadName = Thread.currentThread().getName();
        System.out.println(curThreadName + " sleep times(" + sleepTimes + ") print:" + new Date());
        try {
            Thread.sleep(sleepTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(curThreadName + " print execute end");
    }

    /**
     * 对象锁
     * <p>
     * Thread-2 sleep times(5000) print1:Tue Sep 15 11:19:34 CST 2020
     * Thread-3 sleep times(3000) print1:Tue Sep 15 11:19:34 CST 2020
     * Thread-3 print1 execute end
     * Thread-2 print1 execute end
     * <p>
     * Thread-2 begin -> Thread-3 begin -> Thread-3 end -> Thread-2 end
     *
     * @param sleepTimes
     */
    public synchronized void print1(long sleepTimes) {
        String curThreadName = Thread.currentThread().getName();
        System.out.println(curThreadName + " sleep times(" + sleepTimes + ") print1:" + new Date());
        try {
            Thread.sleep(sleepTimes);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(curThreadName + " print1 execute end");
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedBasicUse synchronizedDemo = new SynchronizedBasicUse();
                //synchronizedDemo.print(5000);
                synchronizedDemo.print1(5000);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                SynchronizedBasicUse synchronizedDemo2 = new SynchronizedBasicUse();
                //synchronizedDemo2.print(3000);
                synchronizedDemo2.print1(3000);
            }
        }).start();
    }
}
