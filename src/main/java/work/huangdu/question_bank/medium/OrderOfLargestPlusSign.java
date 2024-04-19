package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 764. 最大加号标志
 * 在一个 n x n 的矩阵 grid 中，除了在数组 mines 中给出的元素为 0，其他每个元素都为 1。mines[i] = [xi, yi]表示 grid[xi][yi] == 0
 * 返回  grid 中包含 1 的最大的 轴对齐 加号标志的阶数 。如果未找到加号标志，则返回 0 。
 * 一个 k 阶由 1 组成的 “轴对称”加号标志 具有中心网格 grid[r][c] == 1 ，以及4个从中心向上、向下、向左、向右延伸，长度为 k-1，由 1 组成的臂。注意，只有加号标志的所有网格要求为 1 ，别的网格可能为 0 也可能为 1 。
 * 示例 1：
 * 输入: n = 5, mines = [[4, 2]]
 * 输出: 2
 * 解释: 在上面的网格中，最大加号标志的阶只能是2。一个标志已在图中标出。
 * 示例 2：
 * 输入: n = 1, mines = [[0, 0]]
 * 输出: 0
 * 解释: 没有加号标志，返回 0 。
 * 提示：
 * 1 <= n <= 500
 * 1 <= mines.length <= 5000
 * 0 <= xi, yi < n
 * 每一对 (xi, yi) 都 不重复
 *
 * @author huangdu
 * @version 2022/11/10
 */
public class OrderOfLargestPlusSign {
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        Map<Integer, TreeSet<Integer>> xyMap = new HashMap<>(n), yxMap = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            xyMap.put(i, new TreeSet<>());
            yxMap.put(i, new TreeSet<>());
        }
        for (int[] mine : mines) {
            xyMap.get(mine[0]).add(mine[1]);
            yxMap.get(mine[1]).add(mine[0]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (xyMap.get(i).contains(j)) {continue;}
                TreeSet<Integer> ySet = xyMap.get(i);
                Integer yLeft = ySet.floor(j), yRight = ySet.ceiling(j);
                if (yLeft == null) {yLeft = -1;}
                if (yRight == null) {yRight = n;}
                int size = Math.min(j - yLeft, yRight - j);
                TreeSet<Integer> xSet = yxMap.get(j);
                Integer xLeft = xSet.floor(i), xRight = xSet.ceiling(i);
                if (xLeft == null) {xLeft = -1;}
                if (xRight == null) {xRight = n;}
                size = Math.min(size, Math.min(i - xLeft, xRight - i));
                ans = Math.max(ans, size);
            }
        }
        return ans;
    }
}
