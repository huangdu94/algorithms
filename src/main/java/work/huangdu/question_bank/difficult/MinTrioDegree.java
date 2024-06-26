package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 1761. 一个图中连通三元组的最小度数
 * 给你一个无向图，整数 n 表示图中节点的数目，edges 数组表示图中的边，其中 edges[i] = [ui, vi] ，表示 ui 和 vi 之间有一条无向边。
 * 一个 连通三元组 指的是 三个 节点组成的集合且这三个点之间 两两 有边。
 * 连通三元组的度数 是所有满足此条件的边的数目：一个顶点在这个三元组内，而另一个顶点不在这个三元组内。
 * 请你返回所有连通三元组中度数的 最小值 ，如果图中没有连通三元组，那么返回 -1 。
 * 示例 1：
 * 输入：n = 6, edges = [[1,2],[1,3],[3,2],[4,1],[5,2],[3,6]]
 * 输出：3
 * 解释：只有一个三元组 [1,2,3] 。构成度数的边在上图中已被加粗。
 * 示例 2：
 * 输入：n = 7, edges = [[1,3],[4,1],[4,3],[2,5],[5,6],[6,7],[7,5],[2,6]]
 * 输出：0
 * 解释：有 3 个三元组：
 * 1) [1,4,3]，度数为 0 。
 * 2) [2,5,6]，度数为 2 。
 * 3) [5,6,7]，度数为 2 。
 * 提示：
 * 2 <= n <= 400
 * edges[i].length == 2
 * 1 <= edges.length <= n * (n-1) / 2
 * 1 <= ui, vi <= n
 * ui != vi
 * 图中没有重复的边。
 *
 * @author huangdu
 * @version 2023/8/31
 */
public class MinTrioDegree {
    public int minTrioDegree(int n, int[][] edges) {
        int ans = -1;
        Set<Integer>[] graph = new Set[n];
        Arrays.setAll(graph, (o) -> new HashSet<>());
        for (int[] edge : edges) {
            int u = edge[0] - 1, v = edge[1] - 1;
            graph[u].add(v);
            graph[v].add(u);
        }
        for (int i = 0; i < n; i++) {
            Set<Integer> nextI = graph[i];
            for (int j : nextI) {
                if (i < j) {
                    Set<Integer> nextJ = graph[j];
                    for (int k : nextJ) {
                        if (j < k && nextI.contains(k)) {
                            int degree = nextI.size() + nextJ.size() + graph[k].size() - 6;
                            if (ans == -1 || ans > degree) {
                                ans = degree;
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }
}
