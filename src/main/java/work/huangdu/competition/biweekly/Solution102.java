package work.huangdu.competition.biweekly;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import work.huangdu.data_structure.TreeNode;

/**
 * 第 102 场双周赛
 *
 * @author huangdu
 * @version 2023/4/15
 */
public class Solution102 {
    /**
     * 6333. 查询网格图中每一列的宽度
     * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。矩阵中某一列的宽度是这一列数字的最大 字符串长度 。
     * 比方说，如果 grid = [[-10], [3], [12]] ，那么唯一一列的宽度是 3 ，因为 -10 的字符串长度为 3 。
     * 请你返回一个大小为 n 的整数数组 ans ，其中 ans[i] 是第 i 列的宽度。
     * 一个有 len 个数位的整数 x ，如果是非负数，那么 字符串长度 为 len ，否则为 len + 1 。
     * 示例 1：
     * 输入：grid = [[1],[22],[333]]
     * 输出：[3]
     * 解释：第 0 列中，333 字符串长度为 3 。
     * 示例 2：
     * 输入：grid = [[-15,1,3],[15,7,12],[5,6,-2]]
     * 输出：[3,1,2]
     * 解释：
     * 第 0 列中，只有 -15 字符串长度为 3 。
     * 第 1 列中，所有整数的字符串长度都是 1 。
     * 第 2 列中，12 和 -2 的字符串长度都为 2 。
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m, n <= 100
     * -10^9 <= grid[r][c] <= 10^9
     */
    public int[] findColumnWidth(int[][] grid) {
        int n = grid[0].length;
        int[] ans = new int[n];
        for (int[] row : grid) {
            for (int i = 0; i < n; i++) {
                ans[i] = Math.max(ans[i], Integer.toString(row[i]).length());
            }
        }
        return ans;
    }

