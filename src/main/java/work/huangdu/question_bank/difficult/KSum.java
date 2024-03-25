package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 2386. 找出数组的第 K 大和
 * 给你一个整数数组 nums 和一个 正 整数 k 。你可以选择数组的任一 子序列 并且对其全部元素求和。
 * 数组的 第 k 大和 定义为：可以获得的第 k 个 最大 子序列和（子序列和允许出现重复）
 * 返回数组的 第 k 大和 。
 * 子序列是一个可以由其他数组删除某些或不删除元素派生而来的数组，且派生过程不改变剩余元素的顺序。
 * 注意：空子序列的和视作 0 。
 * 示例 1：
 * 输入：nums = [2,4,-2], k = 5
 * 输出：2
 * 解释：所有可能获得的子序列和列出如下，按递减顺序排列：
 * - 6、4、4、2、2、0、0、-2
 * 数组的第 5 大和是 2 。
 * 示例 2：
 * 输入：nums = [1,-2,3,4,-10,12], k = 16
 * 输出：10
 * 解释：数组的第 16 大和是 10 。
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * 1 <= k <= min(2000, 2^n)
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class KSum {
    public long kSum(int[] nums, int k) {
        int n = nums.length;
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            } else {
                sum += nums[i];
            }
        }
        if (k == 1) {return sum;}
        Arrays.sort(nums);
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[0]));
        pq.offer(new long[] {nums[0], 0});
        while (--k > 1) {
            long[] data = pq.poll();
            long total = data[0];
            int idx = (int)data[1];
            if (idx + 1 < n) {
                pq.offer(new long[] {total - nums[idx] + nums[idx + 1], idx + 1});
                pq.offer(new long[] {total + nums[idx + 1], idx + 1});
            }
        }
        return sum - pq.peek()[0];
    }
}
