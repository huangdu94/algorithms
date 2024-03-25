package work.huangdu.question_bank.difficult;

/**
 * 1793. 好子数组的最大分数
 * 给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
 * 一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
 * 请你返回 好 子数组的最大可能 分数 。
 * 示例 1：
 * 输入：nums = [1,4,3,7,4,5], k = 3
 * 输出：15
 * 解释：最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
 * 示例 2：
 * 输入：nums = [5,5,4,5,4,1,1,1], k = 0
 * 输出：20
 * 解释：最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 2 * 10^4
 * 0 <= k < nums.length
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class MaximumScore {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length, left = k - 1, right = k + 1, min = nums[k], ans = min;
        while (left >= 0 || right < n) {
            while (left >= 0 && nums[left] >= min) {left--;}
            while (right < n && nums[right] >= min) {right++;}
            ans = Math.max(ans, min * (right - left - 1));
            if (left >= 0 || right < n) {
                if (right == n) {
                    min = nums[left--];
                } else if (left == -1) {
                    min = nums[right++];
                } else if (nums[left] >= nums[right]) {
                    min = nums[left--];
                } else {
                    min = nums[right++];
                }
            }
        }
        return Math.max(ans, min * (right - left - 1));
    }
}
