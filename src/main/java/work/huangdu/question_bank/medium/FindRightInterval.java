package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 436. 寻找右区间
 * 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 的最小起始位置组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * 示例 1：
 * 输入：intervals = [[1,2]]
 * 输出：[-1]
 * 解释：集合中只有一个区间，所以输出-1。
 * 示例 2：
 * 输入：intervals = [[3,4],[2,3],[1,2]]
 * 输出：[-1,0,1]
 * 解释：对于 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间[3,4]具有最小的“右”起点;
 * 对于 [1,2] ，区间[2,3]具有最小的“右”起点。
 * 示例 3：
 * 输入：intervals = [[1,4],[2,3],[3,4]]
 * 输出：[-1,2,-1]
 * 解释：对于区间 [1,4] 和 [3,4] ，没有满足条件的“右侧”区间。
 * 对于 [2,3] ，区间 [3,4] 有最小的“右”起点。
 * 提示：
 * 1 <= intervals.length <= 2 * 10^4
 * intervals[i].length == 2
 * -10^6 <= starti <= endi <= 10^6
 * 每个间隔的起点都 不相同
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/20
 */
public class FindRightInterval {
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];
        int[][] sortIntervals = new int[n][3];
        for (int i = 0; i < n; i++) {
            sortIntervals[i][0] = intervals[i][0];
            sortIntervals[i][1] = intervals[i][1];
            sortIntervals[i][2] = i;
        }
        Arrays.sort(sortIntervals, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++) {
            final int end = sortIntervals[i][1];
            int left = i, right = n;
            while (left < right) {
                int mid = left + ((right - left) >> 1);
                if (sortIntervals[mid][0] >= end) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans[sortIntervals[i][2]] = left == n ? -1 : sortIntervals[left][2];
        }
        return ans;
    }

    public static void main(String[] args) {
        FindRightInterval fri = new FindRightInterval();
        int[][] intervals = {{3, 4}, {2, 3}, {1, 2}};
        System.out.println(Arrays.toString(fri.findRightInterval(intervals)));
    }
}
