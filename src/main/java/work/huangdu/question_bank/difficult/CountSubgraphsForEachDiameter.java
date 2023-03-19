package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 1617. 统计子树中城市之间最大距离
 * 给你 n 个城市，编号为从 1 到 n 。同时给你一个大小为 n-1 的数组 edges ，其中 edges[i] = [ui, vi] 表示城市 ui 和 vi 之间有一条双向边。题目保证任意城市之间只有唯一的一条路径。换句话说，所有城市形成了一棵 树 。
 * 一棵 子树 是城市的一个子集，且子集中任意城市之间可以通过子集中的其他城市和边到达。两个子树被认为不一样的条件是至少有一个城市在其中一棵子树中存在，但在另一棵子树中不存在。
 * 对于 d 从 1 到 n-1 ，请你找到城市间 最大距离 恰好为 d 的所有子树数目。
 * 请你返回一个大小为 n-1 的数组，其中第 d 个元素（下标从 1 开始）是城市间 最大距离 恰好等于 d 的子树数目。
 * 请注意，两个城市间距离定义为它们之间需要经过的边的数目。
 * 示例 1：
 * 输入：n = 4, edges = [[1,2],[2,3],[2,4]]
 * 输出：[3,4,0]
 * 解释：
 * 子树 {1,2}, {2,3} 和 {2,4} 最大距离都是 1 。
 * 子树 {1,2,3}, {1,2,4}, {2,3,4} 和 {1,2,3,4} 最大距离都为 2 。
 * 不存在城市间最大距离为 3 的子树。
 * 示例 2：
 * 输入：n = 2, edges = [[1,2]]
 * 输出：[1]
 * 示例 3：
 * 输入：n = 3, edges = [[1,2],[2,3]]
 * 输出：[2,1]
 * 提示：
 * 2 <= n <= 15
 * edges.length == n-1
 * edges[i].length == 2
 * 1 <= u_i, v_i <= n
 * 题目保证 (u_i, v_i) 所表示的边互不相同。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/3/19
 */
public class CountSubgraphsForEachDiameter {
    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        int[] ans = new int[n - 1];
        List<Integer>[] edgeList = new List[n];
        for (int i = 0; i < n; i++) {edgeList[i] = new ArrayList<>();}
        for (int[] edge : edges) {
            edgeList[edge[0] - 1].add(edge[1] - 1);
            edgeList[edge[1] - 1].add(edge[0] - 1);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int status = 1, limit = 1 << n; status < limit; status++) {
            if ((status & (status - 1)) == 0) {continue;}
            // 1. 枚举可能的子树节点里列表
            int mask = status, start = 0, cur = 0;
            for (int i = 0; i < n; i++) {
                if ((status & (1 << i)) != 0) {
                    start = i;
                    break;
                }
            }
            // 2. 判断是否连通
            queue.offer(start);
            mask &= ~(1 << start);
            while (!queue.isEmpty()) {
                cur = queue.poll();
                for (int next : edgeList[cur]) {
                    if ((mask & (1 << next)) != 0) {
                        mask &= ~(1 << next);
                        queue.offer(next);
                    }
                }
            }
            if (mask == 0) {
                // 3. 求最大距离
                queue.offer(cur);
                mask = status & ~(1 << cur);
                int maxLen = -1;
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    for (int i = 0; i < size; i++) {
                        cur = queue.remove();
                        for (int next : edgeList[cur]) {
                            if ((mask & (1 << next)) != 0) {
                                mask &= ~(1 << next);
                                queue.offer(next);
                            }
                        }
                    }
                    maxLen++;
                }
                ans[maxLen - 1]++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CountSubgraphsForEachDiameter csfed = new CountSubgraphsForEachDiameter();
        int[][] edges = {{1, 2}, {2, 3}, {2, 4}};
        System.out.println(Arrays.toString(csfed.countSubgraphsForEachDiameter(4, edges)));
    }
}
