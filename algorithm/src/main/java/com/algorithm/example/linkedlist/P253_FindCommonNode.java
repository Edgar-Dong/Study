package com.algorithm.example.linkedlist;

import java.util.Stack;

/**
 * @author:無忌
 * @date:2020/9/18
 * @description:52.两个链表的第一个公共节点
 */
class P253_FindCommonNode {
    public static ListNode<Integer> findCommonNode(ListNode<Integer> head1, ListNode<Integer> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode<Integer> where1 = head1;
        ListNode<Integer> where2 = head2;
        int length1 = 0;
        int length2 = 0;
        while (where1 != null) {
            length1++;
            where1 = where1.next;
        }
        while (where2 != null) {
            length2++;
            where2 = where2.next;
        }
        where1 = head1;
        where2 = head2;
        if (length1 >= length2) {
            for (int i = length1 - length2; i > 0; i--) {
                where1 = where1.next;
            }
        } else {
            for (int i = length2 - length1; i > 0; i--) {
                where2 = where2.next;
            }
        }
        while (where1 != where2) {
            where1 = where1.next;
            where2 = where2.next;
        }
        return where1;
    }

    public static ListNode<Integer> findCommonNode2(ListNode<Integer> head1, ListNode<Integer> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        ListNode<Integer> result = null;
        ListNode<Integer> temp = null;
        Stack<ListNode<Integer>> stack1 = new Stack<>();
        Stack<ListNode<Integer>> stack2 = new Stack<>();
        while (head1 != null) {
            stack1.push(head1);
            head1 = head1.next;
        }
        while (head2 != null) {
            stack2.push(head2);
            head2 = head2.next;
        }
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            temp = stack1.pop();
            if (temp == stack2.pop()) {
                result = temp;
            } else {
                break;
            }
        }
        return result;
    }
}
