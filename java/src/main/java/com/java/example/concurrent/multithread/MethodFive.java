package com.java.example.concurrent.multithread;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author:無忌
 * @date:2020/12/9
 * @description:
 */
public class MethodFive {
    private List<String> list;
    private CyclicBarrier cyclicBarrier;

    public MethodFive() {
        list = Collections.synchronizedList(new ArrayList<>());
        cyclicBarrier = new CyclicBarrier(2, newBarrierAction());
    }

    private Runnable newBarrierAction() {
        return new Runnable() {
            @Override
            public void run() {
                Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        //System.out.println(o1 + " " + o2 + " " + o1.compareTo(o2));
                        if ("10".equals(o1) && o2.equals("9")) {
                            return 1;
                        } else {
                            return o1.compareTo(o2);
                        }
                    }
                });
                list.forEach(s -> System.out.print(s));
                list.clear();
            }
        };
    }

    private Runnable newThreadOne() {
        return new Runnable() {
            @Override
            public void run() {
                String[] noArr = Helper.buildNoArr(52);
                for (int i = 0; i < noArr.length; i += 2) {
                    list.add(noArr[i]);
                    list.add(noArr[i + 1]);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
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
                    list.add(charArr[i]);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    public static void main(String[] args) {
        MethodFive method = new MethodFive();
        Helper.instance.run(method.newThreadOne());
        Helper.instance.run(method.newThreadTwo());
        Helper.instance.shutdown();
    }
}
