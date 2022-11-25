package work.huangdu.question_bank.medium;

/**
 * 795. 区间子数组个数
 * 给你一个整数数组 nums 和两个整数：left 及 right 。找出 nums 中连续、非空且其中最大元素在范围 [left, right] 内的子数组，并返回满足条件的子数组的个数。
 * 生成的测试用例保证结果符合 32-bit 整数范围。
 * 示例 1：
 * 输入：nums = [2,1,4,3], left = 2, right = 3
 * 输出：3
 * 解释：满足条件的三个子数组：[2], [2, 1], [3]
 * 示例 2：
 * 输入：nums = [2,9,2,5,6], left = 2, right = 8
 * 输出：7
 * 提示：
 * 1 <= nums.length <= 10^5
 * 0 <= nums[i] <= 10^9
 * 0 <= left <= right <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/24
 */
public class NumSubarrayBoundedMax {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int pre = 0, i = 0, n = nums.length;
        long ans = 0;
        while (i < n) {
            while (i < n && nums[i] <= right) {
                int mark = i;
                while (i < n && nums[i] < left) {
                    i++;
                }
                ans -= (long)(i - mark + 1) * (i - mark) / 2;
                if (i < n && nums[i] <= right) {
                    i++;
                }
            }
            ans += (long)(i - pre + 1) * (i - pre) / 2;
            pre = ++i;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        NumSubarrayBoundedMax nsbm = new NumSubarrayBoundedMax();
        System.out.println(nsbm.numSubarrayBoundedMax(new int[] {16, 69, 88, 85, 79, 87, 37, 33, 39, 34}, 55, 57));
    }
}
