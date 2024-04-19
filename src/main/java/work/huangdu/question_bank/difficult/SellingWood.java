package work.huangdu.question_bank.difficult;

/**
 * 2312. 卖木头块
 * 给你两个整数 m 和 n ，分别表示一块矩形木块的高和宽。同时给你一个二维整数数组 prices ，其中 prices[i] = [h_i, w_i, price_i] 表示你可以以 price_i 元的价格卖一块高为 h_i 宽为 w_i 的矩形木块。
 * 每一次操作中，你必须按下述方式之一执行切割操作，以得到两块更小的矩形木块：
 * 沿垂直方向按高度 完全 切割木块，或
 * 沿水平方向按宽度 完全 切割木块
 * 在将一块木块切成若干小木块后，你可以根据 prices 卖木块。你可以卖多块同样尺寸的木块。你不需要将所有小木块都卖出去。你 不能 旋转切好后木块来交换它的高度值和宽度值。
 * 请你返回切割一块大小为 m x n 的木块后，能得到的 最多 钱数。
 * 注意你可以切割木块任意次。
 * 示例 1：
 * 输入：m = 3, n = 5, prices = [[1,4,2],[2,2,7],[2,1,3]]
 * 输出：19
 * 解释：上图展示了一个可行的方案。包括：
 * - 2 块 2 x 2 的小木块，售出 2 * 7 = 14 元。
 * - 1 块 2 x 1 的小木块，售出 1 * 3 = 3 元。
 * - 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
 * 总共售出 14 + 3 + 2 = 19 元。
 * 19 元是最多能得到的钱数。
 * 示例 2：
 * 输入：m = 4, n = 6, prices = [[3,2,10],[1,4,2],[4,1,3]]
 * 输出：32
 * 解释：上图展示了一个可行的方案。包括：
 * - 3 块 3 x 2 的小木块，售出 3 * 10 = 30 元。
 * - 1 块 1 x 4 的小木块，售出 1 * 2 = 2 元。
 * 总共售出 30 + 2 = 32 元。
 * 32 元是最多能得到的钱数。
 * 注意我们不能旋转 1 x 4 的木块来得到 4 x 1 的木块。
 * 提示：
 * 1 <= m, n <= 200
 * 1 <= prices.length <= 2 * 10^4
 * prices[i].length == 3
 * 1 <= h_i <= m
 * 1 <= w_i <= n
 * 1 <= price_i <= 10^6
 * 所有 (h_i, w_i) 互不相同 。
 *
 * @author huangdu
 */
public class SellingWood {
    public long sellingWood(int m, int n, int[][] prices) {
        int[][] priceMap = new int[m + 1][n + 1];
        for (int[] price : prices) {
            priceMap[price[0]][price[1]] = price[2];
        }
        long[][] dp = new long[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                long max = priceMap[i][j];
                for (int k = 1; k <= j - 1; k++) {
                    max = Math.max(max, dp[i][k] + dp[i][j - k]);
                }
                for (int k = 1; k <= i - 1; k++) {
                    max = Math.max(max, dp[k][j] + dp[i - k][j]);
                }
                dp[i][j] = max;
            }
        }
        return dp[m][n];
    }
}
