package work.huangdu.question_bank.medium;

/**
 * 1139. 最大的以 1 为边界的正方形
 * 给你一个由若干 0 和 1 组成的二维网格 grid，请你找出边界全部由 1 组成的最大 正方形 子网格，并返回该子网格中的元素数量。如果不存在，则返回 0。
 * 示例 1：
 * 输入：grid = [[1,1,1],[1,0,1],[1,1,1]]
 * 输出：9
 * 示例 2：
 * 输入：grid = [[1,1,0,0]]
 * 输出：1
 * 提示：
 * 1 <= grid.length <= 100
 * 1 <= grid[0].length <= 100
 * grid[i][j] 为 0 或 1
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2023/2/19
 */
public class Largest1BorderedSquare {
    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        // 0 上
        int[][][] dp = new int[m][n][4];
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i - 1][j] == 1) {
                    dp[i][j][0] = dp[i - 1][j][0] + 1;
                }
            }
        }
        // 1 左
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j - 1] == 1) {
                    dp[i][j][1] = dp[i][j - 1][1] + 1;
                }
            }
        }
        // 2 下
        for (int i = m - 2; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (grid[i + 1][j] == 1) {
                    dp[i][j][2] = dp[i + 1][j][2] + 1;
                }
            }
        }
        // 3 右
        for (int j = n - 2; j >= 0; j--) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j + 1] == 1) {
                    dp[i][j][3] = dp[i][j + 1][3] + 1;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int up = dp[i][j][0], left = dp[i][j][1], down = dp[i][j][2], right = dp[i][j][3];
                    // 上 左
                    for (int k = Math.min(up, left); k + 1 > ans; k--) {
                        if (i - k >= 0 && j - k >= 0 && grid[i - k][j - k] == 1 && dp[i - k][j - k][2] >= k && dp[i - k][j - k][3] >= k) {
                            ans = k + 1;
                        }
                    }
                    // 左 下
                    for (int k = Math.min(left, down); k + 1 > ans; k--) {
                        if (i + k < m && j - k >= 0 && grid[i + k][j - k] == 1 && dp[i + k][j - k][0] >= k && dp[i + k][j - k][3] >= k) {
                            ans = k + 1;
                        }
                    }
                    // 下 右
                    for (int k = Math.min(down, right); k + 1 > ans; k--) {
                        if (i + k < m && j + k < n && grid[i + k][j + k] == 1 && dp[i + k][j + k][0] >= k && dp[i + k][j + k][1] >= k) {
                            ans = k + 1;
                        }
                    }
                    // 右 上
                    for (int k = Math.min(right, up); k + 1 > ans; k--) {
                        if (i - k >= 0 && j + k < n && grid[i - k][j + k] == 1 && dp[i - k][j + k][1] >= k && dp[i - k][j + k][2] >= k) {
                            ans = k + 1;
                        }
                    }
                }
            }
        }
        return ans * ans;
    }

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1},
                {1, 1}
        };
        Largest1BorderedSquare lbs = new Largest1BorderedSquare();
        System.out.println(lbs.largest1BorderedSquare(grid));
    }
}
