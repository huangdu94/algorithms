package work.huangdu.question_bank.difficult;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 732. 我的日程安排表 III
 * 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 * MyCalendarThree() 初始化对象。
 * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 * 示例：
 * 输入：
 * ["MyCalendarThree", "book", "book", "book", "book", "book", "book"]
 * [[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
 * 输出：
 * [null, 1, 1, 2, 3, 3, 3]
 * 解释：
 * MyCalendarThree myCalendarThree = new MyCalendarThree();
 * myCalendarThree.book(10, 20); // 返回 1 ，第一个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(50, 60); // 返回 1 ，第二个日程安排可以预订并且不存在相交，所以最大 k 次预订是 1 次预订。
 * myCalendarThree.book(10, 40); // 返回 2 ，第三个日程安排 [10, 40) 与第一个日程安排相交，所以最大 k 次预订是 2 次预订。
 * myCalendarThree.book(5, 15); // 返回 3 ，剩下的日程安排的最大 k 次预订是 3 次预订。
 * myCalendarThree.book(5, 10); // 返回 3
 * myCalendarThree.book(25, 55); // 返回 3
 * 提示：
 * 0 <= start < end <= 10^9
 * 每个测试用例，调用 book 函数最多不超过 400次
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/6/6
 */
public class MyCalendarThree {
    // TODO 线段树需要复习。
    private final Map<Integer, Integer> tree;
    private final Map<Integer, Integer> lazy;

    public MyCalendarThree() {
        this.tree = new HashMap<>();
        this.lazy = new HashMap<>();
    }

    public int book(int start, int end) {
        update(start, end - 1, 0, (int)1e9, 1);
        return tree.get(1);
    }

    private void update(int start, int end, int left, int right, int index) {
        if (right < start || end < left) {return;}
        if (start <= left && right <= end) {
            tree.merge(index, 1, Integer::sum);
            lazy.merge(index, 1, Integer::sum);
        } else {
            int mid = left + ((right - left) >> 1);
            update(start, end, left, mid, 2 * index);
            update(start, end, mid + 1, right, 2 * index + 1);
            tree.put(index, lazy.getOrDefault(index, 0) + Math.max(tree.getOrDefault(index * 2, 0), tree.getOrDefault(index * 2 + 1, 0)));
        }
    }

    class SolutionDiff {
        private final TreeMap<Integer, Integer> diff;

        public SolutionDiff() {
            this.diff = new TreeMap<>(Comparator.comparingInt(o -> o));
        }

        public int book(int start, int end) {
            diff.merge(start, 1, Integer::sum);
            diff.merge(end, -1, Integer::sum);
            int ans = 0, val = 0;
            for (Integer d : diff.values()) {
                ans = Math.max(ans, val += d);
            }
            return ans;
        }
    }
}
