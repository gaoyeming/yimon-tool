package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 环形链表
 * @date: 2024/8/22 下午6:59
 */
public class LeetCode_141_HasCycle {

    public static void main(String[] args) {
        LeetCode_141_HasCycle hasCycle = new LeetCode_141_HasCycle();
        System.out.println(hasCycle.hasCycle(new ListNode(1, new ListNode(2))));
    }

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = new ListNode(0, head);
        ListNode slow = new ListNode(0, head);
        while (fast != slow) {
            if (slow.next == null) {
                return false;
            }
            if (fast.next == null || fast.next.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
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
