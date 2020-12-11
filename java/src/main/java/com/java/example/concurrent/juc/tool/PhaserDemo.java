package com.java.example.concurrent.juc.tool;

import com.java.example.util.TimeUtil;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author:無忌
 * @date:2020/12/10
 * @description:
 * 应用场景:Phaser这个类的使用场景为N个线程分阶段并行的问题。
 * 举例:有这么一个任务为“做3道题“，每个学生一个进程，5个学生可以并行做，这个就是常规的并发，但是如果加一个额外的 限制条件，必须等所有人都做完类第一题，才能开始做第二题，必须等所有人都做完了第二题，才能做第三题，这个问题就转变成了分阶段并发的问题，最适合用Phaser来解题
 */
class PhaserDemo {
    static class MyPhaser extends Phaser {
        private int stage;

        public MyPhaser(int stage) {
            this.stage = stage;
        }

        public int getStage() {
            return stage;
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println("\n" + TimeUtil.getCurTime() + " onAdvance phase:" + phase + ", registeredParties:" + registeredParties);
            return doStage(phase);
        }

        private boolean doStage(int stage) {
            if (stage == this.stage) {
                System.out.println(TimeUtil.getCurTime() + " 第" + stage + "道题所有学生做完，结束考试");
                return true;
            } else if (stage == 0) {
                System.out.println(TimeUtil.getCurTime() + " 学生准备好了，学生人数：" + getRegisteredParties());
                return false;
            }
            System.out.println(TimeUtil.getCurTime() + " 第" + stage + "道题所有学生做完");
            return false;
        }
    }

    static class StudentTask implements Runnable {
        private MyPhaser phaser;

        public StudentTask(MyPhaser phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            for (int i = 0; i < phaser.getStage(); i++) {
                if (i == 0) {
                    System.out.println(TimeUtil.getCurTime() + " " + Thread.currentThread().getName() + "到达考试");
                } else {
                    doExercise(Thread.currentThread(), i);
                }
                phaser.arriveAndAwaitAdvance();
            }
        }

        private void doExercise(Thread thread, int exercise) {
            System.out.println(TimeUtil.getCurTime() + " " + thread.getName() + "开始做第" + exercise + "题");
            long duration = (long) (Math.random() * 10);
            try {
                TimeUnit.SECONDS.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(TimeUtil.getCurTime() + " " + thread.getName() + "第" + exercise + "题完成");
        }
    }

    public static void main(String[] args) {
        MyPhaser phaser = new MyPhaser(4);
        StudentTask[] studentTasks = new StudentTask[5];
        for (int i = 0; i < studentTasks.length; i++) {
            studentTasks[i] = new StudentTask(phaser);
            phaser.register();
        }

        Thread[] threads = new Thread[studentTasks.length];
        for (int i = 0; i < studentTasks.length; i++) {
            threads[i] = new Thread(studentTasks[i], "Student" + i);
            threads[i].start();
        }

        for (int i = 0; i < studentTasks.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Phaser has finished:" + phaser.isTerminated());
    }
}

