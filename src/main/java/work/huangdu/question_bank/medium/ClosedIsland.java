package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1254. 统计封闭岛屿的数目
 * 二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。
 * 请返回 封闭岛屿 的数目。
 * 示例 1：
 * 输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * 输出：2
 * 解释：
 * 灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。
 * 示例 2：
 * 输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * 输出：1
 * 示例 3：
 * 输入：grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * 输出：2
 * 提示：
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 *
 * @author huangdu
 * @version 2023/6/18
 */
public class ClosedIsland {
    public int closedIsland(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new ArrayDeque<>();
        int[] directions = {0, 1, 0, -1, 0};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] || grid[i][j] == 1) {continue;}
                boolean flag = true;
                queue.offer(new int[] {i, j});
                visited[i][j] = true;
                while (!queue.isEmpty()) {
                    int[] data = queue.poll();
                    int x = data[0], y = data[1];
                    if (x == 0 || x == m - 1 || y == 0 || y == n - 1) {flag = false;}
                    for (int k = 0; k < 4; k++) {
                        int nextX = x + directions[k], nextY = y + directions[k + 1];
                        if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                            visited[nextX][nextY] = true;
                            queue.offer(new int[] {nextX, nextY});
                        }
                    }
                }
                if (flag) {ans++;}
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ClosedIsland ci = new ClosedIsland();
        int[][] gird = {{1, 1, 1, 1, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 1, 0}, {1, 0, 1, 0, 1, 1, 1, 0}, {1, 0, 0, 0, 0, 1, 0, 1}, {1, 1, 1, 1, 1, 1, 1, 0}};
        System.out.println(ci.closedIsland(gird));
    }
}
