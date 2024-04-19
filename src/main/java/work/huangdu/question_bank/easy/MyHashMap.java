package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 706. 设计哈希映射
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 *
 * 实现 MyHashMap 类：
 *
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * 示例：
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 * 提示：
 * 0 <= key, value <= 10^6
 * 最多调用 10^4 次 put、get 和 remove 方法
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 *
 * 进阶：你能否不使用内置的 HashMap 库解决此问题？
 *
 * @author huangdu
 * @version 2021/3/14
 */
public class MyHashMap {
    private static final int INIT_SIZE = 1000001;
    private final int[] store;

    /**
     * Initialize your data structure here.
     */
    public MyHashMap() {
        store = new int[INIT_SIZE];
        Arrays.fill(store, -1);
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        store[key] = value;
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        return store[key];
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        store[key] = -1;
    }
}

class HashMap {
    private static final int BASE = 769;
    private final Node[] data;

    static class Node {
        int key;
        int value;
        Node next;

        Node() {}

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        Node(int key, int value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public HashMap() {
        data = new Node[BASE];
    }

    /**
     * value will always be non-negative.
     */
    public void put(int key, int value) {
        int hash = hash(key);
        Node head = data[hash];
        while (head != null && head.getKey() != key) {
            head = head.next;
        }
        if (head == null) {
            data[hash] = new Node(key, value);
        } else {
            head.setValue(value);
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
     */
    public int get(int key) {
        int hash = hash(key);
        Node head = data[hash];
        while (head != null && head.getKey() != key) {
            head = head.next;
        }
        return head == null ? -1 : head.getValue();
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int hash = hash(key);
        Node head = data[hash];
        if (head == null) {return;}
        if (head.getKey() == key) {
            data[hash] = null;
            return;
        }
        while (head.next != null && head.next.getKey() != key) {
            head = head.next;
        }
        if (head.next != null) {
            head.next = null;
        }
    }

    private int hash(int value) {
        return value % BASE;
    }
}