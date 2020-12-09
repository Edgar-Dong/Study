package com.java.example.concurrent.multithread;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description: 通过共享变量控制
 * 利用最基本的synchronized、notify、wait
 */
public class MethodOne {
    class ThreadGo {
        int value = 1;
    }

    ThreadGo lock = new ThreadGo();

    private Runnable newThreadOne() {
        return new Runnable() {
            @Override
            public void run() {
                String[] noArr = Helper.buildNoArr(52);
                try {
                    for (int i = 0; i < noArr.length; i += 2) {
                        synchronized (lock) {
                            while (lock.value == 2) {
                                lock.wait();
                            }
                            Helper.print(noArr[i], noArr[i + 1]);
                            lock.value = 2;
                            lock.notify();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private Runnable newThreadTwo() {
        return new Runnable() {
            @Override
            public void run() {
                String[] charArr = Helper.buildCharArr(26);
                try {
                    for (int i = 0; i < charArr.length; i++) {
                        synchronized (lock) {
                            while (lock.value == 1) {
                                lock.wait();
                            }
                            Helper.print(charArr[i]);
                            lock.value = 1;
                            lock.notify();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodOne method = new MethodOne();
        Helper.instance.run(method.newThreadOne());
        Helper.instance.run(method.newThreadTwo());
        Helper.instance.shutdown();
    }
}
