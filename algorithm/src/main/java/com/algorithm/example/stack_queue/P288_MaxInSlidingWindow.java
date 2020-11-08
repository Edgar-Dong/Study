package com.algorithm.example.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author:無忌
 * @date:2020/9/22
 * @description:滑动窗口的最大值
 */
class P288_MaxInSlidingWindow {
    /**
     * 暴力破解法
     *
     * @param num
     * @param size
     * @return
     */
    public static List<Integer> maxInWindows(int[] num, int size) {
        List<Integer> list = new ArrayList<>();
        if (num == null || num.length == 0 || size == 0) {
            return list;
        }
        for (int i = 0; i < num.length - size + 1; i++) {
            int tempMax = num[i];
            for (int j = 1; j < size; j++) {
                if (num[i + j] > tempMax) {
                    tempMax = num[i + j];
                }
            }
            list.add(tempMax);
        }
        return list;
    }

    /**
     * 双端队列法
     *
     * @param num
     * @param size
     * @return
     */
    public static List<Integer> maxInWindows2(int[] num, int size) {
        List<Integer> list = new ArrayList<>();
        //条件检查
        if (num == null || num.length < 1 || size < 1 || size > num.length) {
            return list;
        }
        Deque<Integer> indexQueue = new LinkedList<>();
        //窗口还没被填满时，找最大值索引
        for (int i = 0; i < size; i++) {
            //如果索引对应的值>=之前存储索引的对应值，删除之前的索引值
            while (!indexQueue.isEmpty() && num[i] >= num[indexQueue.getLast()]) {
                indexQueue.removeLast();
            }
            indexQueue.addLast(i);
        }
        for (int i = size; i < num.length; i++) {
            //保存第一个窗口的最大值
            list.add(num[indexQueue.getFirst()]);
            //新加入窗口的值>=队列中存储索引的对应值，删除之前的索引值
            while (!indexQueue.isEmpty() && num[i] >= num[indexQueue.getLast()]) {
                indexQueue.removeLast();
            }
            //删除队列中已经滑出窗口数据对应的索引
            if (!indexQueue.isEmpty() && indexQueue.getFirst() <= (i - size)) {
                indexQueue.removeFirst();
            }
            indexQueue.addLast(i);
        }
        //最后一个窗口最大值入队
        list.add(num[indexQueue.getFirst()]);
        return list;
    }

    public static void main(String[] args) {
        int[] num = {2, 3, 4, 2, 6, 2, 5, 1};
        int size = 3;
        List<Integer> list = maxInWindows(num, size);
        System.out.println(Arrays.toString(list.toArray()));
        List<Integer> list2 = maxInWindows2(num, size);
        System.out.println(Arrays.toString(list2.toArray()));
    }
}
