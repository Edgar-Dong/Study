package com.algorithm.example.stack_queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author:無忌
 * @date:2020/9/22
 * @description:59.2.队列的最大值
 */
class P292_QueueWithMax {
    static class InternalData<M extends Comparable> {
        public M value;
        public int index;

        public InternalData(M value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static class QueueWithMax<T extends Comparable> {
        private Deque<InternalData<T>> dataQueue;
        private Deque<InternalData<T>> maxQueue;
        private int currentIndex;

        public QueueWithMax() {
            this.dataQueue = new ArrayDeque<>();
            this.maxQueue = new ArrayDeque<>();
            this.currentIndex = 0;
        }

        public T max() {
            if (maxQueue.isEmpty()) {
                return null;
            }
            return maxQueue.getFirst().value;
        }

        public void pushBack(T value) {
            while (!maxQueue.isEmpty() && value.compareTo(maxQueue.getLast().value) >= 0) {
                maxQueue.removeLast();
            }
            InternalData<T> addData = new InternalData<>(value, currentIndex);
            maxQueue.add(addData);
            dataQueue.add(addData);
            currentIndex++;
        }

        public T popFront() {
            if (dataQueue.isEmpty()) {
                return null;
            }
            InternalData<T> delData = dataQueue.removeFirst();
            if (delData.index == maxQueue.getFirst().index) {
                maxQueue.removeFirst();
            }
            return delData.value;
        }
    }

    public static void main(String[] args) {
        QueueWithMax<Integer> queue = new QueueWithMax<>();
        queue.pushBack(3);
        System.out.println(queue.max());//3
        queue.pushBack(5);
        System.out.println(queue.max());//5
        queue.pushBack(1);
        System.out.println(queue.max());//5
        System.out.println("开始出队后，调用max");
        System.out.println(queue.max());//5
        queue.popFront();
        System.out.println(queue.max());//5
        queue.popFront();
        System.out.println(queue.max());//1
        queue.popFront();
        System.out.println(queue.max());//null
    }
}
