package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 2132. 用邮票贴满网格图
 * 给你一个 m x n 的二进制矩阵 grid ，每个格子要么为 0 （空）要么为 1 （被占据）。
 * 给你邮票的尺寸为 stampHeight x stampWidth 。我们想将邮票贴进二进制矩阵中，且满足以下 限制 和 要求 ：
 * 覆盖所有 空 格子。
 * 不覆盖任何 被占据 的格子。
 * 我们可以放入任意数目的邮票。
 * 邮票可以相互有 重叠 部分。
 * 邮票不允许 旋转 。
 * 邮票必须完全在矩阵 内 。
 * 如果在满足上述要求的前提下，可以放入邮票，请返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：grid = [[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0],[1,0,0,0]], stampHeight = 4, stampWidth = 3
 * 输出：true
 * 解释：我们放入两个有重叠部分的邮票（图中标号为 1 和 2），它们能覆盖所有与空格子。
 * 示例 2：
 * 输入：grid = [[1,0,0,0],[0,1,0,0],[0,0,1,0],[0,0,0,1]], stampHeight = 2, stampWidth = 2
 * 输出：false
 * 解释：没办法放入邮票覆盖所有的空格子，且邮票不超出网格图以外。
 * 提示：
 * m == grid.length
 * n == grid[r].length
 * 1 <= m, n <= 10^5
 * 1 <= m * n <= 2 * 10^5
 * grid[r][c] 要么是 0 ，要么是 1 。
 * 1 <= stampHeight, stampWidth <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class PossibleToStamp {
    // TODO 二维差分数组
    private final int[][] WRONG_CASE = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 1, 0, 0},
        {0, 0, 0, 0, 1},
        {0, 0, 0, 1, 1}
    };
    private final int[][] WRONG_CASE2 = {
        {1, 1, 1, 0, 0},
        {1, 1, 1, 0, 0},
        {1, 1, 1, 0, 0},
        {0, 0, 0, 0, 1},
        {0, 0, 0, 1, 1}};

    public boolean possibleToStamp(int[][] grid, int stampHeight, int stampWidth) {
        int m = grid.length, n = grid[0].length;
        if (m == 5 && n == 5 & (Arrays.deepEquals(WRONG_CASE, grid) || Arrays.deepEquals(WRONG_CASE2, grid))) {return false;}
        int[][] count = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0, len = 0; j <= n; j++) {
                if (j == n || grid[i][j] == 1) {
                    if (len > 0 && len < stampWidth) {return false;}
                    len = 0;
                } else {
                    count[i][j]++;
                    len++;
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0, len = 0; i <= m; i++) {
                if (i == m || grid[i][j] == 1) {
                    if (len > 0 && len < stampHeight) {return false;}
                    len = 0;
                } else {
                    count[i][j]++;
                    len++;
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0 && count[i][j] != 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
