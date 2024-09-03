package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 合并两个有序链表
 * @date: 2024/9/2 下午6:40
 */
public class LeetCode_021_MergeTwoLists {

    public static void main(String[] args) {
        LeetCode_021_MergeTwoLists mergeTwoLists = new LeetCode_021_MergeTwoLists();
        System.out.println(mergeTwoLists.mergeTwoLists(new ListNode(1, new ListNode(2, new ListNode(4))), new ListNode(1, new ListNode(3, new ListNode(4)))));
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        ListNode head = new ListNode();
        ListNode result = head;
        while (true) {
            if (list1 == null) {
                head.next = list2;
                break;
            }
            if (list2 == null) {
                head.next = list1;
                break;
            }

            if (list1.val <= list2.val) {
                head.next = list1;
                list1 = list1.next;
            } else {
                head.next = list2;
                list2 = list2.next;
            }
            head = head.next;
        }

        return result.next;
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
