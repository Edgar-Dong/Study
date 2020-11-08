package com.algorithm.example.linkedlist;

/**
 * @author:無忌
 * @date:2020/9/15
 * @description:22.链表中倒数第k个节点
 */
class P134_KthNodeFromEnd {
    public static ListNode<Integer> findKthToTail(ListNode<Integer> head, int k) {
        if (head == null || k <= 0) {
            return null;
        }
        ListNode<Integer> slow = head, fast = head;
        for (int i = 0; i < k; i++) {
            if (fast.next != null || i == k - 1) {
                fast = fast.next;
            } else {
                return null;
            }
        }
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(2);
        head.next.next = new ListNode<>(3);
        System.out.println(head);
        System.out.println(findKthToTail(head, 1).val);
        System.out.println(findKthToTail(head, 2).val);
        System.out.println(findKthToTail(head, 3).val);
        System.out.println(findKthToTail(head, 4));
    }
}
