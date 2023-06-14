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
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/6/14
 */
public class TilingRectangle {
    private int n;
    private int m;
    private int area;
    private boolean[][] fill;
    private int ans;

    public int tilingRectangle(int n, int m) {
        this.n = n;
        this.m = m;
        this.area = n * m;
        this.ans = area;
        this.fill = new boolean[n][m];
        backtrack(0, 0);
        return ans;
    }

    private void backtrack(int total, int cnt) {
        if (cnt >= ans) {return;}
        if (total == area) {
            ans = cnt;
            return;
        }
        int[] coords = find();
        int i0 = coords[0], j0 = coords[1];
        for (int k = Math.min(n - i0, m - j0); k > 0; k--) {
            int i1 = i0 + k, j1 = j0 + k;
            if (check(i0, j0, i1, j1)) {
                fill(i0, j0, i1, j1, true);
                backtrack(total + k * k, cnt + 1);
                fill(i0, j0, i1, j1, false);
            }
        }
    }

    private boolean check(int i0, int j0, int i1, int j1) {
        for (int i = i0; i < i1; i++) {
            for (int j = j0; j < j1; j++) {
                if (fill[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void fill(int i0, int j0, int i1, int j1, boolean value) {
        for (int i = i0; i < i1; i++) {
            for (int j = j0; j < j1; j++) {
                fill[i][j] = value;
            }
        }
    }

    private int[] find() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!fill[i][j]) {
                    return new int[] {i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TilingRectangle tr = new TilingRectangle();
        System.out.println(tr.tilingRectangle(2, 3));
    }
}
