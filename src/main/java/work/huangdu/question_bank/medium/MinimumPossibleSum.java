package work.huangdu.question_bank.medium;

/**
 * 2834. 找出美丽数组的最小和
 * 给你两个正整数：n 和 target 。
 * 如果数组 nums 满足下述条件，则称其为 美丽数组 。
 * nums.length == n.
 * nums 由两两互不相同的正整数组成。
 * 在范围 [0, n-1] 内，不存在 两个 不同 下标 i 和 j ，使得 nums[i] + nums[j] == target 。
 * 返回符合条件的美丽数组所可能具备的 最小 和，并对结果进行取模 10^9 + 7。
 * 示例 1：
 * 输入：n = 2, target = 3
 * 输出：4
 * 解释：nums = [1,3] 是美丽数组。
 * - nums 的长度为 n = 2 。
 * - nums 由两两互不相同的正整数组成。
 * - 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
 * 可以证明 4 是符合条件的美丽数组所可能具备的最小和。
 * 示例 2：
 * 输入：n = 3, target = 3
 * 输出：8
 * 解释：
 * nums = [1,3,4] 是美丽数组。
 * - nums 的长度为 n = 3 。
 * - nums 由两两互不相同的正整数组成。
 * - 不存在两个不同下标 i 和 j ，使得 nums[i] + nums[j] == 3 。
 * 可以证明 8 是符合条件的美丽数组所可能具备的最小和。
 * 示例 3：
 * 输入：n = 1, target = 1
 * 输出：1
 * 解释：nums = [1] 是美丽数组。
 * 提示：
 * 1 <= n <= 10^9
 * 1 <= target <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MinimumPossibleSum {
    // 两部分组成。 [1,...,x] x+x+1 >= target  [target,...,n-x+target-1]
    public int minimumPossibleSum(int n, int target) {
        if (target == 1 || target > n + n - 1) {return (int)(((1L + n) * n / 2) % 1000000007);}
        int left = 0, right = target;
        while (left + 1 < right) {
            int mid = left + (right - left >> 1);
            if (mid + mid + 1 < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return (int)((((1L + right) * right / 2) + (-1L + target + target + n - right) * (n - right) / 2) % 1000000007);
    }

    public static void main(String[] args) {
        MinimumPossibleSum mps = new MinimumPossibleSum();
        // 1
        System.out.println(mps.minimumPossibleSum(2, 3));
    }
}
