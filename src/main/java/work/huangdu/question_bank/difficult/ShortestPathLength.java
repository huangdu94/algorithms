package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 847. 访问所有节点的最短路径
 * 存在一个由 n 个节点组成的无向连通图，图中的节点按从 0 到 n - 1 编号。
 * 给你一个数组 graph 表示这个图。其中，graph[i] 是一个列表，由所有与节点 i 直接相连的节点组成。
 * 返回能够访问所有节点的最短路径的长度。你可以在任一节点开始和停止，也可以多次重访节点，并且可以重用边。
 * 示例 1：
 * 输入：graph = [[1,2,3],[0],[0],[0]]
 * 输出：4
 * 解释：一种可能的路径为 [1,0,2,0,3]
 * 示例 2：
 * 输入：graph = [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * 输出：4
 * 解释：一种可能的路径为 [0,1,4,2,3]
 * 提示：
 * n == graph.length
 * 1 <= n <= 12
 * 0 <= graph[i].length < n
 * graph[i] 不包含 i
 * 如果 graph[a] 包含 b ，那么 graph[b] 也包含 a
 * 输入的图总是连通图
 *
 * @author huangdu
 * @version 2021/8/6
 */
public class ShortestPathLength {

    public int shortestPathLength(int[][] graph) {
        if (graph.length == 1) {return 0;}
        int n = graph.length, complete = (1 << n) - 1;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] exist = new boolean[n][1 << n];
        for (int i = 0; i < n; i++) {
            queue.offer(new int[] {i, 1 << i, 0});
            exist[i][1 << i] = true;
        }
        while (true) {
            int[] nodeInfo = queue.remove();
            int node = nodeInfo[0], visited = nodeInfo[1], length = nodeInfo[2] + 1;
            for (int next : graph[node]) {
                int nextVisited = visited | (1 << next);
                if (exist[next][nextVisited]) {continue;}
                if (nextVisited == complete) {return length;}
                queue.offer(new int[] {next, nextVisited, length});
                exist[next][nextVisited] = true;
            }
        }
    }

    public static void main(String[] args) {
        ShortestPathLength spl = new ShortestPathLength();
        int[][] graph = {{1, 2, 3}, {0}, {0}, {0}};
        System.out.println(spl.shortestPathLength(graph));
    }
}
