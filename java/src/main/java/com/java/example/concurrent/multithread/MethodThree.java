package com.java.example.concurrent.multithread;


/**
 * @author:無忌
 * @date:2020/12/9
 * @description: 通过共享变量控制
 * volatile 修饰的变量值直接存在main memory里面，子线程对该变量的读写直接写入main memory，而不是像其它变量一样在local thread里面产生一份copy。 volatile 能保证所修饰的变量对于多个线程可见性，即只要被修改，其它线程读到的一定是最新的值。
 */
public class MethodThree {
    class ThreadGo {
        int value;
    }

    volatile ThreadGo threadGo = new ThreadGo();

    private Runnable newThreadOne() {
        return new Runnable() {
            @Override
            public void run() {
                String[] noArr = Helper.buildNoArr(52);
                for (int i = 0; i < noArr.length; i += 2) {
                    while (threadGo.value == 2) {

                    }
                    Helper.print(noArr[i], noArr[i + 1]);
                    threadGo.value = 2;
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
                    while (threadGo.value == 1) {

                    }
                    Helper.print(charArr[i]);
                    threadGo.value = 1;
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodThree method = new MethodThree();
        Helper.instance.run(method.newThreadOne());
        Helper.instance.run(method.newThreadTwo());
        Helper.instance.shutdown();
    }
}
