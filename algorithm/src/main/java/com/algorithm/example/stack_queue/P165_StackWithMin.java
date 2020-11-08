package com.algorithm.example.stack_queue;

import java.util.Stack;

/**
 * @author:無忌
 * @date:2020/9/21
 * @description:30.包含min函数的栈
 */
class P165_StackWithMin {
    static class StackWithMin<T extends Comparable> {
        private Stack<T> dataStack = new Stack<>();
        private Stack<T> minStack = new Stack<>();

        public void push(T element) {
            dataStack.push(element);
            if (minStack.isEmpty()) {
                minStack.push(element);
            } else {
                T temp = minStack.peek();
                if (element.compareTo(temp) < 0) {
                    minStack.push(element);
                } else {
                    minStack.push(temp);
                }
            }
        }

        public T pop() {
            if (dataStack.isEmpty()) {
                return null;
            }
            minStack.pop();
            return dataStack.pop();
        }

        public T min() {
            if (minStack.isEmpty()) {
                return null;
            }
            return minStack.peek();
        }
    }

    public static void main(String[] args) {
        StackWithMin stackWithMin = new StackWithMin();
        stackWithMin.push(3);
        stackWithMin.push(4);
        stackWithMin.push(2);
        stackWithMin.push(1);
        System.out.println(stackWithMin.min());//1
        stackWithMin.pop();
        System.out.println(stackWithMin.min());//2
        stackWithMin.pop();
        System.out.println(stackWithMin.min());//3
        stackWithMin.pop();
        System.out.println(stackWithMin.min());//3
        stackWithMin.pop();
        System.out.println(stackWithMin.min());//null
    }
}
