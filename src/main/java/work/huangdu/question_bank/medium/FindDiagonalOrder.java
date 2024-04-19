package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 498. 对角线遍历
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2022/6/15
 */
public class FindDiagonalOrder {
    public int[] findDiagonalOrder(int[][] mat) {
        int m = mat.length, n = mat[0].length, mn = m * n;
        int[] ans = new int[mn];
        int x = 0, y = 0, turn = 0;
        for (int i = 0; i < mn; i++) {
            if (x == m) {
                x--;
                y += 2;
                turn++;
            } else if (y == n) {
                x += 2;
                y--;
                turn++;
            } else if (x == -1) {
                x = 0;
                turn++;
            } else if (y == -1) {
                y = 0;
                turn++;
            }
            ans[i] = mat[x][y];
            if ((turn & 1) == 0) {
                x--;
                y++;
            } else {
                x++;
                y--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindDiagonalOrder fdo = new FindDiagonalOrder();
        int[][] mat = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(fdo.findDiagonalOrder(mat)));
    }
}
