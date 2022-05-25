package work.huangdu.question_bank.difficult;

/**
 * 52. N皇后 II
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
 * 上图为 8 皇后问题的一种解法。
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 * 示例:
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 * [".Q..",  // 解法 1
 * "...Q",
 * "Q...",
 * "..Q."],
 * ["..Q.",  // 解法 2
 * "Q...",
 * "...Q",
 * ".Q.."]
 * ]
 * 提示：
 * 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或 N-1 步，可进可退。（引用自 百度百科 - 皇后 ）
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/17 9:38
 */
public class TotalNQueens {
    class Solution {
        public int totalNQueens(int n) {
            // 列编号 j
            // 对角线编号    n-1-i+j
            // 反对角线编号  i+j
            // 行编号不需要，因为每行放一个Q
            return backtrack(n, 0, 0, 0, 0);
        }

        private int backtrack(int n, int i, int col, int diagonal, int backDiagonal) {
            if (i == n) {return 1;}
            int ans = 0;
            for (int j = 0; j < n; j++) {
                int colMask = 1 << j, diagonalMask = 1 << (n - 1 - i + j), backDiagonalMask = 1 << (i + j);
                if ((colMask & col) == 0 && (diagonalMask & diagonal) == 0 && (backDiagonalMask & backDiagonal) == 0) {
                    ans += backtrack(n, i + 1, col | colMask, diagonal | diagonalMask, backDiagonal | backDiagonalMask);
                }
            }
            return ans;
        }
    }

    private int n;
    private int total;

    public int totalNQueens(int _n) {
        n = _n;
        helper(0, 0, 0, 0);
        return total;
    }

    private void helper(int row, int colVisited, int diaVisited, int backVisited) {
        if (row == n) {
            total++;
            return;
        }
        for (int col = 0; col < n; col++) {
            int dia = row + col;
            int back = row + (n - 1 - col);
            if (((colVisited >>> col) & 1) == 0 && ((diaVisited >>> dia) & 1) == 0 && ((backVisited >>> back) & 1) == 0) {
                helper(row + 1, colVisited | (1 << col), diaVisited | (1 << dia), backVisited | (1 << back));
            }
        }
    }
}
