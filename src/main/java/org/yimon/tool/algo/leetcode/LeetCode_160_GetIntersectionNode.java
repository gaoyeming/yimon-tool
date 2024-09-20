package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 相交链表
 * @date: 2024/9/11 下午3:08
 */
public class LeetCode_160_GetIntersectionNode {

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

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode index1 = headA, index2 = headB;
        while (index1 != null || index2 != null) {
            if (index1 == index2) {
                return index1;
            }
            index1 = index1 == null ? headB : index1.next;
            index2 = index2 == null ? headA : index2.next;
        }
        return null;
    }
}
