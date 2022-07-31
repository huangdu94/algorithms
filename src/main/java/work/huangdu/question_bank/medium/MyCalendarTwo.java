package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 731. 我的日程安排表 II
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。否则，返回 false 并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * 示例：
 * MyCalendar();
 * MyCalendar.book(10, 20); // returns true
 * MyCalendar.book(50, 60); // returns true
 * MyCalendar.book(10, 40); // returns true
 * MyCalendar.book(5, 15); // returns false
 * MyCalendar.book(5, 10); // returns true
 * MyCalendar.book(25, 55); // returns true
 * 解释：
 * 前两个日程安排可以添加至日历中。 第三个日程安排会导致双重预订，但可以添加至日历中。
 * 第四个日程安排活动（5,15）不能添加至日历中，因为它会导致三重预订。
 * 第五个日程安排（5,10）可以添加至日历中，因为它未使用已经双重预订的时间10。
 * 第六个日程安排（25,55）可以添加至日历中，因为时间 [25,40] 将和第三个日程安排双重预订；
 * 时间 [40,50] 将单独预订，时间 [50,55）将和第二个日程安排双重预订。
 * 提示：
 * 每个测试用例，调用 MyCalendar.book 函数最多不超过 1000次。
 * 调用函数 MyCalendar.book(start, end)时， start 和 end 的取值范围为 [0, 10^9]。
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/7/19
 */
public class MyCalendarTwo {
    private final Map<Integer, Integer> tree = new HashMap<>();
    private final Map<Integer, Integer> lazy = new HashMap<>();

    public MyCalendarTwo() {}

    public boolean book(int start, int end) {
        if (query(0, 1000000000, start, end - 1, 1) >= 2) {return false;}
        update(0, 1000000000, start, end - 1, 1);
        return true;
    }

    private void update(int l, int r, int start, int end, int idx) {
        if (start <= l && r <= end) {
            tree.merge(idx, 1, Integer::sum);
            lazy.merge(idx, 1, Integer::sum);
            return;
        }
        int mid = l + ((r - l) >> 1), leftIdx = idx << 1, rightIdx = (idx << 1) + 1;
        pushDown(idx, leftIdx, rightIdx);
        if (start <= mid) {update(l, mid, start, end, leftIdx);}
        if (end > mid) {update(mid + 1, r, start, end, rightIdx);}
        pushUp(idx, leftIdx, rightIdx);
    }

    private int query(int l, int r, int start, int end, int idx) {
        if (start <= l && r <= end) {return tree.getOrDefault(idx, 0);}
        int mid = l + ((r - l) >> 1), leftIdx = idx << 1, rightIdx = (idx << 1) + 1;
        pushDown(idx, leftIdx, rightIdx);
        int leftMax = 0, rightMax = 0;
        if (start <= mid) {leftMax = query(l, mid, start, end, leftIdx);}
        if (end > mid) {rightMax = query(mid + 1, r, start, end, rightIdx);}
        return Math.max(leftMax, rightMax);
    }

    private void pushDown(int idx, int leftIdx, int rightIdx) {
        if (!lazy.containsKey(idx)) {return;}
        int lazyVal = lazy.remove(idx);
        tree.merge(leftIdx, lazyVal, Integer::sum);
        lazy.merge(leftIdx, lazyVal, Integer::sum);
        tree.merge(rightIdx, lazyVal, Integer::sum);
        lazy.merge(rightIdx, lazyVal, Integer::sum);
    }

    private void pushUp(int idx, int leftIdx, int rightIdx) {
        tree.put(idx, Math.max(tree.getOrDefault(leftIdx, 0), tree.getOrDefault(rightIdx, 0)));
    }

    public static void main(String[] args) {
        MyCalendarTwo mct = new MyCalendarTwo();
        mct.update(0, 7, 0, 7, 1);
        mct.update(0, 7, 0, 0, 1);
        mct.update(0, 7, 0, 7, 1);
        System.out.println(mct.query(0, 7, 0, 0, 1));
        System.out.println("DONE!");
    }
}
