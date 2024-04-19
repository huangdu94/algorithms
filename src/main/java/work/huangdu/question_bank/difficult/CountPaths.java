package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2867. 统计树中的合法路径数目
 * 给你一棵 n 个节点的无向树，节点编号为 1 到 n 。给你一个整数 n 和一个长度为 n - 1 的二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示节点 ui 和 vi 在树中有一条边。
 * 请你返回树中的 合法路径数目 。
 * 如果在节点 a 到节点 b 之间 恰好有一个 节点的编号是质数，那么我们称路径 (a, b) 是 合法的 。
 * 注意：
 * 路径 (a, b) 指的是一条从节点 a 开始到节点 b 结束的一个节点序列，序列中的节点 互不相同 ，且相邻节点之间在树上有一条边。
 * 路径 (a, b) 和路径 (b, a) 视为 同一条 路径，且只计入答案 一次 。
 * 示例 1：
 * 输入：n = 5, edges = [[1,2],[1,3],[2,4],[2,5]]
 * 输出：4
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * 只有 4 条合法路径。
 * 示例 2：
 * 输入：n = 6, edges = [[1,2],[1,3],[2,4],[3,5],[3,6]]
 * 输出：6
 * 解释：恰好有一个质数编号的节点路径有：
 * - (1, 2) 因为路径 1 到 2 只包含一个质数 2 。
 * - (1, 3) 因为路径 1 到 3 只包含一个质数 3 。
 * - (1, 4) 因为路径 1 到 4 只包含一个质数 2 。
 * - (1, 6) 因为路径 1 到 6 只包含一个质数 3 。
 * - (2, 4) 因为路径 2 到 4 只包含一个质数 2 。
 * - (3, 6) 因为路径 3 到 6 只包含一个质数 3 。
 * 只有 6 条合法路径。
 * 提示：
 * 1 <= n <= 10^5
 * edges.length == n - 1
 * edges[i].length == 2
 * 1 <= u_i, v_i <= n
 * 输入保证 edges 形成一棵合法的树。
 *
 * @author huangdu
 */
public class CountPaths {
    private static final Set<Integer> PRIME_SET = new HashSet<>();

    static {
        for (int i = 1; i <= 100000; i++) {
            if (isPrime(i)) {PRIME_SET.add(i);}
        }
    }

    private static boolean isPrime(int n) {
        if (n <= 3) {return n > 1;}
        if (n % 2 == 0) {return false;}
        if (n % 6 != 1 && n % 6 != 5) {return false;}
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private Map<Integer, Integer> memo;
    private List<Integer> visited;

    public long countPaths(int n, int[][] edges) {
        List<Integer>[] graph = new List[n + 1];
        Arrays.setAll(graph, (o) -> new ArrayList<>());
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        this.memo = new HashMap<>();
        this.visited = new ArrayList<>();
        long ans = 0;
        for (int start = 1; start <= n; start++) {
            if (!PRIME_SET.contains(start)) {continue;}
            long cur = 0;
            for (int next : graph[start]) {
                if (!PRIME_SET.contains(next)) {
                    int size;
                    if (memo.containsKey(next)) {
                        size = memo.get(next);
                    } else {
                        visited.clear();
                        dfs(graph, start, next);
                        size = visited.size();
                        for (int node : visited) {
                            memo.put(node, size);
                        }
                    }
                    ans += size * cur;
                    cur += size;
                }
            }
            ans += cur;
        }
        return ans;
    }

    private void dfs(List<Integer>[] graph, int pre, int start) {
        visited.add(start);
        for (int next : graph[start]) {
            if (next != pre && !PRIME_SET.contains(next)) {
                dfs(graph, start, next);
            }
        }
    }

    public static void main(String[] args) {
        CountPaths cp = new CountPaths();
        System.out.println(cp.countPaths(5, new int[][] {{1, 2}, {1, 3}, {2, 4}, {2, 5}}));
    }
}
