package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 862. 和至少为 K 的最短子数组
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 * 子数组 是数组中 连续 的一部分。
 * 示例 1：
 * 输入：nums = [1], k = 1
 * 输出：1
 * 示例 2：
 * 输入：nums = [1,2], k = 4
 * 输出：-1
 * 示例 3：
 * 输入：nums = [2,-1,2], k = 3
 * 输出：3
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^5 <= nums[i] <= 10^5
 * 1 <= k <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/27
 */
public class ShortestSubarray {
    // TODO 复习
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] preSumArr = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSumArr[i + 1] = preSumArr[i] + nums[i];
        }
        int res = n + 1;
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            long curSum = preSumArr[i];
            while (!queue.isEmpty() && curSum - preSumArr[queue.peekFirst()] >= k) {
                res = Math.min(res, i - queue.pollFirst());
            }
            while (!queue.isEmpty() && preSumArr[queue.peekLast()] >= curSum) {
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        return res < n + 1 ? res : -1;
    }

    // 暴力超时
    public int shortestSubarray2(int[] nums, int k) {
        int n = nums.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + nums[i - 1];
        }
        int ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (prefix[j] - prefix[i] >= k) {
                    if (ans == -1) {
                        ans = j - i;
                    } else {
                        ans = Math.min(ans, j - i);
                    }
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        ShortestSubarray ss = new ShortestSubarray();
        System.out.println(ss.shortestSubarray(new int[] {45, 95, 97, -34, -42}, 21));
    }
}
