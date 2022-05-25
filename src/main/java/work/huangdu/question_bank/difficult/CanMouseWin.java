package work.huangdu.question_bank.difficult;

/**
 * 1728. 猫和老鼠 II
 * 一只猫和一只老鼠在玩一个叫做猫和老鼠的游戏。
 * 它们所处的环境设定是一个 rows x cols 的方格 grid ，其中每个格子可能是一堵墙、一块地板、一位玩家（猫或者老鼠）或者食物。
 * 玩家由字符 'C' （代表猫）和 'M' （代表老鼠）表示。
 * 地板由字符 '.' 表示，玩家可以通过这个格子。
 * 墙用字符 '#' 表示，玩家不能通过这个格子。
 * 食物用字符 'F' 表示，玩家可以通过这个格子。
 * 字符 'C' ， 'M' 和 'F' 在 grid 中都只会出现一次。
 * 猫和老鼠按照如下规则移动：
 * 老鼠 先移动 ，然后两名玩家轮流移动。
 * 每一次操作时，猫和老鼠可以跳到上下左右四个方向之一的格子，他们不能跳过墙也不能跳出 grid 。
 * catJump 和 mouseJump 是猫和老鼠分别跳一次能到达的最远距离，它们也可以跳小于最大距离的长度。
 * 它们可以停留在原地。
 * 老鼠可以跳跃过猫的位置。
 * 游戏有 4 种方式会结束：
 * 如果猫跟老鼠处在相同的位置，那么猫获胜。
 * 如果猫先到达食物，那么猫获胜。
 * 如果老鼠先到达食物，那么老鼠获胜。
 * 如果老鼠不能在 1000 次操作以内到达食物，那么猫获胜。
 * 给你 rows x cols 的矩阵 grid 和两个整数 catJump 和 mouseJump ，双方都采取最优策略，如果老鼠获胜，那么请你返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
 * 输出：true
 * 解释：猫无法抓到老鼠，也没法比老鼠先到达食物。
 * 示例 2：
 * 输入：grid = ["M.C...F"], catJump = 1, mouseJump = 4
 * 输出：true
 * 示例 3：
 * 输入：grid = ["M.C...F"], catJump = 1, mouseJump = 3
 * 输出：false
 * 示例 4：
 * 输入：grid = ["C...#","...#F","....#","M...."], catJump = 2, mouseJump = 5
 * 输出：false
 * 示例 5：
 * 输入：grid = [".M...","..#..","#..#.","C#.#.","...#F"], catJump = 3, mouseJump = 1
 * 输出：true
 * 提示：
 * rows == grid.length
 * cols = grid[i].length
 * 1 <= rows, cols <= 8
 * grid[i][j] 只包含字符 'C' ，'M' ，'F' ，'.' 和 '#' 。
 * grid 中只包含一个 'C' ，'M' 和 'F' 。
 * 1 <= catJump, mouseJump <= 8
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/10
 */
public class CanMouseWin {
    private static final char CAT = 'C';
    private static final char MOUSE = 'M';
    private static final char FOOD = 'F';
    private static final char WALL = '#';
    private static final int TURN = 2000;
    private final int[] directions = {0, 1, 0, -1, 0};
    private int rows;
    private int cols;
    private int catJump;
    private int mouseJump;
    private Boolean[][][][][] memo;
    private String[] grid;

    public boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
        this.rows = grid.length;
        this.cols = grid[0].length();
        this.catJump = catJump;
        this.mouseJump = mouseJump;
        this.memo = new Boolean[rows][cols][rows][cols][TURN];
        this.grid = grid;
        int cx, cy, mx, my;
        cx = cy = mx = my = -1;
        for (int i = 0; i < rows && (cx == -1 || mx == -1); i++) {
            String row = grid[i];
            for (int j = 0; j < cols; j++) {
                char c = row.charAt(j);
                if (c == CAT) {
                    cx = i;
                    cy = j;
                } else if (c == MOUSE) {
                    mx = i;
                    my = j;
                }
            }
        }
        return mouseMove(cx, cy, mx, my, 1);
    }

    private boolean mouseMove(int cx, int cy, int mx, int my, int turn) {
        if (turn >= TURN) {return false;}
        if (memo[cx][cy][mx][my][turn] != null) {return memo[cx][cy][mx][my][turn];}
        boolean result = false;
        for (int k = 0; k < 4; k++) {
            int xUnit = directions[k], yUnit = directions[k + 1];
            for (int step = 1; step <= mouseJump; step++) {
                int nextX = mx + xUnit * step, nextY = my + yUnit * step;
                if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {break;}
                char c = grid[nextX].charAt(nextY);
                if (c == WALL) {break;}
                if (c == FOOD) {
                    result = true;
                    break;
                }
                if (nextX == cx && nextY == cy) {continue;}
                result = catMove(cx, cy, nextX, nextY, turn + 1);
                if (result) {break;}
            }
            if (result) {break;}
        }
        return memo[cx][cy][mx][my][turn] = result;
    }

    private boolean catMove(int cx, int cy, int mx, int my, int turn) {
        if (turn >= TURN) {return false;}
        if (memo[cx][cy][mx][my][turn] != null) {return memo[cx][cy][mx][my][turn];}
        boolean result = true;
        for (int k = 0; k < 4; k++) {
            int xUnit = directions[k], yUnit = directions[k + 1];
            for (int step = 1; step <= catJump; step++) {
                int nextX = cx + xUnit * step, nextY = cy + yUnit * step;
                if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols) {break;}
                char c = grid[nextX].charAt(nextY);
                if (c == WALL) {break;}
                if (c == FOOD || nextX == mx && nextY == my) {
                    result = false;
                    break;
                }
                result = mouseMove(nextX, nextY, mx, my, turn + 1);
                if (!result) {break;}
            }
            if (!result) {break;}
        }
        if (result) {
            result = mouseMove(cx, cy, mx, my, turn + 1);
        }
        return memo[cx][cy][mx][my][turn] = result;
    }

    public static void main(String[] args) {
        CanMouseWin cmw = new CanMouseWin();
        String[] grid = {
            "#F..",
            "..#.",
            "..M.",
            "..C."};
        int catJump = 3;

        int mouseJump = 3;
        System.out.println(cmw.canMouseWin(grid, catJump, mouseJump));
        System.out.println();
    }
}
