package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 删除排序链表中的重复元素Ⅱ
 * @date: 2024/8/30 上午11:11
 */
public class LeetCode_082_DeleteDuplicates {

    public static void main(String[] args) {
        LeetCode_082_DeleteDuplicates deleteDuplicates = new LeetCode_082_DeleteDuplicates();
        System.out.println(deleteDuplicates.deleteDuplicates(new ListNode(1, new ListNode(1))));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //记录结果节点
        ListNode result = new ListNode(0, head);
        ListNode pre = result;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                //重新遍历跳过所有重复节点
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                //存在重复几点，前一个节点不移动；前一个节点的下一个节点移动到不重复的节点上
                pre.next = head.next;
            } else {
                //不重复的情况，前一个节点向下移动
                pre = pre.next;
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
