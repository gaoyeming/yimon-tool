package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 删除排序链表中的重复元素
 * @date: 2024/8/29 下午8:03
 */
public class LeetCode_083_DeleteDuplicates {

    public static void main(String[] args) {
        LeetCode_083_DeleteDuplicates deleteDuplicates = new LeetCode_083_DeleteDuplicates();
        System.out.println(deleteDuplicates.deleteDuplicates(new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1, new ListNode(1)))))));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;

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
