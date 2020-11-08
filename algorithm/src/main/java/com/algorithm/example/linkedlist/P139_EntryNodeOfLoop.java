package com.algorithm.example.linkedlist;

/**
 * @author:無忌
 * @date:2020/9/15
 * @description:23.链表中环的入口(双指针总结)
 */
class P139_EntryNodeOfLoop {
    public static ListNode<Integer> findEntryNode(ListNode<Integer> head) {
        ListNode<Integer> fast = head;
        ListNode<Integer> slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        ListNode<Integer> node6 = new ListNode<>(6);
        ListNode<Integer> node7 = new ListNode<>(7);
        ListNode<Integer> node8 = new ListNode<>(8);
        ListNode<Integer> node9 = new ListNode<>(9);
        ListNode<Integer> node10 = new ListNode<>(10);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node5;
        System.out.println(findEntryNode(node1).val);
    }
}
