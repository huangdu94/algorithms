package work.huangdu.question_bank.medium;

import work.huangdu.specific.union_find_set.FinalUnionFindSet;

/**
 * 1020. 飞地的数量
 * 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 * 示例 1：
 * 输入：grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
 * 输出：3
 * 解释：有三个 1 被 0 包围。一个 1 没有被包围，因为它在边界上。
 * 示例 2：
 * 输入：grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
 * 输出：0
 * 解释：所有 1 都在边界上或可以到达边界。
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 500
 * grid[i][j] 的值为 0 或 1
 *
 * @author huangdu
 * @version 2022/2/12
 */
public class NumEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        final int limit = m * n;
        FinalUnionFindSet fufs = new FinalUnionFindSet(limit + 1);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int curIndex = i * n + j;
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        fufs.union(curIndex, limit);
                    }
                    if (j > 0 && grid[i][j - 1] == 1) {
                        fufs.union(curIndex, i * n + j - 1);
                    }
                    if (i > 0 && grid[i - 1][j] == 1) {
                        fufs.union(curIndex, (i - 1) * n + j);
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (fufs.find(i * n + j) != fufs.find(limit)) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{0}, {1}, {1}, {0}, {0}};
        NumEnclaves ne = new NumEnclaves();
        int result = ne.numEnclaves(grid);
        System.out.println(result);
    }
}
