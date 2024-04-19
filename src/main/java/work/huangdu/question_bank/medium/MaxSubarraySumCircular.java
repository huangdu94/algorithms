package work.huangdu.question_bank.medium;

/**
 * 918. 环形子数组的最大和
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * 示例 1：
 * 输入：nums = [1,-2,3,-2]
 * 输出：3
 * 解释：从子数组 [3] 得到最大和 3
 * 示例 2：
 * 输入：nums = [5,-3,5]
 * 输出：10
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
 * 示例 3：
 * 输入：nums = [3,-2,2,-3]
 * 输出：3
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
 * 提示：
 * n == nums.length
 * 1 <= n <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 *
 * @author huangdu8
 * @version 2023/7/20
 */
public class MaxSubarraySumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        int maxS = Integer.MIN_VALUE, minS = 0, sum = 0, max = 0, min = 0;
        for (int num : nums) {
            max = Math.max(max, 0) + num;
            maxS = Math.max(maxS, max);
            min = Math.min(min, 0) + num;
            minS = Math.min(minS, min);
            sum += num;
        }
        return minS == sum ? maxS : Math.max(maxS, sum - minS);
    }

    public int maxSubarraySumCircular2(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {max = Math.max(max, num);}
        if (max < 0) {return max;}
        int n = nums.length;
        int[] leftPrefix = new int[n], leftSuffix = new int[n], rightPrefix = new int[n], rightSuffix = new int[n];
        leftPrefix[0] = Math.max(nums[0], 0);
        for (int i = 1, sum = nums[0]; i < n; i++) {
            leftPrefix[i] = Math.max(leftPrefix[i - 1], sum += nums[i]);
        }
        for (int i = 0, sum = 0, min = 0; i < n; i++) {
            leftSuffix[i] = (sum += nums[i]) - (min = Math.min(min, sum));
        }
        for (int i = n - 1, sum = 0, min = 0; i >= 0; i--) {
            rightPrefix[i] = (sum += nums[i]) - (min = Math.min(min, sum));
        }
        rightSuffix[n - 1] = Math.max(nums[n - 1], 0);
        for (int i = n - 2, sum = nums[n - 1]; i >= 0; i--) {
            rightSuffix[i] = Math.max(rightSuffix[i + 1], sum += nums[i]);
        }
        int ans = rightSuffix[0];
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, leftPrefix[i - 1] + rightSuffix[i]);
            ans = Math.max(ans, leftSuffix[i - 1] + rightPrefix[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxSubarraySumCircular mssc = new MaxSubarraySumCircular();
        int[] nums = {1, -2, 3, -2};
        System.out.println(mssc.maxSubarraySumCircular(nums));
    }
}