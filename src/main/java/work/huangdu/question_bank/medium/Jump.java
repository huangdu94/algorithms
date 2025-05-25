package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 45. 跳跃游戏 II
 * 给定一个非负整数数组，你最初位于数组的第一个位置。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
 * 假设你总是可以到达数组的最后一个位置。
 * 示例 1:
 * 输入: [2,3,1,1,4]
 * 输出: 2
 * 解释: 跳到最后一个位置的最小跳跃数是 2。
 * 从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 * 示例 2:
 * 输入: [2,3,0,1,4]
 * 输出: 2
 * 提示:
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 10^5
 *
 * @author huangdu
 * @version 2021/5/11
 */
public class Jump {
    public int jump(int[] nums) {
        int n = nums.length, far = 0, ans = 0;
        for (int i = 0; i < n && far < n; i++) {
            if (i == far) {
                ans++;
            }
            far = Math.max(far, i + nums[i]);
        }
        return ans;
    }

    public int jump2(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int start = 0; start < n; start++) {
            int step = nums[start];
            for (int pos = start + 1; pos <= start + step && pos < n; pos++) {
                dp[pos] = Math.min(dp[pos], dp[start] + 1);
            }
        }
        return dp[n - 1];
    }

    public int jump3(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2 + nums[o2], o1 + nums[o1]));
        pq.offer(0);
        int ans = 1;
        while (true) {
            int start = pq.remove();
            if (start + nums[start] >= n - 1) {
                return ans;
            }
            pq.clear();
            for (int idx = start + 1; idx <= start + nums[start]; idx++) {
                pq.offer(idx);
            }
            ans++;
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        System.out.println(new Jump().jump(nums));
    }
}
