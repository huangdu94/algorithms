package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N 皇后
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 * 示例：
 * 输入：4
 * 输出：[
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 * 提示：
 * 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
 *
 * @author huangdu
 * @version 2020/9/3 9:52
 */
public class SolveNQueens {
    class Solution {
        public List<List<String>> solveNQueens(int n) {
            // 列编号 j
            // 对角线编号    n-1-i+j
            // 反对角线编号  i+j
            // 行编号不需要，因为每行放一个Q
            List<List<String>> ans = new ArrayList<>();
            backtrack(ans, n, 0, 0, 0, 0, new int[n]);
            return ans;
        }

        private void backtrack(List<List<String>> ans, int n, int i, int col, int diagonal, int backDiagonal, int[] record) {
            if (i == n) {
                ans.add(buildResult(n, record));
                return;
            }
            for (int j = 0; j < n; j++) {
                int colMask = 1 << j, diagonalMask = 1 << (n - 1 - i + j), backDiagonalMask = 1 << (i + j);
                if ((colMask & col) == 0 && (diagonalMask & diagonal) == 0 && (backDiagonalMask & backDiagonal) == 0) {
                    record[i] = j;
                    backtrack(ans, n, i + 1, col | colMask, diagonal | diagonalMask, backDiagonal | backDiagonalMask, record);
                }
            }
        }

        private List<String> buildResult(int n, int[] record) {
            List<String> res = new ArrayList<>(n);
            for (int index : record) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[index] = 'Q';
                res.add(new String(row));
            }
            return res;
        }
    }

    private int n;
    private List<List<String>> resList;
    private List<String> res;
    private char[] rowChars;
    private boolean[] colVisited;
    private boolean[] diaVisited;
    private boolean[] backVisited;

    /**
     * 回溯做法，n行
     * 1. 第0行有n种选择，选择完后占据了另外三个位置 col dia back (列、对角斜线、反对角斜线)
     * ...
     * 2. 第i行，需要选择一个col dia back都没被占据的位置，然后继续第i+1行，没有可选择的位置回溯到第i-1行
     * 3. 当到了第n+1行时说明选完了，加入结果list
     * 需要思考的：
     * 对于col设置一个n大小的boolean数组即可
     * 对于dia和back需要特殊的计算，首先它们两都需要2*n-1大小的boolean数组
     * dia的计算方法：dia=row+col
     * back的计算方法：back=row+(n-1-col)
     */
    public List<List<String>> solveNQueens(int _n) {
        n = _n;
        resList = new ArrayList<>();
        res = new ArrayList<>(n);
        rowChars = new char[n];
        Arrays.fill(rowChars, '.');
        colVisited = new boolean[n];
        diaVisited = new boolean[2 * n - 1];
        backVisited = new boolean[2 * n - 1];
        helper(0);
        return resList;
    }

    private void helper(int row) {
        if (row == n) {
            resList.add(new ArrayList<>(res));
            return;
        }
        for (int col = 0; col < n; col++) {
            int dia = row + col;
            int back = row + (n - 1 - col);
            if (!colVisited[col] && !diaVisited[dia] && !backVisited[back]) {
                colVisited[col] = true;
                diaVisited[dia] = true;
                backVisited[back] = true;
                rowChars[col] = 'Q';
                res.add(new String(rowChars));
                rowChars[col] = '.';
                helper(row + 1);
                // 回溯
                colVisited[col] = false;
                diaVisited[dia] = false;
                backVisited[back] = false;
                res.remove(row);
            }
        }
    }

    public List<List<String>> solveNQueens2(int _n) {
        n = _n;
        resList = new ArrayList<>();
        res = new ArrayList<>(n);
        rowChars = new char[n];
        Arrays.fill(rowChars, '.');
        helper(0, 0, 0, 0);
        return resList;
    }

    private void helper(int row, int colVisited, int diaVisited, int backVisited) {
        if (row == n) {
            resList.add(new ArrayList<>(res));
            return;
        }
        for (int col = 0; col < n; col++) {
            int dia = row + col;
            int back = row + (n - 1 - col);
            if (((colVisited >>> col) & 1) == 0 && ((diaVisited >>> dia) & 1) == 0 && ((backVisited >>> back) & 1) == 0) {
                rowChars[col] = 'Q';
                res.add(new String(rowChars));
                rowChars[col] = '.';
                helper(row + 1, colVisited | (1 << col), diaVisited | (1 << dia), backVisited | (1 << back));
                // 回溯
                res.remove(row);
            }
        }
    }
}
