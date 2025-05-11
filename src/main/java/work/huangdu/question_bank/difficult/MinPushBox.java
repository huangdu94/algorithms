package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 1263. 推箱子
 * 「推箱子」是一款风靡全球的益智小游戏，玩家需要将箱子推到仓库中的目标位置。
 * 游戏地图用大小为 m x n 的网格 grid 表示，其中每个元素可以是墙、地板或者是箱子。
 * 现在你将作为玩家参与游戏，按规则将箱子 'B' 移动到目标位置 'T' ：
 * 玩家用字符 'S' 表示，只要他在地板上，就可以在网格中向上、下、左、右四个方向移动。
 * 地板用字符 '.' 表示，意味着可以自由行走。
 * 墙用字符 '#' 表示，意味着障碍物，不能通行。
 * 箱子仅有一个，用字符 'B' 表示。相应地，网格上有一个目标位置 'T'。
 * 玩家需要站在箱子旁边，然后沿着箱子的方向进行移动，此时箱子会被移动到相邻的地板单元格。记作一次「推动」。
 * 玩家无法越过箱子。
 * 返回将箱子推到目标位置的最小 推动 次数，如果无法做到，请返回 -1。
 * 示例 1：
 * 输入：grid = [["#","#","#","#","#","#"],
 * *              ["#","T","#","#","#","#"],
 * *              ["#",".",".","B",".","#"],
 * *             ["#",".","#","#",".","#"],
 * *              ["#",".",".",".","S","#"],
 * *              ["#","#","#","#","#","#"]]
 * 输出：3
 * 解释：我们只需要返回推箱子的次数。
 * 示例 2：
 * 输入：grid = [["#","#","#","#","#","#"],
 * *              ["#","T","#","#","#","#"],
 * *              ["#",".",".","B",".","#"],
 * *              ["#","#","#","#",".","#"],
 * *              ["#",".",".",".","S","#"],
 * *              ["#","#","#","#","#","#"]]
 * 输出：-1
 * 示例 3：
 * 输入：grid = [["#","#","#","#","#","#"],
 * *              ["#","T",".",".","#","#"],
 * *              ["#",".","#","B",".","#"],
 * *              ["#",".",".",".",".","#"],
 * *              ["#",".",".",".","S","#"],
 * *              ["#","#","#","#","#","#"]]
 * 输出：5
 * 解释：向下、向左、向左、向上再向上。
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 20
 * grid 仅包含字符 '.', '#',  'S' , 'T', 以及 'B'。
 * grid 中 'S', 'B' 和 'T' 各只能出现一个。
 *
 * @author huangdu
 * @version 2023/5/8
 */
public class MinPushBox {
    private final int[] direction = {0, 1, 0, -1, 0};
    private int m;
    private int n;
    private char[][] grid;

    public int minPushBox(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        int[] startStatus = new int[2];
        int ans = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'S') {
                    startStatus[0] = i * n + j;
                } else if (grid[i][j] == 'B') {
                    startStatus[1] = i * n + j;
                }
            }
        }
        Queue<int[]> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(startStatus);
        visited.add(Arrays.hashCode(startStatus));
        while (!queue.isEmpty()) {
            for (int i = 0, size = queue.size(); i < size; i++) {
                int[] status = queue.remove();
                int sx = status[0] / n, sy = status[0] % n, bx = status[1] / n, by = status[1] % n;
                Set<Integer> range = range(sx, sy, bx, by);
                // 上
                if (bx - 1 >= 0 && grid[bx - 1][by] != '#' && bx + 1 < m && range.contains((bx + 1) * n + by)) {
                    if (grid[bx - 1][by] == 'T') {return ans;}
                    int[] newStatus = new int[2];
                    newStatus[0] = bx * n + by;
                    newStatus[1] = (bx - 1) * n + by;
                    if (visited.add(Arrays.hashCode(newStatus))) {
                        queue.offer(newStatus);
                    }
                }
                // 下
                if (bx + 1 < m && grid[bx + 1][by] != '#' && bx - 1 >= 0 && range.contains((bx - 1) * n + by)) {
                    if (grid[bx + 1][by] == 'T') {return ans;}
                    int[] newStatus = new int[2];
                    newStatus[0] = bx * n + by;
                    newStatus[1] = (bx + 1) * n + by;
                    if (visited.add(Arrays.hashCode(newStatus))) {
                        queue.offer(newStatus);
                    }
                }
                // 左
                if (by - 1 >= 0 && grid[bx][by - 1] != '#' && by + 1 < n && range.contains(bx * n + by + 1)) {
                    if (grid[bx][by - 1] == 'T') {return ans;}
                    int[] newStatus = new int[2];
                    newStatus[0] = bx * n + by;
                    newStatus[1] = bx * n + by - 1;
                    if (visited.add(Arrays.hashCode(newStatus))) {
                        queue.offer(newStatus);
                    }
                }
                // 右
                if (by + 1 < n && grid[bx][by + 1] != '#' && by - 1 >= 0 && range.contains(bx * n + by - 1)) {
                    if (grid[bx][by + 1] == 'T') {return ans;}
                    int[] newStatus = new int[2];
                    newStatus[0] = bx * n + by;
                    newStatus[1] = bx * n + by + 1;
                    if (visited.add(Arrays.hashCode(newStatus))) {
                        queue.offer(newStatus);
                    }
                }
            }
            ans++;
        }
        return -1;
    }

    private Set<Integer> range(int sx, int sy, int bx, int by) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        visited.add(sx * n + sy);
        queue.offer(sx * n + sy);
        while (!queue.isEmpty()) {
            int data = queue.poll(), x = data / n, y = data % n;
            for (int k = 0; k < 4; k++) {
                int nextX = x + direction[k], nextY = y + direction[k + 1];
                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || grid[nextX][nextY] == '#' || nextX == bx && nextY == by) {continue;}
                if (visited.add(nextX * n + nextY)) {
                    queue.offer(nextX * n + nextY);
                }
            }
        }
        return visited;
    }

    public static void main(String[] args) {
        char[][] grid = {{'T', 'B', '.'}, {'.', '.', '#'}, {'S', '.', '#'}};
        MinPushBox mpb = new MinPushBox();
        System.out.println(mpb.minPushBox(grid));
    }
}
