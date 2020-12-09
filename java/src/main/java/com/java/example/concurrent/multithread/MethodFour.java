package com.java.example.concurrent.multithread;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description: 通过共享变量控制
 */
public class MethodFour {
    class ThreadGo {
        AtomicInteger value = new AtomicInteger(1);
    }

    ThreadGo threadGo = new ThreadGo();

    private Runnable newThreadOne() {
        return new Runnable() {
            @Override
            public void run() {
                String[] noArr = Helper.buildNoArr(52);
                for (int i = 0; i < noArr.length; i += 2) {
                    while (threadGo.value.get() == 2) {

                    }
                    Helper.print(noArr[i], noArr[i + 1]);
                    threadGo.value.set(2);
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
                    while (threadGo.value.get() == 1) {

                    }
                    Helper.print(charArr[i]);
                    threadGo.value.set(1);
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodFour method = new MethodFour();
        Helper.instance.run(method.newThreadOne());
        Helper.instance.run(method.newThreadTwo());
        Helper.instance.shutdown();
    }
}
