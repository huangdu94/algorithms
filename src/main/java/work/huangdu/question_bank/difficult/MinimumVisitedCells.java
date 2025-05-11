package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2617. 网格图中最少访问的格子数
 * 给你一个下标从 0 开始的 m x n 整数矩阵 grid 。你一开始的位置在 左上角 格子 (0, 0) 。
 * 当你在格子 (i, j) 的时候，你可以移动到以下格子之一：
 * 满足 j < k <= grid[i][j] + j 的格子 (i, k) （向右移动），或者
 * 满足 i < k <= grid[i][j] + i 的格子 (k, j) （向下移动）。
 * 请你返回到达 右下角 格子 (m - 1, n - 1) 需要经过的最少移动格子数，如果无法到达右下角格子，请你返回 -1 。
 * 示例 1：
 * 输入：grid = [[3,4,2,1],[4,2,3,1],[2,1,0,0],[2,4,0,0]]
 * 输出：4
 * 解释：上图展示了到达右下角格子经过的 4 个格子。
 * 示例 2：
 * 输入：grid = [[3,4,2,1],[4,2,1,1],[2,1,1,0],[3,4,1,0]]
 * 输出：3
 * 解释：上图展示了到达右下角格子经过的 3 个格子。
 * 示例 3：
 * 输入：grid = [[2,1,0],[1,0,0]]
 * 输出：-1
 * 解释：无法到达右下角格子。
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 10^5
 * 0 <= grid[i][j] < m * n
 * grid[m - 1][n - 1] == 0
 *
 * @author huangdu
 */
public class MinimumVisitedCells {
    public int minimumVisitedCells(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<int[]>[] rows = new PriorityQueue[m], cols = new PriorityQueue[n];
        Arrays.setAll(rows, (e) -> new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[1])));
        Arrays.setAll(cols, (e) -> new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[1])));
        int[][] dp = new int[m][n];
        for (int[] r : dp) {
            Arrays.fill(r, -1);
        }
        dp[0][0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                PriorityQueue<int[]> row = rows[i], col = cols[j];
                while (!row.isEmpty() && row.peek()[0] + grid[i][row.peek()[0]] < j) {row.poll();}
                if (!row.isEmpty()) {
                    dp[i][j] = row.peek()[1] + 1;
                }
                while (!col.isEmpty() && col.peek()[0] + grid[col.peek()[0]][j] < i) {col.poll();}
                if (!col.isEmpty()) {
                    dp[i][j] = dp[i][j] == -1 ? col.peek()[1] + 1 : Math.min(dp[i][j], col.peek()[1] + 1);
                }
                if (dp[i][j] != -1) {
                    row.offer(new int[] {j, dp[i][j]});
                    col.offer(new int[] {i, dp[i][j]});
                }
            }
        }
        return dp[m - 1][n - 1];
    }
}
