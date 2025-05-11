package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * LCP 41. 黑白翻转棋
 * 在 n*m 大小的棋盘中，有黑白两种棋子，黑棋记作字母 "X", 白棋记作字母 "O"，空余位置记作 "."。当落下的棋子与其他相同颜色的棋子在行、列或对角线完全包围（中间不存在空白位置）另一种颜色的棋子，则可以翻转这些棋子的颜色。
 * 「力扣挑战赛」黑白翻转棋项目中，将提供给选手一个未形成可翻转棋子的棋盘残局，其状态记作 chessboard。若下一步可放置一枚黑棋，请问选手最多能翻转多少枚白棋。
 * 注意：
 * 若翻转白棋成黑棋后，棋盘上仍存在可以翻转的白棋，将可以 继续 翻转白棋
 * 输入数据保证初始棋盘状态无可以翻转的棋子且存在空余位置
 * 示例 1：
 * 输入：chessboard =
 * ["....X."
 * ,"....X."
 * ,"XOOO.."
 * ,"......"
 * ,"......"]
 * 输出：3
 * 解释：
 * 可以选择下在 [2,4] 处，能够翻转白方三枚棋子。
 * 示例 2：
 * 输入：chessboard =
 * [".X."
 * ,".O."
 * ,"XO."]
 * 输出：2
 * 解释：
 * 可以选择下在 [2,2] 处，能够翻转白方两枚棋子。
 * 示例 3：
 * 输入：chessboard =
 * ["......."
 * ,"......."
 * ,"......."
 * ,"X......"
 * ,".O....."
 * ,"..O...."
 * ,"....OOX"]
 * 输出：4
 * 解释：
 * 可以选择下在 [6,3] 处，能够翻转白方四枚棋子。
 * 提示：
 * 1 <= chessboard.length, chessboard[i].length <= 8
 * chessboard[i] 仅包含 "."、"O" 和 "X"
 *
 * @author huangdu
 * @version 2023/6/25
 */
public class FlipChess {
    private int m;
    private int n;
    private char[][] chessboard;
    private final int[] direction = {-1, 1, 1, 0, 1, -1, -1, 0, -1};

    public int flipChess(String[] chessboard) {
        this.m = chessboard.length;
        this.n = chessboard[0].length();
        this.chessboard = new char[m][n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (chessboard[i].charAt(j) == '.') {
                    init(chessboard);
                    ans = Math.max(ans, flip(i, j));
                }
            }
        }
        return ans;
    }

    private void init(String[] chessboard) {
        for (int k = 0; k < m; k++) {this.chessboard[k] = chessboard[k].toCharArray();}
    }

    private int flip(int i, int j) {
        int count = 0;
        List<int[]> nextList = new ArrayList<>();
        for (int k = 0; k < 8; k++) {
            int add = 0, deltaI = direction[k], deltaJ = direction[k + 1], curI = i + deltaI, curJ = j + deltaJ;
            while (check(curI, curJ, 'O')) {
                curI += deltaI;
                curJ += deltaJ;
                add++;
            }
            if (add > 0 && check(curI, curJ, 'X')) {
                count += add;
                for (int p = 0; p < add; p++) {
                    chessboard[curI -= deltaI][curJ -= deltaJ] = 'X';
                    nextList.add(new int[] {curI, curJ});
                }
            }
        }
        for (int[] next : nextList) {count += flip(next[0], next[1]);}
        return count;
    }

    private boolean range(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    private boolean check(int i, int j, char ch) {
        return range(i, j) && chessboard[i][j] == ch;
    }

    public static void main(String[] args) {
        FlipChess fc = new FlipChess();
        String[] chessboard = {"....X.", "....X.", "XOOO..", "......", "......"};
        System.out.println(fc.flipChess(chessboard));
    }
}
