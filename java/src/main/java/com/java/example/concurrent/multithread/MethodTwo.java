package com.java.example.concurrent.multithread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description: 通过共享变量控制
 * 利用Lock和Condition
 */
public class MethodTwo {
    class ThreadGo {
        int value;
    }

    Lock lock = new ReentrantLock(true);
    Condition condition = lock.newCondition();
    ThreadGo threadGo = new ThreadGo();

    private Runnable newThreadOne() {
        return new Runnable() {
            @Override
            public void run() {
                String[] noArr = Helper.buildNoArr(52);
                for (int i = 0; i < noArr.length; i += 2) {
                    lock.lock();
                    try {
                        if (threadGo.value == 2) {
                            condition.await();
                        }
                        Helper.print(noArr[i], noArr[i + 1]);
                        threadGo.value = 2;
                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    private Runnable newThreadTwo() {
        return new Runnable() {
            @Override
            public void run() {
                String[] charArr = Helper.buildCharArr(26);
                for (int i = 0; i < charArr.length; i++) {
                    lock.lock();
                    try {
                        while (threadGo.value == 1) {
                            condition.await();
                        }
                        Helper.print(charArr[i]);
                        threadGo.value = 1;
                        condition.signal();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodTwo method = new MethodTwo();
        Helper.instance.run(method.newThreadOne());
        Helper.instance.run(method.newThreadTwo());
        Helper.instance.shutdown();
    }
}
