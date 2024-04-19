package work.huangdu.question_bank.medium;

import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * 855. 考场就座
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * 返回 ExamRoom(int N) 类，它有两个公开的函数：其中，函数 ExamRoom.seat() 会返回一个 int （整型数据），代表学生坐的位置；函数 ExamRoom.leave(int p) 代表坐在座位 p 上的学生现在离开了考场。每次调用 ExamRoom.leave(p) 时都保证有学生坐在座位 p 上。
 * 示例：
 * 输入：["ExamRoom","seat","seat","seat","seat","leave","seat"], [[10],[],[],[],[],[4],[]]
 * 输出：[null,0,9,4,2,null,5]
 * 解释：
 * ExamRoom(10) -> null
 * seat() -> 0，没有人在考场里，那么学生坐在 0 号座位上。
 * seat() -> 9，学生最后坐在 9 号座位上。
 * seat() -> 4，学生最后坐在 4 号座位上。
 * seat() -> 2，学生最后坐在 2 号座位上。
 * leave(4) -> null
 * seat() -> 5，学生最后坐在 5 号座位上。
 * 提示：
 * 1 <= N <= 10^9
 * 在所有的测试样例中 ExamRoom.seat() 和 ExamRoom.leave() 最多被调用 10^4 次。
 * 保证在调用 ExamRoom.leave(p) 时有学生正坐在座位 p 上。
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 *
 * @author huangdu
 * @version 2022/12/30
 */
public class ExamRoom {
    private int n;
    private TreeSet<Integer> seatSet;
    private PriorityQueue<int[]> pq;

    public ExamRoom(int n) {
        this.n = n;
        this.seatSet = new TreeSet<>();
        this.pq = new PriorityQueue<>((interval1, interval2) -> {
            int d1 = (interval1[1] - interval1[0]) / 2, d2 = (interval2[1] - interval2[0]) / 2;
            return d1 > d2 || d1 == d2 && interval1[0] < interval2[0] ? -1 : 1;
        });
    }

    public int seat() {
        if (seatSet.isEmpty()) {
            seatSet.add(0);
            return 0;
        }
        int left = seatSet.first(), right = n - 1 - seatSet.last();
        while (!pq.isEmpty()) {
            int[] interval = pq.peek();
            if (seatSet.contains(interval[0]) && seatSet.contains(interval[1]) && seatSet.higher(interval[0]) == interval[1]) {
                int distance = (interval[1] - interval[0]) / 2;
                if (distance <= left || distance < right) {break;}
                int seat = interval[0] + distance;
                pq.poll();
                pq.offer(new int[] {interval[0], seat});
                pq.offer(new int[] {seat, interval[1]});
                seatSet.add(seat);
                return seat;
            }
            pq.poll();
        }
        if (left >= right) {
            pq.offer(new int[] {0, left});
            seatSet.add(0);
            return 0;
        }
        pq.offer(new int[] {seatSet.last(), n - 1});
        seatSet.add(n - 1);
        return n - 1;
    }

    public void leave(int p) {
        if (seatSet.first() != p && seatSet.last() != p) {
            pq.offer(new int[] {seatSet.lower(p), seatSet.higher(p)});
        }
        seatSet.remove(p);
    }

    public static void main(String[] args) {
        ExamRoom er = new ExamRoom(10);
        System.out.println(er.seat());
        System.out.println(er.seat());
        System.out.println(er.seat());
        er.leave(0);
        er.leave(4);
    }
}
