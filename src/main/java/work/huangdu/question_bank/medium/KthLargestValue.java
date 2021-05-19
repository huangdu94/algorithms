package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1738. 找出第 K 大的异或坐标值
 * 给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。
 * 矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。
 * 请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。
 * 示例 1：
 * 输入：matrix = [[5,2],[1,6]], k = 1
 * 输出：7
 * 解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
 * 示例 2：
 * 输入：matrix = [[5,2],[1,6]], k = 2
 * 输出：5
 * 解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
 * 示例 3：
 * 输入：matrix = [[5,2],[1,6]], k = 3
 * 输出：4
 * 解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
 * 示例 4：
 * 输入：matrix = [[5,2],[1,6]], k = 4
 * 输出：0
 * 解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 1000
 * 0 <= matrix[i][j] <= 10^6
 * 1 <= k <= m * n
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/19
 */
public class KthLargestValue {
    public int kthLargestValue(int[][] matrix, int k) {
        int n = matrix[0].length, rowXor;
        int[] xorPrefix = new int[n];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int[] row : matrix) {
            rowXor = 0;
            for (int j = 0; j < n; j++) {
                rowXor ^= row[j];
                xorPrefix[j] ^= rowXor;
                priorityQueue.add(xorPrefix[j]);
                if (priorityQueue.size() > k) {
                    priorityQueue.remove();
                }
            }
        }
        return priorityQueue.remove();
    }

    public int kthLargestValue2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, rowXor = 0;
        int[][] xorPrefix = new int[m][n];
        for (int j = 0; j < n; j++) {
            rowXor ^= matrix[0][j];
            xorPrefix[0][j] = rowXor;
        }
        for (int i = 1; i < m; i++) {
            rowXor = 0;
            for (int j = 0; j < n; j++) {
                rowXor ^= matrix[i][j];
                xorPrefix[i][j] = rowXor ^ xorPrefix[i - 1][j];
            }
        }
        List<Integer> xorList = new ArrayList<>(m * n);
        for (int[] row : xorPrefix) {
            for (int element : row) {
                xorList.add(element);
            }
        }
        xorList.sort(Integer::compare);
        return xorList.get(m * n - k);
    }

    public static void main(String[] args) {
        int[][] matrix = {{5, 2}, {1, 6}};
        int k = 1;
        KthLargestValue kthLargestValue = new KthLargestValue();
        System.out.println(kthLargestValue.kthLargestValue(matrix, k));
    }
}
