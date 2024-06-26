package work.huangdu.exploration.start_from_scratch.array.roll_2d;

/**
 * 419. 甲板上的战舰
 * 给定一个二维的甲板， 请计算其中有多少艘战舰。 战舰用 'X'表示，空位用 '.'表示。 你需要遵守以下规则：
 * 给你一个有效的甲板，仅由战舰或者空位组成。
 * 战舰只能水平或者垂直放置。换句话说,战舰只能由 1xN (1 行, N 列)组成，或者 Nx1 (N 行, 1 列)组成，其中N可以是任意大小。
 * 两艘战舰之间至少有一个水平或垂直的空位分隔 - 即没有相邻的战舰。
 * 示例 :
 * X..X
 * ...X
 * ...X
 * 在上面的甲板中有2艘战舰。
 * 无效样例 :
 * ...X
 * XXXX
 * ...X
 * 你不会收到这样的无效甲板 - 因为战舰之间至少会有一个空位将它们分开。
 * 进阶:
 * 你可以用一次扫描算法，只使用O(1)额外空间，并且不修改甲板的值来解决这个问题吗？
 *
 * @author huangdu
 * @version 2020/9/16 1:02
 */
public class CountBattleships {
    public int countBattleships2(char[][] board) {
        int m = board.length, n = board[0].length, count = 0;
        int[] direction = {1, 0, -1, 0, 1};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    count++;
                    board[i][j] = '.';
                    for (int k = 0; k < 4; k++) {
                        int nextI = i + direction[k], nextJ = j + direction[k + 1];
                        boolean flag = false;
                        while (nextI >= 0 && nextI < m && nextJ >= 0 && nextJ < n && board[nextI][nextJ] == 'X') {
                            flag = true;
                            board[nextI][nextJ] = '.';
                            nextI += direction[k];
                            nextJ += direction[k + 1];
                        }
                        if (flag) {
                            break;
                        }
                    }
                }
            }
        }
        return count;
    }

    public int countBattleships(char[][] board) {
        int m = board.length, n = board[0].length, count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' &&
                    (i == m - 1 || board[i + 1][j] == '.') &&
                    (j == n - 1 || board[i][j + 1] == '.')) {
                    count++;
                }
            }
        }
        return count;
    }
}
