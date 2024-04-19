package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 1444. 切披萨的方案数
 * 给你一个 rows x cols 大小的矩形披萨和一个整数 k ，矩形包含两种字符： 'A' （表示苹果）和 '.' （表示空白格子）。你需要切披萨 k-1 次，得到 k 块披萨并送给别人。
 * 切披萨的每一刀，先要选择是向垂直还是水平方向切，再在矩形的边界上选一个切的位置，将披萨一分为二。如果垂直地切披萨，那么需要把左边的部分送给一个人，如果水平地切，那么需要把上面的部分送给一个人。在切完最后一刀后，需要把剩下来的一块送给最后一个人。
 * 请你返回确保每一块披萨包含 至少 一个苹果的切披萨方案数。由于答案可能是个很大的数字，请你返回它对 10^9 + 7 取余的结果。
 * 示例 1：
 * 输入：pizza = ["A..","AAA","..."], k = 3
 * 输出：3
 * 解释：上图展示了三种切披萨的方案。注意每一块披萨都至少包含一个苹果。
 * 示例 2：
 * 输入：pizza = ["A..","AA.","..."], k = 3
 * 输出：1
 * 示例 3：
 * 输入：pizza = ["A..","A..","..."], k = 1
 * 输出：1
 * 提示：
 * 1 <= rows, cols <= 50
 * rows == pizza.length
 * cols == pizza[i].length
 * 1 <= k <= 10
 * pizza 只包含字符 'A' 和 '.' 。
 *
 * @author huangdu
 * @version 2023/8/21
 */
public class Ways {
    private int m;
    private int n;
    private int[][] sum;
    private int[][][] memo;

    public int ways(String[] pizza, int k) {
        this.m = pizza.length;
        this.n = pizza[0].length();
        this.sum = new int[m + 1][n + 1];
        this.memo = new int[m][n][k];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i + 1][j] + sum[i][j + 1] - sum[i][j] + (pizza[i].charAt(j) & 1);
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        if (query(0, 0, m, n) < k) {return 0;}
        return dfs(0, 0, k);
    }

    private int dfs(int startI, int startJ, int k) {
        if (k == 1) {return 1;}
        if (memo[startI][startJ][k - 1] != -1) {return memo[startI][startJ][k - 1];}
        int ans = 0;
        for (int i = startI + 1; i < m; i++) {
            if (query(startI, startJ, i, n) > 0 && query(i, startJ, m, n) > 0) {
                ans = (ans + dfs(i, startJ, k - 1)) % 1000000007;
            }
        }
        for (int j = startJ + 1; j < n; j++) {
            if (query(startI, startJ, m, j) > 0 && query(startI, j, m, n) > 0) {
                ans = (ans + dfs(startI, j, k - 1)) % 1000000007;
            }
        }
        return memo[startI][startJ][k - 1] = ans;
    }

    private int query(int r1, int c1, int r2, int c2) {
        return sum[r2][c2] - sum[r2][c1] - sum[r1][c2] + sum[r1][c1];
    }

    public static void main(String[] args) {
        String[] pizza = {"A..", "AA.", "..."};
        Ways ways = new Ways();
        System.out.println(ways.ways(pizza, 3));
    }
}
