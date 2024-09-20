package org.yimon.tool.algo.leetcode;

/**
 * @author: ym.gao
 * @description: 两数相加
 * @date: 2024/7/22 下午6:58
 */
public class LeetCode_002_AddTwoNumbers {

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

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        ListNode head = new ListNode();
        ListNode result = head;
        int carry =0;

        while (l1 != null || l2 !=null) {
            int int1 = l1 == null ? 0 : l1.val;
            int int2 = l2 == null ? 0 : l2.val;
            int sum = (int1+int2+carry)%10;//计算个位数的值，
            head.next = new ListNode(sum);//向后添加链表
            head = head.next;
            carry = (int1+int2+carry)/10;//计算进位值
            if(l1!=null) {
                l1 = l1.next;
            }
            if(l2!=null) {
                l2 = l2.next;
            }
        }
        if(carry>0){
            head.next = new ListNode(carry);//如果最后进位值大于0，则放入链表最后
        }
        return result.next;
    }

    public static void main(String[] args) {
        LeetCode_002_AddTwoNumbers addTwoNumbers = new LeetCode_002_AddTwoNumbers();
        System.out.println(addTwoNumbers.addTwoNumbers(new ListNode(2, new ListNode(4, new ListNode(3))), new ListNode(5, new ListNode(6, new ListNode(4)))));
    }


}
