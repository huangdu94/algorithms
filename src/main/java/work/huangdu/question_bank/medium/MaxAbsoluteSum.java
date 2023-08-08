package work.huangdu.question_bank.medium;

/**
 * 1749. 任意子数组和的绝对值的最大值
 * 给你一个整数数组 nums 。一个子数组 [nums_l, nums_l+1, ..., nums_r-1, nums_r] 的 和的绝对值 为 abs(nums_l + nums_l+1 + ... + nums_r-1 + nums_r) 。
 * 请你找出 nums 中 和的绝对值 最大的任意子数组（可能为空），并返回该 最大值 。
 * abs(x) 定义如下：
 * 如果 x 是负整数，那么 abs(x) = -x 。
 * 如果 x 是非负整数，那么 abs(x) = x 。
 * 示例 1：
 * 输入：nums = [1,-3,2,3,-4]
 * 输出：5
 * 解释：子数组 [2,3] 和的绝对值最大，为 abs(2+3) = abs(5) = 5 。
 * 示例 2：
 * 输入：nums = [2,-5,1,-4,3,-2]
 * 输出：8
 * 解释：子数组 [-5,1,-4] 和的绝对值最大，为 abs(-5+1-4) = abs(-8) = 8 。
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/8/8
 */
public class MaxAbsoluteSum {
    public int maxAbsoluteSum(int[] nums) {
        int sum = 0, min = 0, max = 0, ans = 0;
        for (int num : nums) {
            sum += num;
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            ans = Math.max(ans, max - min);
        }
        return ans;
    }

    public static void main(String[] args) {
        MaxAbsoluteSum mas = new MaxAbsoluteSum();
        int[] nums = {2, -1};
        System.out.println(mas.maxAbsoluteSum(nums));
    }
}
