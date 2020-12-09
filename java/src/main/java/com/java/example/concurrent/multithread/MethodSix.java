package com.java.example.concurrent.multithread;


import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description: 通过共享变量控制
 */
public class MethodSix {

    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

    private Runnable newThreadOne() {
        return new Runnable() {
            @Override
            public void run() {
                String[] noArr = Helper.buildNoArr(52);
                for (int i = 0; i < noArr.length; i += 2) {
                    Helper.print(noArr[i], noArr[i + 1]);
                    queue.offer("TwoGo");
                    while (!"OneGo".equals(queue.peek())) {

                    }
                    queue.poll();
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
                    while (!"TwoGo".equals(queue.peek())) {

                    }
                    queue.poll();
                    Helper.print(charArr[i]);
                    queue.offer("OneGo");
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodSix method = new MethodSix();
        Helper.instance.run(method.newThreadOne());
        Helper.instance.run(method.newThreadTwo());
        Helper.instance.shutdown();
    }
}
