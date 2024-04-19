package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2352. 相等行列对
 * 给你一个下标从 0 开始、大小为 n x n 的整数矩阵 grid ，返回满足 Ri 行和 Cj 列相等的行列对 (Ri, Cj) 的数目。
 * 如果行和列以相同的顺序包含相同的元素（即相等的数组），则认为二者是相等的。
 * 示例 1：
 * 输入：grid = [[3,2,1],[1,7,6],[2,7,7]]
 * 输出：1
 * 解释：存在一对相等行列对：
 * - (第 2 行，第 1 列)：[2,7,7]
 * 示例 2：
 * 输入：grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
 * 输出：3
 * 解释：存在三对相等行列对：
 * - (第 0 行，第 0 列)：[3,1,2,2]
 * - (第 2 行, 第 2 列)：[2,4,2,2]
 * - (第 3 行, 第 2 列)：[2,4,2,2]
 * 提示：
 * n == grid.length == grid[i].length
 * 1 <= n <= 200
 * 1 <= grid[i][j] <= 10^5
 *
 * @author huangdu
 * @version 2023/6/6
 */
public class EqualPairs {
    public int equalPairs(int[][] grid) {
        int n = grid.length, ans = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int[] row : grid) {
            int hash = 1;
            for (int j = 0; j < n; j++) {
                hash = 37 * hash + row[j];
            }
            freq.merge(hash, 1, Integer::sum);
        }
        for (int j = 0; j < n; j++) {
            int hash = 1;
            for (int[] row : grid) {
                hash = 37 * hash + row[j];
            }
            ans += freq.getOrDefault(hash, 0);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid =
            {
                {2, 1},
                {3, 32}
            };
        EqualPairs ep = new EqualPairs();
        System.out.println(ep.equalPairs(grid));
    }
}
