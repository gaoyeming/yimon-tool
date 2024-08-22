package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 两数相加
 * @date: 2024/7/22 下午6:58
 */
public class LeetCode_002_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int intl1 = l1 == null ? 0 : l1.val;
            int intl2 = l2 == null ? 0 : l2.val;
            int sum = intl1 + intl2 + carry;
            if (head == null) {
                head = new ListNode(sum % 10);
                tail = head;
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
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
    }
}
