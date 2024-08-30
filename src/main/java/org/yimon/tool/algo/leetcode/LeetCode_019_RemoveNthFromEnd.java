package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 删除链表倒数第N个节点
 * @date: 2024/8/22 下午3:53
 */
public class LeetCode_019_RemoveNthFromEnd {

    public static void main(String[] args) {
        LeetCode_019_RemoveNthFromEnd removeNthFromEnd = new LeetCode_019_RemoveNthFromEnd();
        System.out.println(removeNthFromEnd.removeNthFromEnd2(new ListNode(1, new ListNode(2)), 2));
    }

    public ListNode removeNthFromEnd1(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (n <= 0) {
            return head;
        }
        int length = 1;
        ListNode cur = head;
        while (cur.next != null) {
            length++;
            cur = cur.next;
        }
        if (n > length) {
            return null;
        }
        if (n == length) {
            return head.next;
        }
        //需要正向删除第m的节点
        int m = length - n;
        ListNode next = head;
        for (int i = 0; i <= m; i++) {
            if (i == m - 1) {//需要删除的节点
                next.next = next.next.next;
                break;
            }
            next = next.next;
        }

        return head;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        if (n <= 0) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);
        ListNode slow = dummy;
        ListNode fast = head;
        for (int i = 0; i < n; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }


        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;

        return dummy.next;

    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}
