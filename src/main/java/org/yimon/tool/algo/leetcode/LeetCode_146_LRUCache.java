package org.yimon.tool.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: ym.gao
 * @description: 手写LRU淘汰策略
 * @date: 2024/8/22 下午3:47
 */
public class LeetCode_146_LRUCache {

    private Map<String, Node> cache;
    private int size;
    private Node head;
    private Node tail;

    public LeetCode_146_LRUCache(int size) {
        this.cache = new HashMap<>();
        this.size = size;
        Node head = new Node(null, null);
        Node tail = new Node(null, null);
        head.next = tail;
        tail.pre = head;
        this.head = head;
        this.tail = tail;
    }

    public String get(String key) {
        //先从缓存取
        Node node = cache.get(key);
        if (node == null) {//取不到返回null
            return null;
        }
        //取到之后需要将当前缓存放入头，然后再返回当前节点值
        removeNode(node);
        addNode(node);
        return node.value;
    }

    public void put(String key, String value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            removeNode(node);
            addNode(node);
            return;
        }
        Node newNode = new Node(key, value);
        cache.put(key, newNode);
        addNode(newNode);
        if (cache.size() > this.size) {
            cache.remove(tail.pre.key);
            removeNode(tail.pre);
        }
    }

    private void addNode(Node node) {
        Node pre = this.head.next;
        pre.pre = node;
        node.pre = this.head.next;
        this.head.next = node;
    }

    private void removeNode(Node node) {
        Node next = node.next;
        Node pre = node.pre;
        pre.next = next;
        next.pre = pre;

    }


    static class Node {
        private String key;
        private String value;
        private Node pre;
        private Node next;

        public Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
