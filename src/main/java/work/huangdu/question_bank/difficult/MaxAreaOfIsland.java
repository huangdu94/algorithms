package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 695. 岛屿的最大面积
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 * 示例 1：
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 * 示例 2：
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 *
 * @author huangdu
 * @version 2022/5/25
 */
public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int[] directions = {0, 1, 0, -1, 0};
        int m = grid.length, n = grid[0].length, ans = 0;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = 1;
                    queue.offer(i * n + j);
                    grid[i][j] = 0;
                    while (!queue.isEmpty()) {
                        int index = queue.poll(), x = index / n, y = index % n;
                        for (int k = 0; k < 4; k++) {
                            int nextX = x + directions[k], nextY = y + directions[k + 1];
                            if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 1) {
                                grid[nextX][nextY] = 0;
                                queue.offer(nextX * n + nextY);
                                area++;
                            }
                        }
                    }
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;
    }
}
