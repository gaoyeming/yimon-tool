package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 翻转链表
 * @date: 2024/8/30 下午3:50
 */
public class LeetCode_206_ReverseList {

    public static void main(String[] args) {
        LeetCode_206_ReverseList reverseList = new LeetCode_206_ReverseList();
        System.out.println(reverseList.reverseList(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))))));
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode tail = null;
        while (head != null) {
            ListNode next = head.next;
            //将当前执行tail
            head.next = tail;
            tail = head;
            head = next;
        }
        return tail;
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
