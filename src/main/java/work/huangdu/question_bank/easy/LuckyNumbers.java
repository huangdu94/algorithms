package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1380. 矩阵中的幸运数
 * 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 * 示例 1：
 * 输入：matrix = [[3,7,8],[9,11,13],[15,16,17]]
 * 输出：[15]
 * 解释：15 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 2：
 * 输入：matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
 * 输出：[12]
 * 解释：12 是唯一的幸运数，因为它是其所在行中的最小值，也是所在列中的最大值。
 * 示例 3：
 * 输入：matrix = [[7,8],[1,2]]
 * 输出：[7]
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= n, m <= 50
 * 1 <= matrix[i][j] <= 10^5
 * 矩阵中的所有元素都是不同的
 *
 * @author huangdu
 * @version 2022/2/15
 */
public class LuckyNumbers {
    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> rowMinList = new ArrayList<>(m), cojMaxList = new ArrayList<>(n);
        for (int[] row : matrix) {
            rowMinList.add(Arrays.stream(row).min().getAsInt());
        }
        for (int j = 0; j < n; j++) {
            int max = matrix[0][j];
            for (int k = 1; k < m; k++) {
                max = Math.max(max, matrix[k][j]);
            }
            cojMaxList.add(max);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int element = matrix[i][j];
                if (element == rowMinList.get(i) && element == cojMaxList.get(j)) {
                    ans.add(element);
                }
            }
        }
        return ans;
    }
}
