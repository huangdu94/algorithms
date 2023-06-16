package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 2699. 修改图中的边权
 * 给你一个 n 个节点的 无向带权连通 图，节点编号为 0 到 n - 1 ，再给你一个整数数组 edges ，其中 edges[i] = [ai, bi, wi] 表示节点 ai 和 bi 之间有一条边权为 wi 的边。
 * 部分边的边权为 -1（wi = -1），其他边的边权都为 正 数（wi > 0）。
 * 你需要将所有边权为 -1 的边都修改为范围 [1, 2 * 10^9] 中的 正整数 ，使得从节点 source 到节点 destination 的 最短距离 为整数 target 。如果有 多种 修改方案可以使 source 和 destination 之间的最短距离等于 target ，你可以返回任意一种方案。
 * 如果存在使 source 到 destination 最短距离为 target 的方案，请你按任意顺序返回包含所有边的数组（包括未修改边权的边）。如果不存在这样的方案，请你返回一个 空数组 。
 * 注意：你不能修改一开始边权为正数的边。
 * 示例 1：
 * 输入：n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
 * 输出：[[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
 * 解释：上图展示了一个满足题意的修改方案，从 0 到 1 的最短距离为 5 。
 * 示例 2：
 * 输入：n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
 * 输出：[]
 * 解释：上图是一开始的图。没有办法通过修改边权为 -1 的边，使得 0 到 2 的最短距离等于 6 ，所以返回一个空数组。
 * 示例 3：
 * 输入：n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
 * 输出：[[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
 * 解释：上图展示了一个满足题意的修改方案，从 0 到 2 的最短距离为 6 。
 * 提示：
 * 1 <= n <= 100
 * 1 <= edges.length <= n * (n - 1) / 2
 * edges[i].length == 3
 * 0 <= ai, bi < n
 * wi = -1 或者 1 <= wi <= 10^7
 * ai != bi
 * 0 <= source, destination < n
 * source != destination
 * 1 <= target <= 10^9
 * 输入的图是连通图，且没有自环和重边。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/6/15
 */
public class ModifiedGraphEdges {
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        List<int[]>[] graph = new List[n];
        Arrays.setAll(graph, (o) -> new ArrayList<>());
        for (int i = 0, size = edges.length; i < size; i++) {
            int x = edges[i][0], y = edges[i][1];
            graph[x].add(new int[] {y, i});
            graph[y].add(new int[] {x, i});
        }
        int[][] dists = new int[2][n];
        for (int[] array : dists) {Arrays.fill(array, Integer.MAX_VALUE);}
        dists[0][source] = dists[1][source] = 0;
        dijkstra(graph, dists, n, edges, destination, target, 0);
        if (dists[0][destination] > target) {return new int[0][0];}
        dijkstra(graph, dists, n, edges, destination, target, 1);
        if (dists[1][destination] < target) {return new int[0][0];}
        for (int[] edge : edges) {
            if (edge[2] == -1) {edge[2] = 1;}
        }
        return edges;
    }

    /**
     * source -> x -> y -> y -> destination
     * dist[x][1] + (y - x) + dist[destination][0] - dist[y][0] = target
     * y - x = target - dist[destination][0] + dist[y][0] - dist[x][1];
     */
    private void dijkstra(List<int[]>[] graph, int[][] dists, int n, int[][] edges, int destination, int target, int k) {
        int[] dist = dists[k];
        boolean[] visited = new boolean[n];
        while (true) {
            int cur = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (cur == -1 || dist[i] < dist[cur])) {cur = i;}
            }
            if (cur == destination) {break;}
            visited[cur] = true;
            for (int[] nextData : graph[cur]) {
                int next = nextData[0], edgeIdx = nextData[1], weight = edges[edgeIdx][2];
                if (weight == -1) {
                    if (k == 0) {
                        weight = 1;
                    } else {
                        weight = Math.max(target - dists[0][destination] + dists[0][next] - dists[1][cur], 1);
                        edges[edgeIdx][2] = weight;
                    }
                }
                dist[next] = Math.min(dist[next], dist[cur] + weight);
            }
        }
    }
}
