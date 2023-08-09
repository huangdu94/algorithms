package work.huangdu.question_bank.difficult;

/**
 * 980. 不同路径 III
 * 在二维网格 grid 上，有 4 种类型的方格：
 * 1 表示起始方格。且只有一个起始方格。
 * 2 表示结束方格，且只有一个结束方格。
 * 0 表示我们可以走过的空方格。
 * -1 表示我们无法跨越的障碍。
 * 返回在四个方向（上、下、左、右）上行走时，从起始方格到结束方格的不同路径的数目。
 * 每一个无障碍方格都要通过一次，但是一条路径中不能重复通过同一个方格。
 * 示例 1：
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
 * 输出：2
 * 解释：我们有以下两条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
 * 2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
 * 示例 2：
 * 输入：[[1,0,0,0],[0,0,0,0],[0,0,0,2]]
 * 输出：4
 * 解释：我们有以下四条路径：
 * 1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
 * 2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
 * 3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
 * 4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
 * 示例 3：
 * 输入：[[0,1],[2,0]]
 * 输出：0
 * 解释：
 * 没有一条路能完全穿过每一个空的方格一次。
 * 请注意，起始和结束方格可以位于网格中的任意位置。
 * 提示：
 * 1 <= grid.length * grid[0].length <= 20
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/8/9
 */
public class UniquePathsIII {
    private static final int START = 1;
    private static final int END = 2;
    private static final int OBSTACLE = -1;
    private static final int[] direction = {0, 1, 0, -1, 0};
    private int m;
    private int n;
    private boolean[][] visited;
    private int[][] grid;
    private int all;

    public int uniquePathsIII(int[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.visited = new boolean[m][n];
        this.grid = grid;
        this.all = m * n;
        int startI = 0, startJ = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == START) {
                    startI = i;
                    startJ = j;
                } else if (grid[i][j] == OBSTACLE) {
                    all--;
                }
            }
        }
        return dfs(startI, startJ, 1);
    }

    private int dfs(int i, int j, int count) {
        if (grid[i][j] == END) {return count == all ? 1 : 0;}
        visited[i][j] = true;
        int ans = 0;
        for (int k = 0; k < 4; k++) {
            int nextI = i + direction[k], nextJ = j + direction[k + 1];
            if (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && grid[nextI][nextJ] != OBSTACLE && !visited[nextI][nextJ]) {
                ans += dfs(nextI, nextJ, count + 1);
            }
        }
        visited[i][j] = false;
        return ans;
    }
}
