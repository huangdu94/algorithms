package work.huangdu.question_bank.medium;

/**
 * 931. 下降路径最小和
 * 给你一个 n x n 的 方形 整数数组 matrix ，请你找出并返回通过 matrix 的下降路径 的 最小和 。
 * 下降路径 可以从第一行中的任何元素开始，并从每一行中选择一个元素。在下一行选择的元素和当前行所选元素最多相隔一列（即位于正下方或者沿对角线向左或者向右的第一个元素）。具体来说，位置 (row, col) 的下一个元素应当是 (row + 1, col - 1)、(row + 1, col) 或者 (row + 1, col + 1) 。
 * 示例 1：
 * 输入：matrix = [[2,1,3],[6,5,4],[7,8,9]]
 * 输出：13
 * 解释：如图所示，为和最小的两条下降路径
 * 示例 2：
 * 输入：matrix = [[-19,57],[-40,-5]]
 * 输出：-59
 * 解释：如图所示，为和最小的下降路径
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 100
 * -100 <= matrix[i][j] <= 100
 *
 * @author huangdu
 * @version 2023/7/13
 */
public class MinFallingPathSum {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        if (n == 1) {return matrix[0][0];}
        int[] dp = new int[n];
        System.arraycopy(matrix[n - 1], 0, dp, 0, n);
        for (int i = n - 2; i >= 0; i--) {
            int[] row = matrix[i], newDp = new int[n];
            for (int j = 0; j < n; j++) {
                int min = dp[j];
                if (j - 1 >= 0) {
                    min = Math.min(min, dp[j - 1]);
                }
                if (j + 1 < n) {
                    min = Math.min(min, dp[j + 1]);
                }
                newDp[j] = row[j] + min;
            }
            dp = newDp;
        }
        int ans = Integer.MAX_VALUE;
        for (int num : dp) {
            ans = Math.min(ans, num);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{100, -42, -46, -41}, {31, 97, 10, -10}, {-58, -51, 82, 89}, {51, 81, 69, -51}};
        MinFallingPathSum mfps = new MinFallingPathSum();
        System.out.println(mfps.minFallingPathSum(matrix));
    }
}
