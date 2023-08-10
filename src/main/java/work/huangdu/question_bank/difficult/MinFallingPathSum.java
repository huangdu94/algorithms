package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1289. 下降路径最小和 II
 * 给你一个 n x n 整数矩阵 grid ，请你返回 非零偏移下降路径 数字和的最小值。
 * 非零偏移下降路径 定义为：从 grid 数组中的每一行选择一个数字，且按顺序选出来的数字中，相邻数字不在原数组的同一列。
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：13
 * 解释：
 * 所有非零偏移下降路径包括：
 * [1,5,9], [1,5,7], [1,6,7], [1,6,8],
 * [2,4,8], [2,4,9], [2,6,7], [2,6,8],
 * [3,4,8], [3,4,9], [3,5,7], [3,5,9]
 * 下降路径中数字和最小的是 [1,5,7] ，所以答案是 13 。
 * 示例 2：
 * 输入：grid = [[7]]
 * 输出：7
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * -99 <= grid[i][j] <= 99
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/8/10
 */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 1) {return grid[0][0];}
        int[] dp = Arrays.copyOf(grid[0], n);
        for (int k = 1; k < n; k++) {
            int[] newDp = new int[n];
            for (int i = 0; i < n; i++) {
                int min = Integer.MAX_VALUE;
                for (int j = 0; j < n; j++) {
                    if (i != j) {min = Math.min(min, dp[j] + grid[k][i]);}
                }
                newDp[i] = min;
            }
            dp = newDp;
        }
        int ans = Integer.MAX_VALUE;
        for (int num : dp) {ans = Math.min(ans, num);}
        return ans;
    }

    public int minFallingPathSum2(int[][] grid) {
        int n = grid.length;
        if (n == 1) {return grid[0][0];}
        int[][] minIdxs = new int[n][3];
        for (int i = 0; i < n; i++) {
            Integer[] idxMap = new Integer[n];
            for (int k = 0; k < n; k++) {idxMap[k] = k;}
            int[] row = grid[i];
            Arrays.sort(idxMap, Comparator.comparingInt(i2 -> row[i2]));
            minIdxs[i][0] = idxMap[0];
            minIdxs[i][1] = idxMap[1];
            minIdxs[i][2] = idxMap[2];
        }
        int[] dp = {grid[0][minIdxs[0][0]], grid[0][minIdxs[0][1]], grid[0][minIdxs[0][2]]};
        for (int i = 1; i < n; i++) {
            int[] newDp = {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE};
            for (int j = 0; j < 3; j++) {
                int cur = minIdxs[i][j];
                for (int k = 0; k < 3; k++) {
                    int pre = minIdxs[i - 1][k];
                    if (pre != cur) {
                        newDp[j] = Math.min(newDp[j], dp[k] + grid[i][cur]);
                    }
                }
            }
            dp = newDp;
        }
        return Math.min(dp[0], Math.min(dp[1], dp[2]));
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 99, 99}, {0, 2, 1}, {99, 99, 4}};
        MinFallingPathSum mfps = new MinFallingPathSum();
        System.out.println(mfps.minFallingPathSum(grid));
    }
}
