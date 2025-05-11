package work.huangdu.question_bank.medium;

/**
 * 1670. 设计前中后队列
 * 请你设计一个队列，支持在前，中，后三个位置的 push 和 pop 操作。
 * 请你完成 FrontMiddleBack 类：
 * FrontMiddleBack() 初始化队列。
 * void pushFront(int val) 将 val 添加到队列的 最前面 。
 * void pushMiddle(int val) 将 val 添加到队列的 正中间 。
 * void pushBack(int val) 将 val 添加到队里的 最后面 。
 * int popFront() 将 最前面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popMiddle() 将 正中间 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * int popBack() 将 最后面 的元素从队列中删除并返回值，如果删除之前队列为空，那么返回 -1 。
 * 请注意当有 两个 中间位置的时候，选择靠前面的位置进行操作。比方说：
 * 将 6 添加到 [1, 2, 3, 4, 5] 的中间位置，结果数组为 [1, 2, 6, 3, 4, 5] 。
 * 从 [1, 2, 3, 4, 5, 6] 的中间位置弹出元素，返回 3 ，数组变为 [1, 2, 4, 5, 6] 。
 * 示例 1：
 * 输入：
 * ["FrontMiddleBackQueue", "pushFront", "pushBack", "pushMiddle", "pushMiddle", "popFront", "popMiddle", "popMiddle", "popBack", "popFront"]
 * [[], [1], [2], [3], [4], [], [], [], [], []]
 * 输出：
 * [null, null, null, null, null, 1, 3, 4, 2, -1]
 * 解释：
 * FrontMiddleBackQueue q = new FrontMiddleBackQueue();
 * q.pushFront(1);   // [1]
 * q.pushBack(2);    // [1, 2]
 * q.pushMiddle(3);  // [1, 3, 2]
 * q.pushMiddle(4);  // [1, 4, 3, 2]
 * q.popFront();     // 返回 1 -> [4, 3, 2]
 * q.popMiddle();    // 返回 3 -> [4, 2]
 * q.popMiddle();    // 返回 4 -> [2]
 * q.popBack();      // 返回 2 -> []
 * q.popFront();     // 返回 -1 -> [] （队列为空）
 * 提示：
 * 1 <= val <= 10^9
 * 最多调用 1000 次 pushFront， pushMiddle， pushBack， popFront， popMiddle 和 popBack 。
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 *
 * @author huangdu
 */
public class FrontMiddleBackQueue {
    private int n;
    private final Node head;
    private final Node tail;
    private Node middle;

    public FrontMiddleBackQueue() {
        this.n = 0;
        head = new Node(0);
        tail = new Node(0);
        middle = head;
        connect(head, tail);
    }

    public void pushFront(int val) {
        Node cur = new Node(val), next = head.next;
        connect(head, cur);
        connect(cur, next);
        if ((n & 1) == 1) {
            middle = middle.prev;
        }
        add(cur);
    }

    public void pushMiddle(int val) {
        Node cur = new Node(val);
        if ((n & 1) == 0) {
            Node next = middle.next;
            connect(middle, cur);
            connect(cur, next);
        } else {
            Node prev = middle.prev;
            connect(prev, cur);
            connect(cur, middle);
        }
        middle = cur;
        add(cur);
    }

    public void pushBack(int val) {
        Node cur = new Node(val), prev = tail.prev;
        connect(prev, cur);
        connect(cur, tail);
        if ((n & 1) == 0) {
            middle = middle.next;
        }
        add(cur);
    }

    public int popFront() {
        if (isEmpty()) {return -1;}
        Node cur = head.next, next = cur.next;
        connect(head, next);
        if ((n & 1) == 0) {
            middle = middle.next;
        }
        remove(cur);
        return cur.val;
    }

    public int popMiddle() {
        if (isEmpty()) {return -1;}
        Node cur = middle, prev = middle.prev, next = middle.next;
        connect(prev, next);
        if ((n & 1) == 0) {
            middle = middle.next;
        } else {
            middle = middle.prev;
        }
        remove(cur);
        return cur.val;
    }

    public int popBack() {
        if (isEmpty()) {return -1;}
        Node cur = tail.prev, prev = cur.prev;
        connect(prev, tail);
        if ((n & 1) == 1) {
            middle = middle.prev;
        }
        remove(cur);
        return cur.val;
    }

    private boolean isEmpty() {
        return head.next == tail;
    }

    private void add(Node node) {
        if (n++ == 0) {middle = node;}
    }

    private void remove(Node node) {
        if (n-- == 1) {middle = head;}
        node.prev = null;
        node.next = null;
    }

    private void connect(Node left, Node right) {
        left.next = right;
        right.prev = left;
    }

    private static class Node {
        int val;
        Node prev;
        Node next;

        Node(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        FrontMiddleBackQueue fmbq = new FrontMiddleBackQueue();
        System.out.println(fmbq.popMiddle());
        fmbq.pushMiddle(5422);
        fmbq.pushMiddle(532228);
        System.out.println(fmbq.popBack());
        fmbq.pushMiddle(206486);
        fmbq.pushBack(351927);
        System.out.println(fmbq.popFront());
        System.out.println(fmbq.popFront());
    }
}
