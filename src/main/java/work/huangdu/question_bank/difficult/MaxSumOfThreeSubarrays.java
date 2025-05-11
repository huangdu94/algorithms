package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 689. 三个无重叠子数组的最大和
 * 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 * 示例 1：
 * 输入：nums = [1,2,1,2,6,7,5,1], k = 2
 * 输出：[0,3,5]
 * 解释：子数组 [1, 2], [2, 6], [7, 5] 对应的起始下标为 [0, 3, 5]。
 * 也可以取 [2, 1], 但是结果 [1, 3, 5] 在字典序上更大。
 * 示例 2：
 * 输入：nums = [1,2,1,2,1,2,1,2,1], k = 2
 * 输出：[0,2,4]
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] < 2^16
 * 1 <= k <= floor(nums.length / 3)
 *
 * @author huangdu
 * @version 2021/12/8
 */
public class MaxSumOfThreeSubarrays {
    // TODO 需要复习
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length, max1 = 0, max12 = 0, max123 = 0, sum1 = 0, sum2 = 0, sum3 = 0;
        int l1 = 0, l21 = 0, l22 = k, l31 = 0, l32 = k, l33 = 2 * k;
        for (int i = 2 * k; i < n; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i - 2 * k + 1 >= k) {
                if (sum1 > max1) {
                    max1 = sum1;
                    l1 = i - 3 * k + 1;
                }
                if (max1 + sum2 > max12) {
                    max12 = max1 + sum2;
                    l21 = l1;
                    l22 = i - 2 * k + 1;
                }
                if (max12 + sum3 > max123) {
                    max123 = max12 + sum3;
                    l31 = l21;
                    l32 = l22;
                    l33 = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return new int[] {l31, l32, l33};
    }

    // 暴力法 超时
    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[nums.length + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        int l1 = n - 3 * k, l2 = n - 2 * k, l3 = n - k;
        int max = 0;
        for (int i3 = n - k; i3 >= 2 * k; i3--) {
            int sum3 = prefixSum[i3 + k] - prefixSum[i3];
            for (int i2 = i3 - k; i2 >= k; i2--) {
                int sum2 = prefixSum[i2 + k] - prefixSum[i2];
                for (int i1 = i2 - k; i1 >= 0; i1--) {
                    int sum1 = prefixSum[i1 + k] - prefixSum[i1];
                    int sum = sum1 + sum2 + sum3;
                    if (max <= sum) {
                        l1 = i1;
                        l2 = i2;
                        l3 = i3;
                        max = sum;
                    }
                }
            }
        }
        return new int[] {l1, l2, l3};
    }

    public static void main(String[] args) {
        MaxSumOfThreeSubarrays msts = new MaxSumOfThreeSubarrays();
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        int k = 2;
        System.out.println(Arrays.toString(msts.maxSumOfThreeSubarrays(nums, k)));
    }
}
