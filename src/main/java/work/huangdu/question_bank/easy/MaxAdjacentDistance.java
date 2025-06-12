package work.huangdu.question_bank.easy;

/**
 * 3423. 循环数组中相邻元素的最大差值
 * MaxAdjacentDistance
 *
 * @author huangdu
 * @version 2025/6/12
 */
public class MaxAdjacentDistance {
    public int maxAdjacentDistance(int[] nums) {
        int n = nums.length;
        int ans = Math.abs(nums[0] - nums[n - 1]);
        for (int i = 1; i < n; i++) {
            ans = Math.max(ans, Math.abs(nums[i] - nums[i - 1]));
        }
        return ans;
    }
}
