package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1210. 穿过迷宫的最少移动次数
 * 你还记得那条风靡全球的贪吃蛇吗？
 * 我们在一个 n*n 的网格上构建了新的迷宫地图，蛇的长度为 2，也就是说它会占去两个单元格。蛇会从左上角（(0, 0) 和 (0, 1)）开始移动。我们用 0 表示空单元格，用 1 表示障碍物。蛇需要移动到迷宫的右下角（(n-1, n-2) 和 (n-1, n-1)）。
 * 每次移动，蛇可以这样走：
 * 如果没有障碍，则向右移动一个单元格。并仍然保持身体的水平／竖直状态。
 * 如果没有障碍，则向下移动一个单元格。并仍然保持身体的水平／竖直状态。
 * 如果它处于水平状态并且其下面的两个单元都是空的，就顺时针旋转 90 度。蛇从（(r, c)、(r, c+1)）移动到 （(r, c)、(r+1, c)）。
 * 如果它处于竖直状态并且其右面的两个单元都是空的，就逆时针旋转 90 度。蛇从（(r, c)、(r+1, c)）移动到（(r, c)、(r, c+1)）。
 * 返回蛇抵达目的地所需的最少移动次数。
 * 如果无法到达目的地，请返回 -1。
 * 示例 1：
 * 输入：grid = [[0,0,0,0,0,1],
 * [1,1,0,0,1,0],
 * [0,0,0,0,1,1],
 * [0,0,1,0,1,0],
 * [0,1,1,0,0,0],
 * [0,1,1,0,0,0]]
 * 输出：11
 * 解释：
 * 一种可能的解决方案是 [右, 右, 顺时针旋转, 右, 下, 下, 下, 下, 逆时针旋转, 右, 下]。
 * 示例 2：
 * 输入：grid = [[0,0,1,1,1,1],
 * [0,0,0,0,1,1],
 * [1,1,0,0,0,1],
 * [1,1,1,0,0,1],
 * [1,1,1,0,0,1],
 * [1,1,1,0,0,0]]
 * 输出：9
 * 提示：
 * 2 <= n <= 100
 * 0 <= grid[i][j] <= 1
 * 蛇保证从空单元格开始出发。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/2/6
 */
public class MinimumMoves {
    public int minimumMoves(int[][] grid) {
        this.n = grid.length;
        this.gird = grid;
        this.visited = new boolean[n][n][2];
        // [x,y,d]分别表示蛇头坐标xy，蛇身方向：0 水平、1 垂直
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {0, 1, 0});
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] snake = queue.remove();
                if (checkRight(snake)) {
                    int[] next = goRight(snake);
                    if (end(next)) {return step;}
                    if (checkVisited(next)) {
                        queue.offer(next);
                    }
                }
                if (checkDown(snake)) {
                    int[] next = goDown(snake);
                    if (end(next)) {return step;}
                    if (checkVisited(next)) {
                        queue.offer(next);
                    }
                }
                if (checkTurn(snake)) {
                    int[] next = turn(snake);
                    if (end(next)) {return step;}
                    if (checkVisited(next)) {
                        queue.offer(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }

    int n;
    int[][] gird;
    boolean[][][] visited;

    boolean checkRight(int[] snake) {
        int x = snake[0], y = snake[1], d = snake[2];
        if (y + 1 >= n) {return false;}
        if (d == 0) {return gird[x][y + 1] == 0;}
        return gird[x - 1][y + 1] == 0 && gird[x][y + 1] == 0;
    }

    boolean checkDown(int[] snake) {
        int x = snake[0], y = snake[1], d = snake[2];
        if (x + 1 >= n) {return false;}
        if (d == 1) {return gird[x + 1][y] == 0;}
        return gird[x + 1][y - 1] == 0 && gird[x + 1][y] == 0;
    }

    boolean checkTurn(int[] snake) {
        if (snake[2] == 0) {return checkDown(snake);}
        return checkRight(snake);
    }

    boolean checkVisited(int[] snake) {
        if (visited[snake[0]][snake[1]][snake[2]]) {return false;}
        return visited[snake[0]][snake[1]][snake[2]] = true;
    }

    int[] goRight(int[] snake) {
        return new int[] {snake[0], snake[1] + 1, snake[2]};
    }

    int[] goDown(int[] snake) {
        return new int[] {snake[0] + 1, snake[1], snake[2]};
    }

    int[] turn(int[] snake) {
        int x = snake[0], y = snake[1], d = snake[2];
        if (d == 0) {return new int[] {x + 1, y - 1, 1};}
        return new int[] {x - 1, y + 1, 0};
    }

    boolean end(int[] snake) {
        return snake[0] == n - 1 && snake[1] == n - 1 && snake[2] == 0;
    }

    public static void main(String[] args) {
        int[][] gird = {{0, 0}, {0, 0}};
        MinimumMoves mm = new MinimumMoves();
        System.out.println(mm.minimumMoves(gird));
    }
}
