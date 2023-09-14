package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1222. 可以攻击国王的皇后
 * 在一个 8x8 的棋盘上，放置着若干「黑皇后」和一个「白国王」。
 * 给定一个由整数坐标组成的数组 queens ，表示黑皇后的位置；以及一对坐标 king ，表示白国王的位置，返回所有可以攻击国王的皇后的坐标(任意顺序)。
 * 示例 1：
 * 输入：queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
 * 输出：[[0,1],[1,0],[3,3]]
 * 解释：
 * [0,1] 的皇后可以攻击到国王，因为他们在同一行上。
 * [1,0] 的皇后可以攻击到国王，因为他们在同一列上。
 * [3,3] 的皇后可以攻击到国王，因为他们在同一条对角线上。
 * [0,4] 的皇后无法攻击到国王，因为她被位于 [0,1] 的皇后挡住了。
 * [4,0] 的皇后无法攻击到国王，因为她被位于 [1,0] 的皇后挡住了。
 * [2,4] 的皇后无法攻击到国王，因为她和国王不在同一行/列/对角线上。
 * 示例 2：
 * 输入：queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
 * 输出：[[2,2],[3,4],[4,4]]
 * 示例 3：
 * 输入：queens = [[5,6],[7,7],[2,1],[0,7],[1,6],[5,1],[3,7],[0,3],[4,0],[1,2],[6,3],[5,0],[0,4],[2,2],[1,1],[6,4],[5,4],[0,0],[2,6],[4,5],[5,2],[1,4],[7,5],[2,3],[0,5],[4,2],[1,0],[2,7],[0,1],[4,6],[6,1],[0,6],[4,3],[1,7]], king = [3,4]
 * 输出：[[2,3],[1,4],[1,6],[3,7],[4,3],[5,4],[4,5]]
 * 提示：
 * 1 <= queens.length <= 63
 * queens[i].length == 2
 * 0 <= queens[i][j] < 8
 * king.length == 2
 * 0 <= king[0], king[1] < 8
 * 一个棋盘格上最多只能放置一枚棋子。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class QueensAttacktheKing {
    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int[][] caches = new int[8][3];
        int x = king[0], y = king[1];
        for (int[] queen : queens) {
            int _x = queen[0], _y = queen[1];
            int direction = direction(_x, _y, x, y);
            if (direction != -1) {
                int[] cache = caches[direction];
                int d = Math.abs(x - _x) + Math.abs(y - _y);
                if (cache[0] == 0 || cache[0] > d) {
                    cache[0] = d;
                    cache[1] = queen[0];
                    cache[2] = queen[1];
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>(8);
        for (int[] cache : caches) {
            if (cache[0] == 0) {continue;}
            ans.add(Arrays.asList(cache[1], cache[2]));
        }
        return ans;
    }

    private int direction(int _x, int _y, int x, int y) {
        if (_x == x) {return _y < y ? 0 : 1;}
        if (_y == y) {return _x < x ? 2 : 3;}
        if (Math.abs(x - _x) == Math.abs(y - _y)) {
            if (_x < x) {return _y < y ? 4 : 5;}
            return _y < y ? 6 : 7;
        }
        return -1;
    }

    public List<List<Integer>> queensAttacktheKing2(int[][] queens, int[] king) {
        List<List<Integer>> ans = new ArrayList<>(8);
        boolean[][] board = new boolean[9][9];
        for (int[] queen : queens) {board[queen[0]][queen[1]] = true;}
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {continue;}
                int x = king[0], y = king[1];
                do {
                    x += i;
                    y += j;
                } while (x >= 0 && x < 9 && y >= 0 && y < 9 && !board[x][y]);
                if (x >= 0 && x < 9 && y >= 0 && y < 9 && board[x][y]) {
                    ans.add(Arrays.asList(x, y));
                }
            }
        }
        return ans;
    }
}
