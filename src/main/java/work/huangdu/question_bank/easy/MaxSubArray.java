package work.huangdu.question_bank.easy;

/**
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * 示例1:
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/7/17
 * @see work.huangdu.exploration.primary_algorithms.dynamic.MaxSubArray
 */
public class MaxSubArray {
    public int maxSubArray(int[] nums) {
        int prefixSumMin = 0, prefixSum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            prefixSum += num;
            max = Math.max(max, prefixSum - prefixSumMin);
            prefixSumMin = Math.min(prefixSumMin, prefixSum);
        }
        return max;
    }

    public static void main(String[] args) {
        MaxSubArray msa = new MaxSubArray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(msa.maxSubArray(nums));
    }
}
