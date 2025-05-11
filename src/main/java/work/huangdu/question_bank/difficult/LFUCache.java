package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.Map;

/**
 * 460. LFU 缓存
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * 实现 LFUCache 类：
 * LFUCache(int capacity) - 用数据结构的容量 capacity 初始化对象
 * int get(int key) - 如果键 key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value) - 如果键 key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量 capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 示例：
 * 输入：
 * ["LFUCache", "put", "put", "get", "put", "get", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4, 4], [1], [3], [4]]
 * 输出：
 * [null, null, null, 1, null, -1, 3, null, -1, 3, 4]
 * 解释：
 * // cnt(x) = 键 x 的使用计数
 * // cache=[] 将显示最后一次使用的顺序（最左边的元素是最近的）
 * LFUCache lfu = new LFUCache(2);
 * lfu.put(1, 1);   // cache=[1,_], cnt(1)=1
 * lfu.put(2, 2);   // cache=[2,1], cnt(2)=1, cnt(1)=1
 * lfu.get(1);      // 返回 1
 * // cache=[1,2], cnt(2)=1, cnt(1)=2
 * lfu.put(3, 3);   // 去除键 2 ，因为 cnt(2)=1 ，使用计数最小
 * // cache=[3,1], cnt(3)=1, cnt(1)=2
 * lfu.get(2);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 * // cache=[3,1], cnt(3)=2, cnt(1)=2
 * lfu.put(4, 4);   // 去除键 1 ，1 和 3 的 cnt 相同，但 1 最久未使用
 * // cache=[4,3], cnt(4)=1, cnt(3)=2
 * lfu.get(1);      // 返回 -1（未找到）
 * lfu.get(3);      // 返回 3
 * // cache=[3,4], cnt(4)=1, cnt(3)=3
 * lfu.get(4);      // 返回 4
 * // cache=[3,4], cnt(4)=2, cnt(3)=3
 * 提示：
 * 1 <= capacity <= 10^4
 * 0 <= key <= 10^5
 * 0 <= value <= 10^9
 * 最多调用 2 * 10^5 次 get 和 put 方法
 *
 * @author huangdu
 */
public class LFUCache {
    private final int capacity;
    private int minFreq;
    private final Map<Integer, DoublyListNode> map;
    private final Map<Integer, DoublyListNode> freqs;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.map = new HashMap<>(capacity);
        this.freqs = new HashMap<>(16);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {return -1;}
        return getNode(key).val;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            if (map.size() == capacity) {removeFromTail();}
            DoublyListNode newNode = new DoublyListNode(key, value);
            addToHead(1, newNode);
            map.put(key, newNode);
            minFreq = 1;
            return;
        }
        getNode(key).val = value;
    }

    private DoublyListNode getNode(int key) {
        DoublyListNode node = map.get(key);
        int freq = removeNode(node);
        if (freq == minFreq && isEmpty(freq)) {minFreq++;}
        addToHead(freq + 1, node);
        return node;
    }

    private void addToHead(int freq, DoublyListNode node) {
        DoublyListNode head = freqs.computeIfAbsent(freq, i -> init());
        DoublyListNode next = head.next;
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
        node.freq++;
    }

    private void removeFromTail() {
        DoublyListNode tail = freqs.get(minFreq).prev;
        map.remove(tail.prev.key);
        DoublyListNode prev = tail.prev.prev;
        prev.next = tail;
        tail.prev = prev;
    }

    private int removeNode(DoublyListNode node) {
        DoublyListNode prev = node.prev, next = node.next;
        prev.next = next;
        next.prev = prev;
        return node.freq;
    }

    private DoublyListNode init() {
        DoublyListNode head = new DoublyListNode(), tail = new DoublyListNode();
        head.prev = tail;
        head.next = tail;
        tail.next = head;
        tail.prev = head;
        return head;
    }

    private boolean isEmpty(int freq) {
        DoublyListNode head = freqs.get(freq);
        return head == head.next.next;
    }

    static class DoublyListNode {
        int key;
        int val;
        int freq;
        DoublyListNode prev;
        DoublyListNode next;

        public DoublyListNode() {
        }

        DoublyListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.freq = 0;
        }
    }
}
