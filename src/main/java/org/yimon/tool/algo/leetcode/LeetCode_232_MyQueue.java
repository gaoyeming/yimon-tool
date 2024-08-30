package org.yimon.tool.algo.leetcode;

import java.util.Stack;

/**
 * @author: ym.gao
 * @description: 用栈实现队列
 * @date: 2024/8/30 下午5:31
 */
public class LeetCode_232_MyQueue {

    private Stack<Integer> stack1, stack2;

    public LeetCode_232_MyQueue() {
        this.stack1 = new Stack<>();
        this.stack2 = new Stack<>();
    }

    /**
     * 添加元素到队尾
     */
    public void push(int x) {
        stack1.push(x);
    }

    /**
     * 删除队头的元素并返回
     */
    public int pop() {
        peek();
        return stack2.pop();
    }

    /**
     * 返回队头元素
     */
    public int peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                // 把 s1 元素压入 s2
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    /**
     * 判断队列是否为空
     */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}
