package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 368. 最大整除子集
 * 给你一个由 无重复 正整数组成的集合 nums ，请你找出并返回其中最大的整除子集 answer ，子集中每一元素对 (answer[i], answer[j]) 都应当满足：
 * answer[i] % answer[j] == 0 ，或
 * answer[j] % answer[i] == 0
 * 如果存在多个有效解子集，返回其中任何一个均可。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,2]
 * 解释：[1,3] 也会被视为正确答案。
 * 示例 2：
 * 输入：nums = [1,2,4,8]
 * 输出：[1,2,4,8]
 * 提示：
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 2 * 10^9
 * nums 中的所有整数 互不相同
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/4/23
 */
public class LargestDivisibleSubset {
    private int max;
    private int n;
    private int[] nums;
    private List<Integer> result;
    private List<Integer> branch;

    public List<Integer> largestDivisibleSubset2(int[] _nums) {
        Arrays.sort(_nums);
        max = 0;
        n = _nums.length;
        nums = _nums;
        branch = new ArrayList<>(n);
        if (nums[0] == 1) {
            branch.add(1);
            helper(1);
        } else {
            helper(0);
        }
        return result;
    }

    private void helper(int index) {
        if (n - index + branch.size() < max) {
            return;
        }
        for (int i = index; i < n; i++) {
            int num = nums[i];
            if (branch.size() == 0 || num % branch.get(branch.size() - 1) == 0) {
                branch.add(num);
                helper(i + 1);
                branch.remove(branch.size() - 1);
            }
        }
        if (branch.size() > max) {
            result = new ArrayList<>(branch);
            max = branch.size();
        }
    }

    public static void main(String[] args) {
        LargestDivisibleSubset lds = new LargestDivisibleSubset();
        //        int[] nums = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144, 524288, 1048576, 2097152, 4194304, 8388608, 16777216, 33554432, 67108864,
        //                134217728, 268435456, 536870912, 1073741824};
        int[] nums = {2, 4};
        System.out.println(lds.largestDivisibleSubset(nums));
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length, maxIndex = 0;
        Arrays.sort(nums);
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                if (dp[i] > dp[maxIndex]) {
                    maxIndex = i;
                }
            }
        }
        int curSize = dp[maxIndex], curVal = nums[maxIndex];
        List<Integer> result = new ArrayList<>(curSize);
        for (int i = maxIndex; i >= 0; i--) {
            if (dp[i] == curSize && curVal % nums[i] == 0) {
                result.add(nums[i]);
                curVal = nums[i];
                curSize--;
            }
        }
        return result;
    }
}
