package work.huangdu.question_bank.difficult;

/**
 * 1330. 翻转子数组得到最大的数组值
 * 给你一个整数数组 nums 。「数组值」定义为所有满足 0 <= i < nums.length-1 的 |nums[i]-nums[i+1]| 的和。
 * 你可以选择给定数组的任意子数组，并将该子数组翻转。但你只能执行这个操作 一次 。
 * 请你找到可行的最大 数组值 。
 * 示例 1：
 * 输入：nums = [2,3,1,5,4]
 * 输出：10
 * 解释：通过翻转子数组 [3,1,5] ，数组变成 [2,5,1,3,4] ，数组值为 10 。
 * 示例 2：
 * 输入：nums = [2,4,9,24,2,1,10]
 * 输出：68
 * 提示：
 * 1 <= nums.length <= 3*10^4
 * -10^5 <= nums[i] <= 10^5
 *
 * @author huangdu
 * @version 2023/5/12
 */
public class MaxValueAfterReverse {
    // TODO 数学推导
    public int maxValueAfterReverse2(int[] nums) {
        int base = 0, d = 0, n = nums.length;
        int mx = Integer.MIN_VALUE, mn = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int a = nums[i - 1], b = nums[i];
            int dab = Math.abs(a - b);
            base += dab;
            mx = Math.max(mx, Math.min(a, b));
            mn = Math.min(mn, Math.max(a, b));
            d = Math.max(d, Math.max(Math.abs(nums[0] - b) - dab, // i=0
                Math.abs(nums[n - 1] - a) - dab)); // j=n-1
        }
        return base + Math.max(d, 2 * (mx - mn));
    }
}
