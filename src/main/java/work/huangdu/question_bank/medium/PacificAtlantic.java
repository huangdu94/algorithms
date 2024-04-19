package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/**
 * 417. 太平洋大西洋水流问题
 * 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 * 示例 1：
 * 输入: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 * 输出: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 示例 2：
 * 输入: heights = [[2,1],[1,2]]
 * 输出: [[0,0],[0,1],[1,0],[1,1]]
 * 提示：
 * m == heights.length
 * n == heights[r].length
 * 1 <= m, n <= 200
 * 0 <= heights[r][c] <= 10^5
 *
 * @author huangdu
 * @version 2022/4/27
 */
public class PacificAtlantic {
    private static final byte PACIFIC = 0X01;
    private static final byte ATLANTIC = 0X10;
    private static final byte ALL = 0X11;
    private static final int[] DIRECTION = {1, 0, -1, 0, 1};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        int m = heights.length, n = heights[0].length;
        byte[][] visited = new byte[m][n];
        for (int i = 0; i < m; i++) {
            bfs(heights, i, 0, m, n, visited, PACIFIC, ans);
        }
        for (int j = 1; j < n; j++) {
            bfs(heights, 0, j, m, n, visited, PACIFIC, ans);
        }
        for (int i = 0; i < m; i++) {
            bfs(heights, i, n - 1, m, n, visited, ATLANTIC, ans);
        }
        for (int j = 0; j < n - 1; j++) {
            bfs(heights, m - 1, j, m, n, visited, ATLANTIC, ans);
        }
        return ans;
    }

    private void bfs(int[][] heights, int i, int j, int m, int n, byte[][] visited, byte mask, List<List<Integer>> ans) {
        if ((visited[i][j] & mask) != 0) {return;}
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {i, j});
        if ((visited[i][j] |= mask) == ALL) {ans.add(Arrays.asList(i, j));}
        while (!queue.isEmpty()) {
            i = queue.peek()[0];
            j = queue.poll()[1];
            int curHeight = heights[i][j];
            for (int k = 0; k < 4; k++) {
                int nextI = i + DIRECTION[k], nextJ = j + DIRECTION[k + 1];
                if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && heights[nextI][nextJ] >= curHeight && (visited[nextI][nextJ] & mask) == 0) {
                    queue.offer(new int[] {nextI, nextJ});
                    if ((visited[nextI][nextJ] |= mask) == ALL) {ans.add(Arrays.asList(nextI, nextJ));}
                }
            }
        }
    }

    public static void main(String[] args) {
        PacificAtlantic pa = new PacificAtlantic();
        int[][] heights = {{2, 1}, {1, 2}};
        System.out.println(pa.pacificAtlantic(heights));
    }
}
