package com.algorithm.example.linkedlist;

import java.util.Stack;

/**
 * @author:無忌
 * @date:2020/9/14
 * @description:6.从尾到头打印链表
 */
class P58_PrintListInReversedOrder {
    //递归版
    public static void printReversinglyRecursively(ListNode<Integer> node) {
        if (node == null) {
            return;
        } else {
            printReversinglyRecursively(node.next);
            System.out.println(node.val);
        }
    }

    //非递归版
    public static void printReversinglyIteratively(ListNode<Integer> node) {
        Stack<Integer> stack = new Stack<>();
        for (ListNode<Integer> temp = node; temp != null; temp = temp.next) {
            stack.push(temp.val);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        printReversinglyRecursively(head);
        System.out.println();
        printReversinglyIteratively(head);
    }
}
