package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 1034. 边界着色
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 * 示例 1：
 * 输入：grid = [[1,1],[1,2]], row = 0, col = 0, color = 3
 * 输出：[[3,3],[3,2]]
 * 示例 2：
 * 输入：grid = [[1,2,2],[2,3,2]], row = 0, col = 1, color = 3
 * 输出：[[1,3,3],[2,3,3]]
 * 示例 3：
 * 输入：grid = [[1,1,1],[1,1,1],[1,1,1]], row = 1, col = 1, color = 2
 * 输出：[[2,2,2],[2,1,2],[2,2,2]]
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * 1 <= grid[i][j], color <= 1000
 * 0 <= row < m
 * 0 <= col < n
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/12/7
 */
public class ColorBorder {
    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        final int origin = grid[row][col];
        if (origin == color) {return grid;}
        final int m = grid.length, n = grid[0].length;
        List<Integer> boards = new ArrayList<>(m * n);
        boolean[] visited = new boolean[m * n];
        Queue<Integer> queue = new ArrayDeque<>(m * n);
        visited[row * n + col] = true;
        queue.offer(row * n + col);
        int[] direction = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int index = queue.poll(), i = index / n, j = index % n;
            for (int k = 0; k < 4; k++) {
                int nextI = i + direction[k], nextJ = j + direction[k + 1];
                boolean flag = false;
                if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && grid[nextI][nextJ] == origin) {
                    int nextIndex = nextI * n + nextJ;
                    if (!visited[nextIndex]) {
                        queue.offer(nextIndex);
                        visited[nextIndex] = true;
                    }
                } else {
                    flag = true;
                }
                if (flag) {
                    boards.add(index);
                }
            }
        }
        for (int index : boards) {
            int i = index / n, j = index % n;
            grid[i][j] = color;
        }
        return grid;
    }

    public int[][] colorBorder2(int[][] grid, int row, int col, int color) {
        final int origin = grid[row][col];
        if (origin == color) {return grid;}
        final int flag = 0, m = grid.length, n = grid[0].length;
        Queue<Integer> queue = new ArrayDeque<>(m * n);
        grid[row][col] = flag;
        queue.offer(row * n + col);
        int[] direction = {-1, 0, 1, 0, -1};
        while (!queue.isEmpty()) {
            int index = queue.poll(), i = index / n, j = index % n;
            for (int k = 0; k < 4; k++) {
                int nextI = i + direction[k], nextJ = j + direction[k + 1];
                if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && grid[nextI][nextJ] == origin) {
                    queue.offer(nextI * n + nextJ);
                    grid[nextI][nextJ] = flag;
                }
            }
        }
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != flag) {
                    result[i][j] = grid[i][j];
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int nextI = i + direction[k], nextJ = j + direction[k + 1];
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n || grid[nextI][nextJ] != flag) {
                        result[i][j] = color;
                        break;
                    }
                }
                if (result[i][j] != color) {
                    result[i][j] = origin;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ColorBorder cb = new ColorBorder();
        int[][] grid = {
            {1, 2, 1, 2, 1, 2},
            {2, 2, 2, 2, 1, 2},
            {1, 2, 2, 2, 1, 2}
        };
        int row = 1, coj = 3, color = 1;
        System.out.println(Arrays.deepToString(cb.colorBorder(grid, row, coj, color)));
    }
}
