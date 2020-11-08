package com.algorithm.example.linkedlist;

/**
 * @author:無忌
 * @date:2020/9/15
 * @description:8-2.删除排序链表中重复的节点
 */
class P122_DeleteDuplicatedNode {
    //[1,2,2,3,3,3]
    //[1,1,1,1,1,1]
    //[1,1,2,3,4,5]
    public static ListNode<Integer> deleteDuplication(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode<Integer> pre = null;
        ListNode<Integer> cur = head;
        ListNode<Integer> pos = cur.next;
        boolean needDelete = false;
        while (pos != null) {
            if (cur.val.equals(pos.val)) {
                needDelete = true;
                pos = pos.next;
            } else if (needDelete && !cur.val.equals(pos.val)) {
                if (pre == null) {
                    head = pos;
                } else {
                    pre.next = pos;
                }
                cur = pos;
                pos = pos.next;
                needDelete = false;
            } else {
                pre = cur;
                cur = pos;
                pos = pos.next;
            }
        }

        if (needDelete && pre != null) {
            pre.next = null;
        } else if (needDelete && pre == null) {
            head = null;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = new ListNode<>(1);
        head.next= new ListNode<>(1);
        head.next.next = new ListNode<>(2);
        head.next.next.next = new ListNode<>(2);
        head.next.next.next.next = new ListNode<>(2);
        head.next.next.next.next.next = new ListNode<>(3);
        System.out.println(head);
        head = deleteDuplication(head);
        System.out.println(head);
    }
}
