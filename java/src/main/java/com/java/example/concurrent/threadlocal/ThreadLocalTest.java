package com.java.example.concurrent.threadlocal;

/**
 * @author:無忌
 * @date:2020/11/25
 * @description:
 */
class ThreadLocalTest {
    private static ThreadLocal<StringBuilder> threadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new StringBuilder();
        }
    };

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        threadLocal.set(stringBuilder.append(j));
                        System.out.println(String.format("thread(name:%s, hashcode:%s) threadlocal(hashcode:%s) stringbuilder(hashcode:%s, value:%s)",
                                Thread.currentThread().getName(),
                                Thread.currentThread().hashCode(),
                                threadLocal.hashCode(),
                                threadLocal.get().hashCode(),
                                threadLocal.get().toString()));
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }, "thread-" + i).start();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
