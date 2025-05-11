package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1976. 到达目的地的方案数
 * 你在一个城市里，城市由 n 个路口组成，路口编号为 0 到 n - 1 ，某些路口之间有 双向 道路。输入保证你可以从任意路口出发到达其他任意路口，且任意两个路口之间最多有一条路。
 * 给你一个整数 n 和二维整数数组 roads ，其中 roads[i] = [ui, vi, timei] 表示在路口 ui 和 vi 之间有一条需要花费 timei 时间才能通过的道路。你想知道花费 最少时间 从路口 0 出发到达路口 n - 1 的方案数。
 * 请返回花费 最少时间 到达目的地的 路径数目 。由于答案可能很大，将结果对 10^9 + 7 取余 后返回。
 * 示例 1：
 * 输入：n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
 * 输出：4
 * 解释：从路口 0 出发到路口 6 花费的最少时间是 7 分钟。
 * 四条花费 7 分钟的路径分别为：
 * - 0 ➝ 6
 * - 0 ➝ 4 ➝ 6
 * - 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
 * - 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
 * 示例 2：
 * 输入：n = 2, roads = [[1,0,10]]
 * 输出：1
 * 解释：只有一条从路口 0 到路口 1 的路，花费 10 分钟。
 * 提示：
 * 1 <= n <= 200
 * n - 1 <= roads.length <= n * (n - 1) / 2
 * roads[i].length == 3
 * 0 <= ui, vi <= n - 1
 * 1 <= timei <= 10^9
 * ui != vi
 * 任意两个路口之间至多有一条路。
 * 从任意路口出发，你能够到达其他任意路口。
 *
 * @author huangdu
 */
public class CountPaths {
    public int countPaths(int n, int[][] roads) {
        List<int[]>[] graph = new List[n];
        Arrays.setAll(graph, (o) -> new ArrayList<>());
        for (int[] road : roads) {
            int x = road[0], y = road[1], t = road[2];
            graph[x].add(new int[] {y, t});
            graph[y].add(new int[] {x, t});
        }
        long[] visited = new long[n];
        int[] ans = new int[n];
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[1]));
        Arrays.fill(visited, Long.MAX_VALUE);
        visited[0] = 0;
        ans[0] = 1;
        pq.offer(new long[] {0, 0L});
        while (!pq.isEmpty()) {
            long[] data = pq.poll();
            int cur = (int)data[0];
            long time = data[1];
            if (cur == n - 1) {break;}
            if (time > visited[cur]) {continue;}
            for (int[] nexts : graph[cur]) {
                int next = nexts[0];
                long newTime = time + nexts[1];
                if (visited[next] >= newTime) {
                    if (visited[next] > newTime) {
                        visited[next] = newTime;
                        ans[next] = 0;
                        pq.offer(new long[] {next, newTime});
                    }
                    ans[next] = (ans[next] + ans[cur]) % 1000000007;
                }
            }
        }
        return ans[n - 1];
    }

    public static void main(String[] args) {
        case1();
        case2();
    }

    private static void case1() {
        int[][] roads = {{1, 0, 1983}, {1, 2, 4922}, {2, 3, 3643}, {2, 4, 9994}, {1, 4, 14916}, {4, 0, 16899}, {5, 4, 4354}, {0, 5, 21253}, {2, 6, 15836}, {6, 1, 20758}, {6, 0, 22741}, {6, 3, 12193}, {6, 5, 1488}, {4, 6, 5842},
            {7, 0, 18855}, {7, 1, 16872}, {7, 3, 8307}, {8, 7, 9446}, {4, 8, 11402}, {3, 8, 17753}, {8, 6, 5560}, {4, 9, 13109}, {6, 9, 7267}, {8, 9, 1707}, {3, 9, 19460}, {9, 7, 11153}, {0, 9, 30008}, {9, 5, 8755}, {9, 1, 28025},
            {9, 2, 23103}, {5, 10, 13248}, {3, 10, 23953}, {1, 10, 32518}, {7, 10, 15646}, {9, 10, 4493}, {11, 9, 7385}, {12, 3, 36592}, {12, 5, 25887}, {7, 12, 28285}, {12, 11, 9747}, {12, 6, 24399}, {2, 12, 40235}, {13, 8, 18968},
            {4, 13, 30370}, {13, 0, 47269}, {13, 2, 40364}, {5, 13, 26016}, {13, 9, 17261}, {11, 13, 9876}, {13, 3, 36721}, {1, 13, 45286}, {7, 13, 28414}, {10, 13, 12768}, {2, 14, 48419}, {14, 1, 53341}, {12, 14, 8184}, {14, 11, 17931},
            {14, 9, 25316}, {15, 0, 62624}, {9, 15, 32616}, {15, 14, 7300}, {13, 15, 15355}, {15, 2, 55719}, {16, 5, 31585}, {13, 16, 5569}, {0, 16, 52838}, {12, 16, 5698}, {16, 9, 22830}, {16, 10, 18337}, {16, 11, 15445}, {1, 16, 50855},
            {6, 16, 30097}, {16, 7, 33983}, {16, 17, 6677}, {17, 1, 57532}, {14, 17, 4191}, {17, 8, 31214}, {0, 17, 59515}, {18, 14, 1723}, {12, 18, 9907}, {0, 18, 57047}, {5, 18, 35794}, {16, 18, 4209}, {10, 18, 22546}, {18, 3, 46499},
            {19, 7, 10407}, {19, 8, 961}, {6, 20, 38441}, {2, 20, 54277}, {11, 20, 23789}, {20, 12, 14042}, {20, 1, 59199}, {14, 20, 5858}, {2, 21, 55541}, {21, 18, 5399}, {21, 1, 60463}};
        CountPaths cp = new CountPaths();
        int ans = cp.countPaths(22, roads);
        System.out.println(ans);
    }

    private static void case2() {
        int[][] roads = {{0, 1, 1}, {1, 2, 4}, {0, 4, 3}, {3, 2, 5}, {3, 4, 1}, {3, 0, 5}, {1, 3, 1}};
        CountPaths cp = new CountPaths();
        int ans = cp.countPaths(5, roads);
        System.out.println(ans);
    }
}
