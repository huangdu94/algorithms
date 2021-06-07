package work.huangdu.question_bank.medium;

/**
 * 494. 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * 示例 1：
 * 输入：nums = [1,1,1,1,1], target = 3
 * 输出：5
 * 解释：一共有 5 种方法让最终目标和为 3 。
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * 示例 2：
 * 输入：nums = [1], target = 1
 * 输出：1
 * 提示：
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 100
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/6/7
 */
public class FindTargetSumWays {
    private int n;
    private int[] nums;
    private int target;
    private int count;

    public int findTargetSumWays2(int[] nums, int target) {
        this.n = nums.length;
        this.nums = nums;
        this.target = target;
        this.count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        helper(0, sum);
        return count;
    }

    private void helper(int index, int sum) {
        if (target > sum) {
            return;
        }
        if (index == n) {
            if (sum == target) {
                count++;
            }
            return;
        }
        helper(index + 1, sum - 2 * nums[index]);
        helper(index + 1, sum);
    }

    public static void main(String[] args) {
        int[] nums = {1, 0};
        int target = 1;
        FindTargetSumWays ftsw = new FindTargetSumWays();
        System.out.println(ftsw.findTargetSumWays(nums, target));
    }

    // TODO 需要复习 动态规划.
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || (diff & 1) == 1) {
            return 0;
        }
        int neg = (sum - target) / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = neg; i >= num; i--) {
                dp[i] += dp[i - num];
            }
        }
        return dp[neg];
    }
}
