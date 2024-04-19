package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 934. 最短的桥
 * 给你一个大小为 n x n 的二元矩阵 grid ，其中 1 表示陆地，0 表示水域。
 * 岛 是由四面相连的 1 形成的一个最大组，即不会与非组内的任何其他 1 相连。grid 中 恰好存在两座岛 。
 * 你可以将任意数量的 0 变为 1 ，以使两座岛连接起来，变成 一座岛 。
 * 返回必须翻转的 0 的最小数目。
 * 示例 1：
 * 输入：grid = [[0,1],[1,0]]
 * 输出：1
 * 示例 2：
 * 输入：grid = [[0,1,0],[0,0,0],[0,0,1]]
 * 输出：2
 * 示例 3：
 * 输入：grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * 输出：1
 * 提示：
 * n == grid.length == grid[i].length
 * 2 <= n <= 100
 * grid[i][j] 为 0 或 1
 * grid 中恰有两个岛
 *
 * @author huangdu
 * @version 2022/10/25
 */
public class ShortestBridge {
    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean[][] mark = new boolean[n][n];
        boolean[][] visited = new boolean[n][n];
        int[] directions = {-1, 0, 1, 0, -1};
        Queue<Integer> idxQueue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {continue;}
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i * n + j);
                mark[i][j] = true;
                while (!queue.isEmpty()) {
                    int hash = queue.poll(), curI = hash / n, curJ = hash % n;
                    idxQueue.offer(hash);
                    visited[curI][curJ] = true;
                    for (int k = 0; k < 4; k++) {
                        int nextI = curI + directions[k], nextJ = curJ + directions[k + 1];
                        if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n && grid[nextI][nextJ] == 1 && !mark[nextI][nextJ]) {
                            mark[nextI][nextJ] = true;
                            queue.offer(nextI * n + nextJ);
                        }
                    }
                }
                flag = true;
                break;
            }
            if (flag) {break;}
        }
        int ans = 0;
        while (!idxQueue.isEmpty()) {
            int size = idxQueue.size();
            for (int i = 0; i < size; i++) {
                int hash = idxQueue.poll(), curI = hash / n, curJ = hash % n;
                for (int k = 0; k < 4; k++) {
                    int nextI = curI + directions[k], nextJ = curJ + directions[k + 1];
                    if (nextI >= 0 && nextI < n && nextJ >= 0 && nextJ < n && !mark[nextI][nextJ] && !visited[nextI][nextJ]) {
                        if (grid[nextI][nextJ] == 1) {return ans;}
                        visited[nextI][nextJ] = true;
                        idxQueue.offer(nextI * n + nextJ);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestBridge sb = new ShortestBridge();
        int[][] grid = {{0, 1}, {1, 0}};
        System.out.println(sb.shortestBridge(grid));
    }
}