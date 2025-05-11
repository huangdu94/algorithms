package work.huangdu.question_bank.difficult;

/**
 * 1240. 铺瓷砖
 * 你是一位施工队的工长，根据设计师的要求准备为一套设计风格独特的房子进行室内装修。
 * 房子的客厅大小为 n x m，为保持极简的风格，需要使用尽可能少的 正方形 瓷砖来铺盖地面。
 * 假设正方形瓷砖的规格不限，边长都是整数。
 * 请你帮设计师计算一下，最少需要用到多少块方形瓷砖？
 * 示例 1：
 * 输入：n = 2, m = 3
 * 输出：3
 * 解释：3 块地砖就可以铺满卧室。
 * 2 块 1x1 地砖
 * 1 块 2x2 地砖
 * 示例 2：
 * 输入：n = 5, m = 8
 * 输出：5
 * 示例 3：
 * 输入：n = 11, m = 13
 * 输出：6
 * 提示：
 * 1 <= n <= 13
 * 1 <= m <= 13
 *
 * @author huangdu
 * @version 2023/6/14
 */
public class TilingRectangle {
    private int n;
    private int m;
    private boolean[][] fill;
    private int ans;

    public int tilingRectangle(int n, int m) {
        this.n = n;
        this.m = m;
        this.ans = n * m;
        this.fill = new boolean[n][m];
        backtrack(0, 0, 0);
        return ans;
    }

    private void backtrack(int x, int y, int cnt) {
        if (cnt >= ans) {return;}
        if (x >= n) {
            ans = cnt;
            return;
        }
        if (y >= m) {
            backtrack(x + 1, 0, cnt);
            return;
        }
        if (fill[x][y]) {
            backtrack(x, y + 1, cnt);
            return;
        }
        int k = Math.min(n - x, m - y);
        while (!check(x, y, k)) {k--;}
        while (k > 0) {
            fill(x, y, k, true);
            backtrack(x, y + k, cnt + 1);
            fill(x, y, k, false);
            k--;
        }
    }

    private boolean check(int x, int y, int k) {
        for (int i = x + k - 1; i >= x; i--) {
            for (int j = y + k - 1; j >= y; j--) {
                if (fill[i][j]) {return false;}
            }
        }
        return true;
    }

    private void fill(int x, int y, int k, boolean value) {
        for (int i = x + k - 1; i >= x; i--) {
            for (int j = y + k - 1; j >= y; j--) {
                fill[i][j] = value;
            }
        }
    }

    public static void main(String[] args) {
        TilingRectangle tr = new TilingRectangle();
        System.out.println(tr.tilingRectangle(2, 3));
    }
}
