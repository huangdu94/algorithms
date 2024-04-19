package work.huangdu.question_bank.difficult;

/**
 * 782. 变为棋盘
 * 一个 n x n 的二维网络 board 仅由 0 和 1 组成 。每次移动，你能任意交换两列或是两行的位置。
 * 返回 将这个矩阵变为  “棋盘”  所需的最小移动次数 。如果不存在可行的变换，输出 -1。
 * “棋盘” 是指任意一格的上下左右四个方向的值均与本身不同的矩阵。
 * 示例 1:
 * 输入: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
 * 输出: 2
 * 解释:一种可行的变换方式如下，从左到右：
 * 第一次移动交换了第一列和第二列。
 * 第二次移动交换了第二行和第三行。
 * 示例 2:
 * 输入: board = [[0, 1], [1, 0]]
 * 输出: 0
 * 解释: 注意左上角的格值为0时也是合法的棋盘，也是合法的棋盘.
 * 示例 3:
 * 输入: board = [[1, 0], [1, 0]]
 * 输出: -1
 * 解释: 任意的变换都不能使这个输入变为合法的棋盘。
 * 提示：
 * n == board.length
 * n == board[i].length
 * 2 <= n <= 30
 * board[i][j] 将只包含 0或 1
 *
 * @author huangdu
 * @version 2022/9/1
 */
public class MovesToChessboard {
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int firstRowSnapshot = snapshot(board[0]), firstRowOneCount = bitCount(firstRowSnapshot);
        if (countCheck(n, firstRowOneCount)) {return -1;}
        int firstCojSnapshot = snapshot(board, n, 0), firstCojOneCount = bitCount(firstCojSnapshot);
        if (countCheck(n, firstCojOneCount)) {return -1;}
        int rowEqualCount = checkRowAndCount(firstRowSnapshot, board, n);
        if (rowEqualCount == -1 || countCheck(n, rowEqualCount)) {return -1;}
        int cojEqualCount = checkCojAndCount(firstCojSnapshot, board, n);
        if (cojEqualCount == -1 || countCheck(n, cojEqualCount)) {return -1;}
        return ans(n, firstRowSnapshot, firstRowOneCount, rowEqualCount, firstCojSnapshot, firstCojOneCount, cojEqualCount);
    }

    private boolean countCheck(int n, int count) {
        return !((n & 1) == 0 && (n >> 1) == count || (n & 1) == 1 && Math.abs(n - (count << 1)) == 1);
    }

    private int snapshot(int[] array) {
        int snapshot = 0;
        for (int element : array) {
            snapshot = (snapshot << 1) | element;
        }
        return snapshot;
    }

    private int snapshot(int[][] board, int n, int coj) {
        int snapshot = 0;
        for (int i = 0; i < n; i++) {
            snapshot = (snapshot << 1) | board[i][coj];
        }
        return snapshot;
    }

    private int notSnapshot(int base, int n) {
        return ~base & ((1 << n) - 1);
    }

    private int checkRowAndCount(int base, int[][] board, int n) {
        int notBase = notSnapshot(base, n), count = 1;
        for (int i = 1; i < n; i++) {
            int cur = snapshot(board[i]);
            if (cur != base && cur != notBase) {return -1;}
            if (cur == base) {count++;}
        }
        return count;
    }

    private int checkCojAndCount(int base, int[][] board, int n) {
        int notBase = notSnapshot(base, n), count = 1;
        for (int j = 1; j < n; j++) {
            int cur = snapshot(board, n, j);
            if (cur != base && cur != notBase) {return -1;}
            if (cur == base) {count++;}
        }
        return count;
    }

    private int bitCount(int target) {
        return Integer.bitCount(target);
    }

    private int ans(int n, int firstRowSnapshot, int firstRowOneCount, int rowEqualCount, int firstCojSnapshot, int firstCojOneCount, int cojEqualCount) {
        int mask = 0B01010101010101010101010101010101;
        if ((n & 1) == 0) {
            // 取左上角是0，和左上角是1最小的
            int rowNeedChange = bitCount(firstRowSnapshot & mask), cojNeedChange = bitCount(firstCojSnapshot & mask);
            return Math.min(rowNeedChange, (n >> 1) - rowNeedChange) + Math.min(cojNeedChange, (n >> 1) - cojNeedChange);
        } else {
            // 左上角只能是，数量多的那个行，0和1数量多的那个
            int rowNeedChange = bitCount(firstRowSnapshot & (firstRowOneCount > n >> 1 ? ~mask : mask)),
                cojNeedChange = bitCount(firstCojSnapshot & (firstCojOneCount > n >> 1 ? ~mask : mask));
            return rowNeedChange + cojNeedChange;
        }
    }

    public static void main(String[] args) {
        MovesToChessboard mtc = new MovesToChessboard();
        int[][] board = {
            // 0  1  0  0  0  1  0  1  0  1  0  0  0  0  0  0  0
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1}
        };
        System.out.println(mtc.movesToChessboard(board));
    }
}
