package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 891. 子序列宽度之和
 * 一个序列的 宽度 定义为该序列中最大元素和最小元素的差值。
 * 给你一个整数数组 nums ，返回 nums 的所有非空 子序列 的 宽度之和 。由于答案可能非常大，请返回对 10^9 + 7 取余 后的结果。
 * 子序列 定义为从一个数组里删除一些（或者不删除）元素，但不改变剩下元素的顺序得到的数组。例如，[3,6,2,7] 就是数组 [0,3,1,6,2,2,7] 的一个子序列。
 * 示例 1：
 * 输入：nums = [2,1,3]
 * 输出：6
 * 解释：子序列为 [1], [2], [3], [2,1], [2,3], [1,3], [2,1,3] 。
 * 相应的宽度是 0, 0, 0, 1, 1, 2, 2 。
 * 宽度之和是 6 。
 * 示例 2：
 * 输入：nums = [2]
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/21
 */
public class SumSubseqWidths {
    /**
     * 数学推导
     * 1. 数组中数的顺序不影响结果，所以可以对数组先排序
     * 2. 影响一个子序列的值的数是最小值和最大值，所以对于一对i,j(i<j)
     * ∑（i<j）(nums[j]-nums[i])*2^(j-i-1)
     * ∑（i<j）nums[j]*2^(j-i-1)-∑（i<j）*nums[i]*2^(j-i-1)
     * nums[j]*(2^j-1)-∑（i<j）*nums[i]*2^(j-i-1)
     * xi=∑（i<j）*nums[i]*2^(j-i-1)
     * xi+1=∑（i<j+1）*nums[i]*2^(j-i)
     * *   =2*xj+nums[j]
     */
    public int sumSubseqWidths(int[] nums) {
        final int mod = 1000000007;
        Arrays.sort(nums);
        int n = nums.length;
        long ans = 0, xj = 0, yj = 1;
        for (int j = 1; j < n; j++) {
            yj = yj * 2 % mod;
            xj = (xj * 2 + nums[j - 1]) % mod;
            ans = (ans + (nums[j] * (yj - 1) - xj) % mod) % mod;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        SumSubseqWidths ssw = new SumSubseqWidths();
        System.out.println(ssw.sumSubseqWidths(new int[] {2, 1, 3}));
    }
}
