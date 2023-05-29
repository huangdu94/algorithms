package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * 1439. 有序矩阵中的第 k 个最小数组和
 * 给你一个 m * n 的矩阵 mat，以及一个整数 k ，矩阵中的每一行都以非递减的顺序排列。
 * 你可以从每一行中选出 1 个元素形成一个数组。返回所有可能数组中的第 k 个 最小 数组和。
 * 示例 1：
 * 输入：mat = [[1,3,11],[2,4,6]], k = 5
 * 输出：7
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,2], [1,4], [3,2], [3,4], [1,6]。其中第 5 个的和是 7 。
 * 示例 2：
 * 输入：mat = [[1,3,11],[2,4,6]], k = 9
 * 输出：17
 * 示例 3：
 * 输入：mat = [[1,10,10],[1,4,5],[2,3,6]], k = 7
 * 输出：9
 * 解释：从每一行中选出一个元素，前 k 个和最小的数组分别是：
 * [1,1,2], [1,1,3], [1,4,2], [1,4,3], [1,1,6], [1,5,2], [1,5,3]。其中第 7 个的和是 9 。
 * 示例 4：
 * 输入：mat = [[1,1,10],[2,2,9]], k = 7
 * 输出：12
 * 提示：
 * m == mat.length
 * n == mat.length[i]
 * 1 <= m, n <= 40
 * 1 <= k <= min(200, n ^ m)
 * 1 <= mat[i][j] <= 5000
 * mat[i] 是一个非递减数组
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/5/28
 */
public class KthSmallest {
    public int kthSmallest(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length, size = Math.min(k, n);
        List<Integer> selected = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            selected.add(mat[0][i]);
        }
        for (int i = 1; i < m; i++) {
            int[] row = mat[i];
            List<Integer> next = new ArrayList<>(selected.size() * n);
            for (int num1 : selected) {
                for (int num2 : row) {
                    next.add(num1 + num2);
                }
            }
            next.sort(Integer::compare);
            selected = next.subList(0, Math.min(k, next.size()));
        }
        return selected.get(k - 1);
    }
}
