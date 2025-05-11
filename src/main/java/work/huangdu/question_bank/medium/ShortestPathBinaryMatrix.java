package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1091. 二进制矩阵中的最短路径
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 * 示例 1：
 * 输入：grid = [[0,1],[1,0]]
 * 输出：2
 * 示例 2：
 * 输入：grid = [[0,0,0],[1,1,0],[1,1,0]]
 * 输出：4
 * 示例 3：
 * 输入：grid = [[1,0,0],[1,1,0],[1,1,0]]
 * 输出：-1
 * 提示：
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 100
 * grid[i][j] 为 0 或 1
 *
 * @author huangdu
 * @version 2023/5/26
 */
public class ShortestPathBinaryMatrix {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {return -1;}
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n * n];
        queue.offer(0);
        visited[0] = true;
        int ans = 1;
        while (!queue.isEmpty()) {
            for (int k = 0, size = queue.size(); k < size; k++) {
                int data = queue.remove(), x = data / n, y = data % n;
                if (x == n - 1 && y == n - 1) {return ans;}
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0 || x + i < 0 || x + i >= n || y + j < 0 || y + j >= n || grid[x + i][y + j] != 0) {continue;}
                        int hash = (x + i) * n + y + j;
                        if (!visited[hash]) {
                            visited[hash] = true;
                            queue.offer(hash);
                        }
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    public static void main(String[] args) {
        ShortestPathBinaryMatrix spbm = new ShortestPathBinaryMatrix();
        int[][] grid = {{0, 1}, {1, 0}};
        System.out.println(spbm.shortestPathBinaryMatrix(grid));
    }
}
