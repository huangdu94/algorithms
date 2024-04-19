package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1851. 包含每个查询的最小区间
 * 给你一个二维整数数组 intervals ，其中 intervals[i] = [lefti, righti] 表示第 i 个区间开始于 lefti 、结束于 righti（包含两侧取值，闭区间）。区间的 长度 定义为区间中包含的整数数目，更正式地表达是 righti - lefti + 1 。
 * 再给你一个整数数组 queries 。第 j 个查询的答案是满足 lefti <= queries[j] <= righti 的 长度最小区间 i 的长度 。如果不存在这样的区间，那么答案是 -1 。
 * 以数组形式返回对应查询的所有答案。
 * 示例 1：
 * 输入：intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
 * 输出：[3,3,1,4]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,4] 是包含 2 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 3 ：区间 [2,4] 是包含 3 的最小区间，答案为 4 - 2 + 1 = 3 。
 * - Query = 4 ：区间 [4,4] 是包含 4 的最小区间，答案为 4 - 4 + 1 = 1 。
 * - Query = 5 ：区间 [3,6] 是包含 5 的最小区间，答案为 6 - 3 + 1 = 4 。
 * 示例 2：
 * 输入：intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
 * 输出：[2,-1,4,6]
 * 解释：查询处理如下：
 * - Query = 2 ：区间 [2,3] 是包含 2 的最小区间，答案为 3 - 2 + 1 = 2 。
 * - Query = 19：不存在包含 19 的区间，答案为 -1 。
 * - Query = 5 ：区间 [2,5] 是包含 5 的最小区间，答案为 5 - 2 + 1 = 4 。
 * - Query = 22：区间 [20,25] 是包含 22 的最小区间，答案为 25 - 20 + 1 = 6 。
 * 提示：
 * 1 <= intervals.length <= 10^5
 * 1 <= queries.length <= 10^5
 * intervals[i].length == 2
 * 1 <= lefti <= righti <= 10^7
 * 1 <= queries[j] <= 10^7
 *
 * @author huangdu
 * @version 2023/7/18
 */
public class MinInterval {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int m = intervals.length, n = queries.length, k = 0;
        Integer[] queryIdxs = new Integer[n];
        for (int i = 0; i < n; i++) {queryIdxs[i] = i;}
        Arrays.sort(queryIdxs, Comparator.comparingInt(idx -> queries[idx]));
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((interval1, interval2) -> {
            int len1 = interval1[1] - interval1[0], len2 = interval2[1] - interval2[0];
            if (len1 == len2) {return interval1[1] - interval2[1];}
            return len1 - len2;
        });
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int query = queries[queryIdxs[i]];
            while (k < m && intervals[k][0] <= query) {
                pq.offer(intervals[k++]);
            }
            while (!pq.isEmpty() && pq.peek()[1] < query) {pq.poll();}
            ans[queryIdxs[i]] = pq.isEmpty() ? -1 : pq.peek()[1] - pq.peek()[0] + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        MinInterval mi = new MinInterval();
        int[][] intervals = {{1, 4}, {2, 4}, {3, 6}, {4, 4}};
        int[] queries = {2, 3, 4, 5};
        System.out.println(Arrays.toString(mi.minInterval(intervals, queries)));
    }
}
