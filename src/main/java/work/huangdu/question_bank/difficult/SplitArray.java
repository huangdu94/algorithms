package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 410. 分割数组的最大值
 * 给定一个非负整数数组 nums 和一个整数 k ，你需要将这个数组分成 k 个非空的连续子数组。
 * 设计一个算法使得这 k 个子数组各自和的最大值最小。
 * 示例 1：
 * 输入：nums = [7,2,5,10,8], k = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。
 * 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * 示例 2：
 * 输入：nums = [1,2,3,4,5], k = 2
 * 输出：9
 * 示例 3：
 * 输入：nums = [1,4,4], k = 3
 * 输出：4
 * 提示：
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^6
 * 1 <= k <= min(50, nums.length)
 *
 * @author huangdu
 */
public class SplitArray {
    public int splitArray(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        // 数组 的 前i个数，分成k组，分组和最大的最小值
        int[][] dp = new int[n + 1][k + 1];
        for (int[] row : dp) {Arrays.fill(row, Integer.MAX_VALUE);}
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = Math.min(k, i); j > 0; j--) {
                for (int q = j - 1; q < i; q++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[q][j - 1], prefixSum[i] - prefixSum[q]));
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        SplitArray solution = new SplitArray();
        System.out.println(solution.splitArray(nums, 2));
    }
}
