package work.huangdu.question_bank.medium;

/**
 * 641. 设计循环双端队列
 * 设计实现双端队列。
 * 实现 MyCircularDeque 类:
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 * 示例 1：
 * 输入
 * ["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
 * [[3], [1], [2], [3], [4], [], [], [], [4], []]
 * 输出
 * [null, true, true, true, false, 2, true, true, true, 4]
 * 解释
 * MyCircularDeque circularDeque = new MycircularDeque(3); // 设置容量大小为3
 * circularDeque.insertLast(1);			        // 返回 true
 * circularDeque.insertLast(2);			        // 返回 true
 * circularDeque.insertFront(3);			        // 返回 true
 * circularDeque.insertFront(4);			        // 已经满了，返回 false
 * circularDeque.getRear();  				// 返回 2
 * circularDeque.isFull();				        // 返回 true
 * circularDeque.deleteLast();			        // 返回 true
 * circularDeque.insertFront(4);			        // 返回 true
 * circularDeque.getFront();				// 返回 4
 * 提示：
 * 1 <= k <= 1000
 * 0 <= value <= 1000
 * insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull  调用次数不大于 2000 次
 *
 * @author huangdu
 * @version 2022/8/15
 */
public class MyCircularDeque {
    private final int n;
    private final int[] array;
    private int front;
    private int rear;
    private int cnt;

    public MyCircularDeque(int k) {
        this.n = k;
        this.array = new int[k];
        this.front = k - 1;
        this.rear = 0;
        this.cnt = 0;
    }

    public boolean insertFront(int value) {
        if (isFull()) {return false;}
        front = next(front);
        array[front] = value;
        add();
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {return false;}
        rear = prev(rear);
        array[rear] = value;
        add();
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {return false;}
        front = prev(front);
        subtract();
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {return false;}
        rear = next(rear);
        subtract();
        return true;
    }

    public int getFront() {
        if (isEmpty()) {return -1;}
        return array[front];
    }

    public int getRear() {
        if (isEmpty()) {return -1;}
        return array[rear];
    }

    public boolean isEmpty() {
        return cnt == 0;
    }

    public boolean isFull() {
        return cnt == n;
    }

    private int prev(int idx) {
        return (idx - 1 + n) % n;
    }

    private int next(int idx) {
        return (idx + 1) % n;
    }

    private void add() {
        cnt++;
    }

    private void subtract() {
        cnt--;
    }

    public static void main(String[] args) {
        // [null,true,true,7,true,7,true,3,9,9,true,0]
        MyCircularDeque mcd = new MyCircularDeque(5);
        System.out.println(mcd.insertFront(7));
        System.out.println(mcd.insertLast(0));
        System.out.println(mcd.getFront());
        System.out.println(mcd.insertLast(3));
        System.out.println(mcd.getFront());
        System.out.println(mcd.insertFront(9));
        System.out.println(mcd.getRear());
        System.out.println(mcd.getFront());
        System.out.println(mcd.getFront());
        System.out.println(mcd.deleteLast());
        System.out.println(mcd.getRear());
    }
}
