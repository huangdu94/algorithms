package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 37. 解数独
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * 一个数独的解法需遵循如下规则：
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 * 一个数独。
 * 答案被标成红色。
 * Note:
 * 给定的数独序列只包含数字 1-9 和字符 '.' 。
 * 你可以假设给定的数独只有唯一解。
 * 给定数独永远是 9x9 形式的。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/15 9:54
 */
public class SolveSudoku {
    private char[][] board;
    private int[] flags;

    public void solveSudoku(char[][] board) {
        this.board = board;
        init();
        backtrack(0);
    }

    private boolean backtrack(int index) {
        if (index == 81) {return true;}
        int row = index / 9, coj = index % 9;
        if (board[row][coj] != '.') {
            return backtrack(index + 1);
        }
        int block = (row / 3) * 3 + coj / 3;
        for (char i = '1'; i <= '9'; i++) {
            if (valid(row, coj, block, i)) {
                board[row][coj] = i;
                commit(row, coj, block, i);
                if (backtrack(index + 1)) {
                    return true;
                }
                board[row][coj] = '.';
                rollback(row, coj, block, i);
            }
        }
        return false;
    }

    private void init() {
        this.flags = new int[9];
        for (int row = 0; row < 9; row++) {
            for (int coj = 0; coj < 9; coj++) {
                char num = board[row][coj];
                if (num != '.') {
                    flags[row] |= getRowFlag(num);
                    flags[coj] |= getCojFlag(num);
                    flags[(row / 3) * 3 + coj / 3] |= getBlockFlag(num);
                }
            }
        }
    }

    private boolean valid(int row, int coj, int block, int num) {
        return (flags[row] & getRowFlag(num)) == 0
            && (flags[coj] & getCojFlag(num)) == 0
            && (flags[block] & getBlockFlag(num)) == 0;
    }

    private void commit(int row, int coj, int block, int num) {
        flags[row] |= getRowFlag(num);
        flags[coj] |= getCojFlag(num);
        flags[block] |= getBlockFlag(num);
    }

    private void rollback(int row, int coj, int block, int num) {
        flags[row] ^= getRowFlag(num);
        flags[coj] ^= getCojFlag(num);
        flags[block] ^= getBlockFlag(num);
    }

    private int getRowFlag(int num) {
        return 1 << (num + 17);
    }

    private int getCojFlag(int num) {
        return 1 << (num + 8);
    }

    private int getBlockFlag(int num) {
        return 1 << (num - 1);
    }

    // 一个int数字32位使用低27位，1-9行，10-18列，19-27宫
    // 9行9列9宫对应9个数字
    public void solveSudoku2(char[][] board) {
        List<int[]> blankList = new ArrayList<>();
        int[] records = new int[9];
        for (int row = 0; row < 9; row++) {
            for (int coj = 0; coj < 9; coj++) {
                int block = (row / 3) * 3 + coj / 3;
                if (board[row][coj] == '.') {
                    blankList.add(new int[] {row, coj, block, -1});
                } else {
                    int record = 1 << (board[row][coj] - '1');
                    records[row] |= (record << 18);
                    records[coj] |= (record << 9);
                    records[block] |= record;
                }
            }
        }
        helper(blankList, records, 0);
        for (int[] blank : blankList) {
            board[blank[0]][blank[1]] = (char)(blank[3] + '0');
        }
    }

    private boolean helper(List<int[]> blankList, int[] records, int index) {
        if (index == blankList.size()) { return true; }
        int[] blank = blankList.get(index);
        int row = blank[0], coj = blank[1], block = blank[2];
        for (int num = 1; num < 10; num++) {
            int mask = 1 << (num - 1);
            if (((records[row] >> 18) & mask) == 0 &&
                ((records[coj] >> 9) & mask) == 0 &&
                (records[block] & mask) == 0) {
                int rowRecord = records[row];
                int cojRecord = records[coj];
                int blockRecord = records[block];
                records[row] |= (mask << 18);
                records[coj] |= (mask << 9);
                records[block] |= mask;
                blank[3] = num;
                if (helper(blankList, records, index + 1)) {
                    return true;
                }
                records[row] = rowRecord;
                records[coj] = cojRecord;
                records[block] = blockRecord;
                blank[3] = -1;
            }
        }
        return false;
    }

    /*
     {
      {'5','3','4','6','7','8','9','1','2'}
     ,{'6','7','2','1','9','5','3','4','8'}
     ,{'1','9','8','3','4','2','5','6','7'}
     ,{'8','5','9','7','6','1','4','2','3'}
     ,{'4','2','6','8','5','3','7','9','1'}
     ,{'7','1','3','9','2','4','8','5','6'}
     ,{'9','6','1','5','3','7','2','8','4'}
     ,{'2','8','7','4','1','9','6','3','5'}
     ,{'3','4','5','2','8','6','1','7','9'}
     }
    */
    public static void main(String[] args) {
        SolveSudoku ss = new SolveSudoku();
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};
        ss.solveSudoku(board);
        System.out.println(Arrays.deepToString(board));
    }
}
