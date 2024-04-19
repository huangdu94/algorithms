package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 630. 课程表 III
 * 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，并且必须在不晚于 lastDayi 的时候完成。
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * 返回你最多可以修读的课程数目。
 * 示例 1：
 * 输入：courses = [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
 * 输出：3
 * 解释：
 * 这里一共有 4 门课程，但是你最多可以修 3 门：
 * 首先，修第 1 门课，耗费 100 天，在第 100 天完成，在第 101 天开始下门课。
 * 第二，修第 3 门课，耗费 1000 天，在第 1100 天完成，在第 1101 天开始下门课程。
 * 第三，修第 2 门课，耗时 200 天，在第 1300 天完成。
 * 第 4 门课现在不能修，因为将会在第 3300 天完成它，这已经超出了关闭日期。
 * 示例 2：
 * 输入：courses = [[1,2]]
 * 输出：1
 * 示例 3：
 * 输入：courses = [[3,2],[4,3]]
 * 输出：0
 * 提示:
 * 1 <= courses.length <= 10^4
 * 1 <= durationi, lastDayi <= 10^4
 *
 * @author huangdu
 * @version 2021/12/20
 */
public class ScheduleCourse {
    /**
     * 证明：结束时间早的课应当先学
     * 设两门课 (d1,l1) (d2,l2)  l1 < l2
     * 先学第一门课需要满足
     * d1 <= l1
     * d1 + d2 <= l2
     * 先学第二门课需要满足
     * d2 <= l2
     * d1 + d2 <= l1
     * 对于 d1 <= l1 和 d2 <= l2 如果有一条不满足，则两门课本就不可能同时学，
     * 而对于 d1 + d2 <= l2 和 d1 + d2 <= l1 ，由于l1 < l2，所以满足d1 + d2 <= l2 一定能满足 d1 + d2 <= l1，反之则不是
     * 所以结束时间早的课应当先学
     * 假设到结束时间小于或等于lx的所有课，最优解（也就是可以学的课最多的情况，且总时间加起来最小）为
     * d1 + d2 + ... + dx <= lx
     * 那么此时来了一个结束时间为ly的课，分为两种情况：
     * 1. d1 + d2 + ... + dx + dy <= ly 则直接将这个课加入即可
     * 2. d1 + d2 + ... + dx + dy > ly 此时则不可直接将这门课加入，又分为两种情况
     * 2.1 dy>d1,d2,...,dx则这门课没有任何价值直接放弃
     * 2.2 dy小于d1,d2,...,dx的某一个，这时设d1,d2,...,dx中最大的是dk，则将dk替换成dy，将会得到更好的结果（很容易想清楚）
     * 那么如何解这题显而易见
     * 贪心 + 优先队列
     */
    public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, Comparator.comparingInt((int[] o) -> o[1]).thenComparingInt(o -> o[0]));
        Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        int totalTime = 0;
        for (int[] course : courses) {
            int duration = course[0], lastDay = course[1];
            if (totalTime + duration <= lastDay) {
                totalTime += duration;
                maxHeap.offer(duration);
            } else if (!maxHeap.isEmpty() && duration < maxHeap.peek()) {
                totalTime += (duration - maxHeap.poll());
                maxHeap.offer(duration);
            }
        }
        return maxHeap.size();
    }

    public static void main(String[] args) {
        ScheduleCourse sc = new ScheduleCourse();
        int[][] courses = {{3, 2}, {4, 3}};
        System.out.println(sc.scheduleCourse(courses));
    }
}