    /**
     * 6334. 一个数组所有前缀的分数
     * 定义一个数组 arr 的 转换数组 conver 为：
     * conver[i] = arr[i] + max(arr[0..i])，其中 max(arr[0..i]) 是满足 0 <= j <= i 的所有 arr[j] 中的最大值。
     * 定义一个数组 arr 的 分数 为 arr 转换数组中所有元素的和。
     * 给你一个下标从 0 开始长度为 n 的整数数组 nums ，请你返回一个长度为 n 的数组 ans ，其中 ans[i]是前缀 nums[0..i] 的分数。
     * 示例 1：
     * 输入：nums = [2,3,7,5,10]
     * 输出：[4,10,24,36,56]
     * 解释：
     * 对于前缀 [2] ，转换数组为 [4] ，所以分数为 4 。
     * 对于前缀 [2, 3] ，转换数组为 [4, 6] ，所以分数为 10 。
     * 对于前缀 [2, 3, 7] ，转换数组为 [4, 6, 14] ，所以分数为 24 。
     * 对于前缀 [2, 3, 7, 5] ，转换数组为 [4, 6, 14, 12] ，所以分数为 36 。
     * 对于前缀 [2, 3, 7, 5, 10] ，转换数组为 [4, 6, 14, 12, 20] ，所以分数为 56 。
     * 示例 2：
     * 输入：nums = [1,1,2,4,8,16]
     * 输出：[2,4,8,16,32,64]
     * 解释：
     * 对于前缀 [1] ，转换数组为 [2] ，所以分数为 2 。
     * 对于前缀 [1, 1]，转换数组为 [2, 2] ，所以分数为 4 。
     * 对于前缀 [1, 1, 2]，转换数组为 [2, 2, 4] ，所以分数为 8 。
     * 对于前缀 [1, 1, 2, 4]，转换数组为 [2, 2, 4, 8] ，所以分数为 16 。
     * 对于前缀 [1, 1, 2, 4, 8]，转换数组为 [2, 2, 4, 8, 16] ，所以分数为 32 。
     * 对于前缀 [1, 1, 2, 4, 8, 16]，转换数组为 [2, 2, 4, 8, 16, 32] ，所以分数为 64 。
     * 提示：
     * 1 <= nums.length <= 10^5
     * 1 <= nums[i] <= 10^9
     */
    public long[] findPrefixScore(int[] nums) {
        int n = nums.length;
        int[] conver = new int[n];
        long[] ans = new long[n];
        for (int i = 0, max = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            conver[i] = nums[i] + max;
        }
        ans[0] = conver[0];
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] + conver[i];
        }
        return ans;
    }

    /**
     * 6335. 二叉树的堂兄弟节点 II
     * 给你一棵二叉树的根 root ，请你将每个节点的值替换成该节点的所有 堂兄弟节点值的和 。
     * 如果两个节点在树中有相同的深度且它们的父节点不同，那么它们互为 堂兄弟 。
     * 请你返回修改值之后，树的根 root 。
     * 注意，一个节点的深度指的是从树根节点到这个节点经过的边数。
     * 示例 1：
     * 输入：root = [5,4,9,1,10,null,7]
     * 输出：[0,0,0,7,7,null,11]
     * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
     * - 值为 5 的节点没有堂兄弟，所以值修改为 0 。
     * - 值为 4 的节点没有堂兄弟，所以值修改为 0 。
     * - 值为 9 的节点没有堂兄弟，所以值修改为 0 。
     * - 值为 1 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
     * - 值为 10 的节点有一个堂兄弟，值为 7 ，所以值修改为 7 。
     * - 值为 7 的节点有两个堂兄弟，值分别为 1 和 10 ，所以值修改为 11 。
     * 示例 2：
     * 输入：root = [3,1,2]
     * 输出：[0,0,0]
     * 解释：上图展示了初始的二叉树和修改每个节点的值之后的二叉树。
     * - 值为 3 的节点没有堂兄弟，所以值修改为 0 。
     * - 值为 1 的节点没有堂兄弟，所以值修改为 0 。
     * - 值为 2 的节点没有堂兄弟，所以值修改为 0 。
     * 提示：
     * 树中节点数目的范围是 [1, 10^5] 。
     * 1 <= Node.val <= 10^4
     */
    public TreeNode replaceValueInTree(TreeNode root) {
        Map<TreeNode, TreeNode> childParentMap = new HashMap<>();
        List<List<TreeNode>> levelList = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            levelList.add(new ArrayList<>());
            for (int i = 0, size = queue.size(); i < size; i++) {
                TreeNode cur = queue.remove();
                levelList.get(levelList.size() - 1).add(cur);
                if (cur.left != null) {
                    childParentMap.put(cur.left, cur);
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    childParentMap.put(cur.right, cur);
                    queue.offer(cur.right);
                }
            }
        }
        for (List<TreeNode> level : levelList) {
            int sum = 0;
            for (TreeNode node : level) {sum += node.val;}
            List<Integer> valueList = new ArrayList<>(level.size());
            for (TreeNode node : level) {
                TreeNode parent = childParentMap.get(node);
                int value = sum;
                if (parent != null) {
                    if (parent.left != null) {
                        value -= parent.left.val;
                    }
                    if (parent.right != null) {
                        value -= parent.right.val;
                    }
                } else {
                    value -= node.val;
                }
                valueList.add(value);
            }
            for (int i = 0, size = level.size(); i < size; i++) {level.get(i).val = valueList.get(i);}
        }
        return root;
    }

    /**
     * 6336. 设计可以求最短路径的图类
     * 给你一个有 n 个节点的 有向带权 图，节点编号为 0 到 n - 1 。图中的初始边用数组 edges 表示，其中 edges[i] = [fromi, toi, edgeCosti] 表示从 fromi 到 toi 有一条代价为 edgeCosti 的边。
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
     * 0 <= from_i, to_i, from, to, node1, node2 <= n - 1
     * 1 <= edgeCost_i, edgeCost <= 10^6
     * 图中任何时候都不会有重边和自环。
     * 调用 addEdge 至多 100 次。
     * 调用 shortestPath 至多 100 次。
     */
    static class Graph {
        private final int n;
        private final int[][] graph;

        public Graph(int n, int[][] edges) {
            this.n = n;
            this.graph = new int[n][n];
            for (int[] edge : edges) {
                graph[edge[0]][edge[1]] = edge[2];
            }
        }

        public void addEdge(int[] edge) {
            graph[edge[0]][edge[1]] = edge[2];
        }

        public int shortestPath(int node1, int node2) {
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
            pq.offer(new int[] {node1, 0});
            int[] dist = new int[n];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[node1] = 0;
            while (!pq.isEmpty()) {
                int[] data = pq.poll();
                int cur = data[0], sum = data[1];
                if (cur == node2) {return sum;}
                if (sum > dist[cur]) {continue;}
                for (int next = 0; next < n; next++) {
                    int cost = graph[cur][next];
                    if (cost == 0) {continue;}
                    if (sum + cost < dist[next]) {
                        dist[next] = sum + cost;
                        pq.offer(new int[] {next, sum + cost});
                    }
                }
            }
            return -1;
        }
    }
}
