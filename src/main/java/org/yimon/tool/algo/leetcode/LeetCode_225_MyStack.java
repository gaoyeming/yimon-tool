package org.yimon.tool.algo.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author: ym.gao
 * @description: 用队列实现栈
 * @date: 2024/8/30 下午6:40
 */
public class LeetCode_225_MyStack {

    private Queue<Integer> queue;
    private Integer top_elem;//记录队列头元素

    public LeetCode_225_MyStack() {
        this.queue = new LinkedList<>();
    }

    /**
     * 添加元素到栈顶
     */
    public void push(int x) {
        // x 是队列的队尾，是栈的栈顶
        queue.offer(x);
        top_elem = x;
    }

    /**
     * 删除栈顶的元素并返回
     */
    public int pop() {
        int size = queue.size();
        // 留下队尾 2 个元素
        while (size > 2) {
            queue.offer(queue.poll());
            size--;
        }
        // 记录新的队尾元素
        top_elem = queue.peek();//queue.peek()是Java中Queue接口的一个方法，用于获取但不移除队头的元素。如果队列为空，则返回null。
        queue.offer(queue.poll());//queue.poll()是Java中Queue接口的一个方法，用于从队列中获取并移除队头的元素。如果队列为空，则返回null
        // 删除之前的队尾元素
        return queue.poll();
    }

    /**
     * 返回栈顶元素
     */
    public int top() {
        return top_elem;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
