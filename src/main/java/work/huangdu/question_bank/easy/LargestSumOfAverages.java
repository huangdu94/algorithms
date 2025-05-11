package work.huangdu.question_bank.easy;

/**
 * 813. 最大平均值和的分组
 * 给定数组 nums 和一个整数 k 。我们将给定的数组 nums 分成 最多 k 个相邻的非空子数组 。 分数 由每个子数组内的平均值的总和构成。
 * 注意我们必须使用 nums 数组中的每一个数进行分组，并且分数不一定需要是整数。
 * 返回我们所能得到的最大 分数 是多少。答案误差在 10-6 内被视为是正确的。
 * 示例 1:
 * 输入: nums = [9,1,2,3,9], k = 3
 * 输出: 20.00000
 * 解释:
 * nums 的最优分组是[9], [1, 2, 3], [9]. 得到的分数是 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * 我们也可以把 nums 分成[9, 1], [2], [3, 9].
 * 这样的分组得到的分数为 5 + 2 + 6 = 13, 但不是最大值.
 * 示例 2:
 * 输入: nums = [1,2,3,4,5,6,7], k = 4
 * 输出: 20.50000
 * 提示:
 * 1 <= nums.length <= 100
 * 1 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * @author huangdu
 * @version 2022/11/28
 */
public class LargestSumOfAverages {
    // TODO 复习动态规划
    public double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        // 0,1,...,n-1 个数 分成 k 组 最大的平均值和
        double[][] dp = new double[n + 1][k + 1];
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        for (int i = 1; i <= n; i++) {
            dp[i][1] = (double)prefixSum[i] / i;
        }
        for (int j = 2; j <= k; j++) {
            // dp[i][j] = dp[m][j-1]  (m >= j-1) + m到n 的平均数
            for (int i = j; i <= n; i++) {
                double max = 0;
                for (int m = j - 1; m < i; m++) {
                    max = Math.max(max, dp[m][j - 1] + (double)(prefixSum[i] - prefixSum[m]) / (i - m));
                }
                dp[i][j] = max;
            }
        }
        return dp[n][k];
    }
}
