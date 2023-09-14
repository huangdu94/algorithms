package work.huangdu.question_bank.medium;

/**
 * 794. 有效的井字游戏
 * 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * 以下是井字游戏的规则：
 * 玩家轮流将字符放入空位（' '）中。
 * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 * 示例 1：
 * 输入：board = ["O  ","   ","   "]
 * 输出：false
 * 解释：玩家 1 总是放字符 "X" 。
 * 示例 2：
 * 输入：board = ["XOX"," X ","   "]
 * 输出：false
 * 解释：玩家应该轮流放字符。
 * 示例 3：
 * 输入：board = ["XXX","   ","OOO"]
 * 输出：false
 * Example 4:
 * 输入：board = ["XOX","O O","XOX"]
 * 输出：true
 * 提示：
 * board.length == 3
 * board[i].length == 3
 * board[i][j] 为 'X'、'O' 或 ' '
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/12/9
 */
public class ValidTicTacToe {
    private static final char o = 'O';
    private static final char x = 'X';
    private static final char blank = ' ';

    public boolean validTicTacToe(String[] b) {
        char[][] board = new char[3][];
        for (int i = 0; i < 3; i++) {board[i] = b[i].toCharArray();}
        int countO = 0, countX = 0;
        for (char[] row : board) {
            for (char element : row) {
                if (element == o) {
                    countO++;
                } else if (element == x) {
                    countX++;
                }
            }
        }
        if (countX < countO || countX - countO > 1) {return false;}
        if (countX < 3 || countX == 3 && countO < 3) {return true;}
        int rowWinX = 0, rowWinO = 0;
        for (char[] row : board) {
            if (row[0] != blank && row[0] == row[1] && row[1] == row[2]) {
                if (row[0] == o && ++rowWinO > 1) {
                    return false;
                } else if (row[0] == x && ++rowWinX > 1) {
                    return false;
                }
            }
        }
        int cojWinX = 0, cojWinO = 0;
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != blank && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] == o && ++cojWinO > 1) {
                    return false;
                } else if (board[0][j] == x && ++cojWinX > 1) {
                    return false;
                }
            }
        }
        int diagonalWinX = 0, diagonalWinO = 0;
        if (board[0][0] != blank && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == o) {
                ++diagonalWinO;
            } else if (board[0][0] == x) {
                ++diagonalWinX;
            }
        }
        int backDiagonalWinX = 0, backDiagonalWinO = 0;
        if (board[0][2] != blank && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == o) {
                ++backDiagonalWinO;
            } else if (board[0][2] != x) {
                ++backDiagonalWinX;
            }
        }
        int winX = rowWinX + cojWinX + diagonalWinX + backDiagonalWinX, winO = rowWinO + cojWinO + diagonalWinO + backDiagonalWinO;
        if (winX != 0 && winO == 0 && countX == countO) {return false;}
        if (winX == 0 && winO != 0 && countX > countO) {return false;}
        return winO == 0 || winX == 0;
    }

    public static void main(String[] args) {
        ValidTicTacToe vttt = new ValidTicTacToe();
        System.out.println(vttt.validTicTacToe(new String[] {
            "OXX",
            "XOX",
            "OXO"
        }));
    }
}
