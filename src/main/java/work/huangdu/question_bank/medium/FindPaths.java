package work.huangdu.question_bank.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 576. 出界的路径数
 * 给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。
 * 给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。因为答案可能非常大，返回对 109 + 7 取余 后的结果。
 * 示例 1：
 * 输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 * 输出：6
 * 示例 2：
 * 输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
 * 输出：12
 * 提示：
 * 1 <= m, n <= 50
 * 0 <= maxMove <= 50
 * 0 <= startRow < m
 * 0 <= startColumn < n
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/8/15
 */
public class FindPaths {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        final int base = (int)1e9 + 7;
        int paths = 0;
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int[][] board = new int[m][n];
        board[startRow][startColumn] = 1;
        Set<Integer> siteSet = new HashSet<>();
        siteSet.add(getIndex(n, startRow, startColumn));
        for (int k = 0; k < maxMove; k++) {
            int[][] newBoard = new int[m][n];
            Set<Integer> newSiteSet = new HashSet<>();
            for (int index : siteSet) {
                int[] coord = getCoord(n, index);
                int row = coord[0], coj = coord[1];
                int count = board[row][coj];
                for (int[] direction : directions) {
                    int newRow = row + direction[0], newCoj = coj + direction[1];
                    if (newRow < 0 || newRow >= m || newCoj < 0 || newCoj >= n) {
                        paths += count;
                        if (paths > base) {paths -= base;}
                        continue;
                    }
                    newSiteSet.add(getIndex(n, newRow, newCoj));
                    newBoard[newRow][newCoj] += count;
                    if (newBoard[newRow][newCoj] > base) {newBoard[newRow][newCoj] -= base;}
                }
            }
            siteSet = newSiteSet;
            board = newBoard;
        }
        return paths;
    }

    private int getIndex(int n, int row, int coj) {
        return row * n + coj;
    }

    private int[] getCoord(int n, int index) {
        return new int[] {index / n, index % n};
    }

    public static void main(String[] args) {
        FindPaths fp = new FindPaths();
        System.out.println(fp.findPaths(1, 1, 50, 0, 0));
    }
}
