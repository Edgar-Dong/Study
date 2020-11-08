package com.algorithm.example.stack_queue;

import java.util.Stack;

/**
 * @author:無忌
 * @date:2020/9/21
 * @description:9.用两个栈实现队列
 */
class P68_QueueWithTwoStacks {
    static class MyQueue<T> {
        private Stack<T> stack1 = new Stack<>();
        private Stack<T> stack2 = new Stack<>();

        public void offer(T element) {
            stack1.push(element);
        }

        public T poll() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }
            return null;
        }
    }

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        for (int i = 0; i < 5; i++) {
            myQueue.offer(i);
        }
        System.out.println(myQueue.poll());
        myQueue.offer(6);
        System.out.println(myQueue.poll());
    }
}
