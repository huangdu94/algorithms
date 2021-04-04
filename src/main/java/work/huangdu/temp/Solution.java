package work.huangdu.temp;

import java.util.Arrays;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/12
 */
public class Solution {
    private int n;
    private int max;
    private int[][] ans;
    private boolean[] used;
    private int mid;
    // 一行的值
    private int standard;

    public int[][] sudoku(int n) {
        this.n = n;
        this.max = n * n;
        this.ans = new int[n][n];
        this.used = new boolean[max + 1];
        // 中间数必固定
        this.mid = (max + 1) / 2;
        ans[n / 2][n / 2] = mid;
        used[mid] = true;
        this.standard = n * (max + 1) / 2;
        backtrack(0);
        return ans;
    }

    public boolean backtrack(int idx) {
        // 跳过中间一个数，它是确定的
        if (idx == mid - 1) { idx++;}
        if (idx == max) { return true; }
        int row = idx / n, coj = idx % n;
        for (int num = 1; num <= max; num++) {
            if (!used[num] && check(row, coj, num)) {
                ans[row][coj] = num;
                used[num] = true;
                if (backtrack(idx + 1)) {
                    return true;
                }
                used[num] = false;
            }
        }
        return false;
    }

    public boolean check(int row, int coj, int num) {
        int sum;
        // 检查行
        if (coj == n - 1) {
            sum = num;
            for (int j = 0; j < n - 1; j++) {
                sum += ans[row][j];
            }
            if (sum != standard) {return false;}
        }
        // 检查列
        if (row == n - 1) {
            sum = num;
            for (int i = 0; i < n - 1; i++) {
                sum += ans[i][coj];
            }
            if (sum != standard) {return false;}
        }
        // 检查对角线 或 反对角线
        if (row == n - 1) {
            if (coj == n - 1) {
                sum = num;
                for (int i = 0; i < n - 1; i++) { sum += ans[i][i]; }
                return sum == standard;
            } else if (coj == 0) {
                sum = num;
                for (int i = 0; i < n - 1; i++) { sum += ans[i][n - i - 1]; }
                return sum == standard;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long start = System.currentTimeMillis();
        System.out.println(Arrays.deepToString(solution.sudoku(5)));
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start) / 1000.0 + "秒.");
        System.out.println("Done!");
    }
}
