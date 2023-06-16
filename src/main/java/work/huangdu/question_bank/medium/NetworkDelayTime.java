package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 743. 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表 times，表示信号经过 有向 边的传递时间。 times[i] = (ui, vi, wi)，其中 ui 是源节点，vi 是目标节点， wi 是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点 K 发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回 -1 。
 * 示例 1：
 * 输入：times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 * 输出：2
 * 示例 2：
 * 输入：times = [[1,2,1]], n = 2, k = 1
 * 输出：1
 * 示例 3：
 * 输入：times = [[1,2,1]], n = 2, k = 2
 * 输出：-1
 * 提示：
 * 1 <= k <= n <= 100
 * 1 <= times.length <= 6000
 * times[i].length == 3
 * 1 <= ui, vi <= n
 * ui != vi
 * 0 <= wi <= 100
 * 所有 (ui, vi) 对都 互不相同（即，不含重复边）
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/8/2
 */
public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[][] graph = new int[n][n];
        for (int[] row : graph) {Arrays.fill(row, -1);}
        for (int[] time : times) {graph[time[0] - 1][time[1] - 1] = time[2];}
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[k - 1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        pq.offer(new int[] {k - 1, 0});
        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int cur = data[0], total = data[1];
            if (dp[cur] < total) {continue;}
            for (int next = 0; next < n; next++) {
                if (graph[cur][next] == -1) {continue;}
                if (dp[next] > total + graph[cur][next]) {
                    dp[next] = total + graph[cur][next];
                    pq.offer(new int[] {next, dp[next]});
                }
            }
        }
        int ans = 0;
        for (int time : dp) {
            if (time == Integer.MAX_VALUE) {return -1;}
            ans = Math.max(ans, time);
        }
        return ans;
    }
}
