package com.algorithm.example.linkedlist;

/**
 * @author:無忌
 * @date:2020/9/17
 * @description:25.合并两个排序的链表
 * leetcode测试地址：https://leetcode-cn.com/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/
 */
class P145_MergeortedList {
    /**
     * 递归
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode<Integer> mergeTwoLists(ListNode<Integer> head1, ListNode<Integer> head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode<Integer> head = null;

        if (head1.val <= head2.val) {
            head = head1;
            head1.next = mergeTwoLists(head1.next, head2);
        } else {
            head = head2;
            head2.next = mergeTwoLists(head1, head2.next);
        }

        return head;
    }

    /**
     * 非递归
     *
     * @param head1
     * @param head2
     * @return
     */
    public ListNode<Integer> mergeTwoLists2(ListNode<Integer> head1, ListNode<Integer> head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }

        ListNode<Integer> head = new ListNode(0);
        ListNode<Integer> root = head;
        while (head1 != null && head2 != null) {
            if (head1.val <= head2.val) {
                head.next = head1;
                head1 = head1.next;
            } else {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }
        if (head1 != null) {
            head.next = head1;
        }
        if (head2 != null) {
            head.next = head2;
        }
        return root.next;
    }
}
