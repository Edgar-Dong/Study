package com.algorithm.example.stack_queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author:無忌
 * @date:2020/9/21
 * @description:9.用两个队列实现栈
 */
class P68_StackWithTwoQueues {
    static class MyStack<T> {
        private Queue<T> queue1 = new LinkedList<>();
        private Queue<T> queue2 = new LinkedList<>();

        public void push(T element) {
            if (!queue2.isEmpty()) {
                queue2.offer(element);
            } else {
                queue1.offer(element);
            }
        }

        public T pop() {
            if (!queue2.isEmpty()) {
                int size = queue2.size();
                for (int i = 0; i < size - 1; i++) {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            } else if (!queue1.isEmpty()) {
                int size = queue1.size();
                for (int i = 0; i < size - 1; i++) {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        for (int i = 0; i < 5; i++) {
            myStack.push(i);
        }
        System.out.println(myStack.pop());
        myStack.push(6);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}
