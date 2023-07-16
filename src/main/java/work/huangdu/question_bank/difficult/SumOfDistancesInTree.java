package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 834. 树中距离之和
 * 给定一个无向、连通的树。树中有 n 个标记为 0...n-1 的节点以及 n-1 条边 。
 * 给定整数 n 和数组 edges ， edges[i] = [ai, bi]表示树中的节点 ai 和 bi 之间有一条边。
 * 返回长度为 n 的数组 answer ，其中 answer[i] 是树中第 i 个节点与所有其他节点之间的距离之和。
 * 示例 1:
 * 输入: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释: 树如图所示。
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 * 示例 2:
 * 输入: n = 1, edges = []
 * 输出: [0]
 * 示例 3:
 * 输入: n = 2, edges = [[1,0]]
 * 输出: [1,1]
 * 提示:
 * 1 <= n <= 3 * 10^4
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * 给定的输入保证为有效的树
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/6 13:40
 */
public class SumOfDistancesInTree {
    static class Solution {
        private int n;
        private int[] ans;
        private int[] size;
        private List<Integer>[] graph;

        public int[] sumOfDistancesInTree(int n, int[][] edges) {
            this.n = n;
            this.ans = new int[n];
            this.size = new int[n];
            this.graph = new List[n];
            Arrays.setAll(graph, (o) -> new ArrayList<>());
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
            dfs(-1, 0, 0);
            dfs(-1, 0);
            return ans;
        }

        private void dfs(int father, int cur, int level) {
            ans[0] += level;
            size[cur] = 1;
            for (int child : graph[cur]) {
                if (child == father) {continue;}
                dfs(cur, child, level + 1);
                size[cur] += size[child];
            }
        }

        private void dfs(int father, int cur) {
            if (father != -1) {
                ans[cur] = ans[father] + n - 2 * size[cur];
            }
            for (int child : graph[cur]) {
                if (child == father) {continue;}
                dfs(cur, child);
            }
        }

        public static void main(String[] args) {
            SumOfDistancesInTree.Solution solution = new SumOfDistancesInTree.Solution();
            int n = 6;
            int[][] edges = {{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}};
            System.out.println(Arrays.toString(solution.sumOfDistancesInTree(n, edges)));
        }
    }

    private List<List<Integer>> graph;
    private int[] ans;
    private int[] dp;
    private int[] nc;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        graph = new ArrayList<>();
        ans = new int[N];
        dp = new int[N];
        nc = new int[N];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        // 之所以采用图而非树，是因为如果用树的话必须确定根节点
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs(int u, int f) {
        dp[u] = 0;
        nc[u] = 1;
        for (int v : graph.get(u)) {
            if (v == f) {continue;}
            dfs(v, u);
            dp[u] += dp[v] + nc[v];
            nc[u] += nc[v];
        }
    }

    private void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v : graph.get(u)) {
            if (v == f) {continue;}
            int dpu = dp[u], dpv = dp[v], ncu = nc[u], ncv = nc[v];
            dp[u] -= (dp[v] + nc[v]);
            nc[u] -= nc[v];
            dp[v] += (dp[u] + nc[u]);
            nc[v] += nc[u];
            dfs2(v, u);
            // 回溯
            dp[u] = dpu;
            nc[u] = ncu;
            dp[v] = dpv;
            nc[v] = ncv;
        }
    }
}
