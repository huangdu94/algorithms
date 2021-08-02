package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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
        if (times.length == 0) { return 0;}
        List<int[]>[] edgeMap = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            edgeMap[i] = new ArrayList<>();
        }
        for (int[] time : times) {
            edgeMap[time[0]].add(time);
        }
        int[] timeMap = new int[n + 1];
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o[2] + timeMap[o[0]]));
        timeMap[k] = 0;
        visited.add(k);
        priorityQueue.addAll(edgeMap[k]);
        int max = 0;
        while (visited.size() < n && !priorityQueue.isEmpty()) {
            int[] time = priorityQueue.poll();
            if (visited.add(time[1])) {
                int sum = timeMap[time[0]] + time[2];
                timeMap[time[1]] = sum;
                max = Math.max(max, sum);
                List<int[]> nextEdge = edgeMap[time[1]];
                priorityQueue.addAll(nextEdge);
            }
        }
        return visited.size() == n ? max : -1;
    }

    public static void main(String[] args) {
        int[][] times = {{3, 5, 78}, {2, 1, 1}, {1, 3, 0}, {4, 3, 59}, {5, 3, 85}, {5, 2, 22}, {2, 4, 23}, {1, 4, 43}, {4, 5, 75}, {5, 1, 15}, {1, 5, 91}, {4, 1, 16}, {3, 2, 98}, {3, 4, 22},
            {5, 4, 31}, {1, 2, 0}, {2, 5, 4}, {4, 2, 51}, {3, 1, 36}, {2, 3, 59}};
        int n = 5;
        int k = 5;
        NetworkDelayTime ndt = new NetworkDelayTime();
        System.out.println(ndt.networkDelayTime(times, n, k));
    }
}
