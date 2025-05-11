package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.TreeMap;

/**
 * 1825. 求出 MK 平均值
 * 给你两个整数 m 和 k ，以及数据流形式的若干整数。你需要实现一个数据结构，计算这个数据流的 MK 平均值 。
 * MK 平均值 按照如下步骤计算：
 * 如果数据流中的整数少于 m 个，MK 平均值 为 -1 ，否则将数据流中最后 m 个元素拷贝到一个独立的容器中。
 * 从这个容器中删除最小的 k 个数和最大的 k 个数。
 * 计算剩余元素的平均值，并 向下取整到最近的整数 。
 * 请你实现 MKAverage 类：
 * MKAverage(int m, int k) 用一个空的数据流和两个整数 m 和 k 初始化 MKAverage 对象。
 * void addElement(int num) 往数据流中插入一个新的元素 num 。
 * int calculateMKAverage() 对当前的数据流计算并返回 MK 平均数 ，结果需 向下取整到最近的整数 。
 * 示例 1：
 * 输入：
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage", "addElement", "calculateMKAverage", "addElement", "addElement", "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * 输出：
 * [null, null, null, -1, null, 3, null, null, null, 5]
 * 解释：
 * MKAverage obj = new MKAverage(3, 1);
 * obj.addElement(3);        // 当前元素为 [3]
 * obj.addElement(1);        // 当前元素为 [3,1]
 * obj.calculateMKAverage(); // 返回 -1 ，因为 m = 3 ，但数据流中只有 2 个元素
 * obj.addElement(10);       // 当前元素为 [3,1,10]
 * obj.calculateMKAverage(); // 最后 3 个元素为 [3,1,10]
 * // 删除最小以及最大的 1 个元素后，容器为 [3]
 * // [3] 的平均值等于 3/1 = 3 ，故返回 3
 * obj.addElement(5);        // 当前元素为 [3,1,10,5]
 * obj.addElement(5);        // 当前元素为 [3,1,10,5,5]
 * obj.addElement(5);        // 当前元素为 [3,1,10,5,5,5]
 * obj.calculateMKAverage(); // 最后 3 个元素为 [5,5,5]
 * // 删除最小以及最大的 1 个元素后，容器为 [5]
 * // [5] 的平均值等于 5/1 = 5 ，故返回 5
 * 提示：
 * 3 <= m <= 10^5
 * 1 <= k*2 < m
 * 1 <= num <= 10^5
 * addElement 与 calculateMKAverage 总操作次数不超过 10^5 次。
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 *
 * @author huangdu
 * @version 2023/1/29
 */
public class MKAverage {
    private final int m;
    private final int k;
    private final Queue<Integer> queue;
    private final TreeMap<Integer, Integer> small;
    private final TreeMap<Integer, Integer> medium;
    private final TreeMap<Integer, Integer> big;
    private int total;

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        this.queue = new ArrayDeque<>(m);
        this.small = new TreeMap<>();
        this.medium = new TreeMap<>();
        this.big = new TreeMap<>();
        this.total = 0;
    }

    public void addElement(int num) {
        queue.offer(num);
        if (queue.size() <= m) {
            big.merge(num, 1, Integer::sum);
            if (queue.size() == m) {init();}
            return;
        }
        int[] diff = new int[3];
        if (num > medium.lastKey()) {
            big.merge(num, 1, Integer::sum);
            diff[2]++;
        } else if (num < medium.firstKey()) {
            small.merge(num, 1, Integer::sum);
            diff[0]++;
        } else {
            medium.merge(num, 1, Integer::sum);
            total += num;
            diff[1]++;
        }
        int remove = queue.remove();
        if (remove > medium.lastKey()) {
            if (big.merge(remove, -1, Integer::sum) == 0) {
                big.remove(remove);
            }
            diff[2]--;
        } else if (remove < medium.firstKey()) {
            if (small.merge(remove, -1, Integer::sum) == 0) {
                small.remove(remove);
            }
            diff[0]--;
        } else {
            if (medium.merge(remove, -1, Integer::sum) == 0) {
                medium.remove(remove);
            }
            total -= remove;
            diff[1]--;
        }
        if (diff[0] == 1 && diff[1] == -1) {
            int change = small.lastKey();
            if (small.merge(change, -1, Integer::sum) == 0) {
                small.remove(change);
            }
            medium.merge(change, 1, Integer::sum);
            total += change;
        } else if (diff[0] == -1 && diff[1] == 1) {
            int change = medium.firstKey();
            small.merge(change, 1, Integer::sum);
            if (medium.merge(change, -1, Integer::sum) == 0) {
                medium.remove(change);
            }
            total -= change;
        } else if (diff[1] == 1) {
            int change = medium.lastKey();
            if (medium.merge(change, -1, Integer::sum) == 0) {
                medium.remove(change);
            }
            big.merge(change, 1, Integer::sum);
            total -= change;
        } else if (diff[1] == -1) {
            int change = big.firstKey();
            medium.merge(change, 1, Integer::sum);
            if (big.merge(change, -1, Integer::sum) == 0) {
                big.remove(change);
            }
            total += change;
        } else if (diff[0] == 1) {
            int change = small.lastKey();
            if (small.merge(change, -1, Integer::sum) == 0) {
                small.remove(change);
            }
            medium.merge(change, 1, Integer::sum);
            total += change;
            change = medium.lastKey();
            if (medium.merge(change, -1, Integer::sum) == 0) {
                medium.remove(change);
            }
            big.merge(change, 1, Integer::sum);
            total -= change;
        } else if (diff[0] == -1) {
            int change = medium.firstKey();
            small.merge(change, 1, Integer::sum);
            if (medium.merge(change, -1, Integer::sum) == 0) {
                medium.remove(change);
            }
            total -= change;
            change = big.firstKey();
            medium.merge(change, 1, Integer::sum);
            if (big.merge(change, -1, Integer::sum) == 0) {
                big.remove(change);
            }
            total += change;
        }
    }

    public int calculateMKAverage() {
        if (queue.size() < m) {return -1;}
        return total / (m - 2 * k);
    }

    private void init() {
        for (int i = 0; i < k; i++) {
            int smallKey = big.firstKey();
            small.merge(smallKey, 1, Integer::sum);
            if (big.merge(smallKey, -1, Integer::sum) == 0) {
                big.remove(smallKey);
            }
        }
        for (int i = k; i < m - k; i++) {
            int smallKey = big.firstKey();
            total += smallKey;
            medium.merge(smallKey, 1, Integer::sum);
            if (big.merge(smallKey, -1, Integer::sum) == 0) {
                big.remove(smallKey);
            }
        }
    }

    public static void main(String[] args) {
        MKAverage mk = new MKAverage(6, 1);
        mk.addElement(3);
        mk.addElement(1);
        mk.addElement(12);
        mk.addElement(5);
        mk.addElement(3);
        mk.addElement(4);
        System.out.println(mk.calculateMKAverage());
    }
}
