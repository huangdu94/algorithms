package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 2642. 设计可以求最短路径的图类
 * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。图中的初始边用数组 edges 表示，其中 edges[i] = [from_i, to_i, edgeCost_i] 表示从 from_i 到 to_i 有一条代价为 edgeCost_i 的边。
 * 请你实现一个 Graph 类：
 * Graph(int n, int[][] edges) 初始化图有 n 个节点，并输入初始边。
 * addEdge(int[] edge) 向边集中添加一条边，其中 edge = [from, to, edgeCost] 。数据保证添加这条边之前对应的两个节点之间没有有向边。
 * int shortestPath(int node1, int node2) 返回从节点 node1 到 node2 的路径 最小 代价。如果路径不存在，返回 -1 。一条路径的代价是路径中所有边代价之和。
 * 示例 1：
 * 输入：
 * ["Graph", "shortestPath", "shortestPath", "addEdge", "shortestPath"]
 * [[4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]], [3, 2], [0, 3], [[1, 3, 4]], [0, 3]]
 * 输出：
 * [null, 6, -1, null, 6]
 * 解释：
 * Graph g = new Graph(4, [[0, 2, 5], [0, 1, 2], [1, 2, 1], [3, 0, 3]]);
 * g.shortestPath(3, 2); // 返回 6 。从 3 到 2 的最短路径如第一幅图所示：3 -> 0 -> 1 -> 2 ，总代价为 3 + 2 + 1 = 6 。
 * g.shortestPath(0, 3); // 返回 -1 。没有从 0 到 3 的路径。
 * g.addEdge([1, 3, 4]); // 添加一条节点 1 到节点 3 的边，得到第二幅图。
 * g.shortestPath(0, 3); // 返回 6 。从 0 到 3 的最短路径为 0 -> 1 -> 3 ，总代价为 2 + 4 = 6 。
 * 提示：
 * 1 <= n <= 100
 * 0 <= edges.length <= n * (n - 1)
 * edges[i].length == edge.length == 3
 * 0 <= from_i, to_i, from, to, node_1, node_2 <= n - 1
 * 1 <= edgeCost_i, edgeCost <= 10^6
 * 图中任何时候都不会有重边和自环。
 * 调用 addEdge 至多 100 次。
 * 调用 shortestPath 至多 100 次。
 * Your Graph object will be instantiated and called as such:
 * Graph obj = new Graph(n, edges);
 * obj.addEdge(edge);
 * int param_2 = obj.shortestPath(node1,node2);
 *
 * @author huangdu
 */
public class Graph {
    private static final int INF = Integer.MAX_VALUE >> 1;
    private final List<int[]>[] graph;
    private final int[] dp;

    public Graph(int n, int[][] edges) {
        this.graph = new List[n];
        this.dp = new int[n];
        Arrays.setAll(graph, (o) -> new ArrayList<>());
        for (int[] edge : edges) {graph[edge[0]].add(new int[] {edge[1], edge[2]});}
    }

    public void addEdge(int[] edge) {
        graph[edge[0]].add(new int[] {edge[1], edge[2]});
    }

    public int shortestPath(int node1, int node2) {
        Arrays.fill(dp, INF);
        dp[node1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        pq.offer(new int[] {0, node1});
        while (!pq.isEmpty()) {
            int[] data = pq.poll();
            int distance = data[0], cur = data[1];
            if (cur == node2) {return distance;}
            if (distance > dp[cur]) {continue;}
            for (int[] nexts : graph[cur]) {
                int next = nexts[0], cost = nexts[1];
                if (cost != 0) {
                    if (distance + cost < dp[next]) {
                        dp[next] = distance + cost;
                        pq.offer(new int[] {dp[next], next});
                    }
                }
            }
        }
        return -1;
    }
}
