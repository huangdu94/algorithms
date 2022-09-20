package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 698. 划分为k个相等的子集
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 示例 1：
 * 输入： nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * 输出： True
 * 说明： 有可能将其分成 4 个子集（5），（1,4），（2,3），（2,3）等于总和。
 * 示例 2:
 * 输入: nums = [1,2,3,4], k = 3
 * 输出: false
 * 提示：
 * 1 <= k <= len(nums) <= 16
 * 0 < nums[i] < 10000
 * 每个元素的频率在 [1,4] 范围内
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/20
 */
public class CanPartitionKSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {sum += num;}
        if (sum % k != 0) {return false;}
        int n = nums.length, avg = sum / k;
        if (nums[n - 1] > avg) {return false;}
        return helper(n, avg, k, nums, new boolean[n], 0, 1, new HashSet<>());
    }

    private boolean helper(int n, int avg, int k, int[] nums, boolean[] used, int partitionSum, int time, Set<Integer> memo) {
        int statusHash = Arrays.hashCode(used);
        if (memo.contains(statusHash)) {return false;}
        if (partitionSum == avg) {
            if (time == k) {return true;}
            return helper(n, avg, k, nums, used, 0, time + 1, memo);
        }
        for (int i = 0; i < n; i++) {
            if (used[i] || partitionSum + nums[i] > avg) {continue;}
            used[i] = true;
            if (helper(n, avg, k, nums, used, partitionSum + nums[i], time, memo)) {
                return true;
            }
            used[i] = false;
        }
        memo.add(statusHash);
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {3, 3, 10, 2, 6, 5, 10, 6, 8, 3, 2, 1, 6, 10, 7, 2};
        int k = 6;
        CanPartitionKSubsets cpks = new CanPartitionKSubsets();
        System.out.println(cpks.canPartitionKSubsets(nums, k));
    }
}
