package com.algorithm.example.linkedlist;

/**
 * @author:無忌
 * @date:2020/9/16
 * @description:24.反转链表
 */
class P142_ReverseList {
    /**
     * 非递归
     *
     * @param head
     * @return
     */
    public static ListNode<Integer> reverseList(ListNode<Integer> head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }

    /**
     * 递归
     *
     * @param head
     * @return
     */
    public static ListNode<Integer> reverseList2(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<Integer> reverse = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        head.next.next.next = null;
        System.out.println(head);
        System.out.println(reverseList2(head));
    }
}
