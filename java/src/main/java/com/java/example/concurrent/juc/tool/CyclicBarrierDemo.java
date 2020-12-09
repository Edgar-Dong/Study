package com.java.example.concurrent.juc.tool;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description:
 */
class CyclicBarrierDemo {
    static class TravelTask implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private String name;
        private int arriveTime;

        public TravelTask(CyclicBarrier cyclicBarrier, String name, int arriveTime) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            this.arriveTime = arriveTime;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(arriveTime * 1000);
                System.out.println(name + "到达集合点");
                cyclicBarrier.await();
                System.out.println(name + "开始旅行");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class TourGuideTask implements Runnable {

        @Override
        public void run() {
            System.out.println("*****导游分发护照签证*****");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new TourGuideTask());
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new TravelTask(cyclicBarrier, "张三", 3));
        executorService.execute(new TravelTask(cyclicBarrier, "王五", 5));
        executorService.execute(new TravelTask(cyclicBarrier, "李四", 4));
    }
}
