package work.huangdu.question_bank.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * 1260. 二维网格迁移
 * 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * 每次「迁移」操作将会引发下述活动：
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 * 示例 1：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 * 输出：[[9,1,2],[3,4,5],[6,7,8]]
 * 示例 2：
 * 输入：grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
 * 输出：[[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
 * 示例 3：
 * 输入：grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
 * 输出：[[1,2,3],[4,5,6],[7,8,9]]
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 50
 * 1 <= n <= 50
 * -1000 <= grid[i][j] <= 1000
 * 0 <= k <= 100
 *
 * @author huangdu
 * @version 2022/7/20
 */
public class ShiftGrid {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {ans.add(new ArrayList<>(n));}
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans.get(i).add(grid[i][j]);
            }
        }
        for (int i = 0; i < k; i++) {
            shift(ans, m, n);
        }
        return ans;
    }

    private void shift(List<List<Integer>> gird, int m, int n) {
        List<Integer> temp = new ArrayList<>(n);
        for (int i = 0; i < m; i++) {temp.add(gird.get(i).get(n - 1));}
        for (int i = 0; i < m; i++) {
            for (int j = n - 2; j >= 0; j--) {
                gird.get(i).set(j + 1, gird.get(i).get(j));
            }
        }
        for (int i = 0; i < m - 1; i++) {
            gird.get(i + 1).set(0, temp.get(i));
        }
        gird.get(0).set(0, temp.get(m - 1));
    }
}
