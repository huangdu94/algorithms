package work.huangdu.question_bank.difficult;

import java.util.TreeSet;

/**
 * 363. 矩形区域不超过 K 的最大数值和
 * 给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
 * 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
 * 示例 1：
 * 输入：matrix = [[1,0,1],[0,-2,3]], k = 2
 * 输出：2
 * 解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
 * 示例 2：
 * 输入：matrix = [[2,2,-1]], k = 3
 * 输出：3
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -100 <= matrix[i][j] <= 100
 * -10^5 <= k <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/4/22
 */
public class MaxSumSubmatrix {
    private int[][] matrixSum;

    public void numMatrix(int[][] matrix, int m, int n) {
        matrixSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                matrixSum[i + 1][j + 1] = matrixSum[i][j + 1] + (rowSum += matrix[i][j]);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return matrixSum[row2 + 1][col2 + 1] - matrixSum[row1][col2 + 1] - matrixSum[row2 + 1][col1] + matrixSum[row1][col1];
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, result = Integer.MIN_VALUE;
        numMatrix(matrix, m, n);
        for (int i1 = 0; i1 < m; i1++) {
            for (int j1 = 0; j1 < n; j1++) {
                for (int i2 = i1; i2 < m; i2++) {
                    for (int j2 = j1; j2 < n; j2++) {
                        int sum = sumRegion(i1, j1, i2, j2);
                        if (sum == k) {
                            return k;
                        }
                        if (sum < k && result < sum) {
                            result = sum;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MaxSumSubmatrix mss = new MaxSumSubmatrix();
        int[][] matrix = {{2, 2, -1}};
        int k = 0;
        System.out.println(mss.maxSumSubmatrix(matrix, k));
    }

    public int maxSumSubmatrix2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, result = Integer.MIN_VALUE;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] nums = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    nums[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<>();
                sumSet.add(0);
                int sum = 0;
                for (int v : nums) {
                    sum += v;
                    Integer ceil = sumSet.ceiling(sum - k);
                    if (ceil != null) {
                        result = Math.max(result, sum - ceil);
                    }
                    sumSet.add(sum);
                }
            }
        }
        return result;
    }
}
