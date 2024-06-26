package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 673. 最长递增子序列的个数
 * 给定一个未排序的整数数组，找到最长递增子序列的个数。
 * 示例 1:
 * 输入: [1,3,5,4,7]
 * 输出: 2
 * 解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
 * 示例 2:
 * 输入: [2,2,2,2,2]
 * 输出: 5
 * 解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
 * 注意: 给定的数组长度不超过 2000 并且结果一定是32位有符号整数。
 *
 * @author huangdu
 * @version 2021/9/20
 */
public class FindNumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        List<List<Integer>> dps = new ArrayList<>(), counts = new ArrayList<>();
        for (int num : nums) {
            List<Integer> dp, count;
            int insertDps = binarySearchDps(dps, num);
            int amount = 1;
            if (insertDps > 0) {
                dp = dps.get(insertDps - 1);
                count = counts.get(insertDps - 1);
                int countIndex = binarySearchDp(dp, num);
                amount = count.get(count.size() - 1) - count.get(countIndex);
            }
            if (insertDps == dps.size()) {
                dp = new ArrayList<>();
                count = new ArrayList<>();
                dps.add(dp);
                counts.add(count);
                dp.add(num);
                count.add(0);
                count.add(amount);
            } else {
                dp = dps.get(insertDps);
                dp.add(num);
                List<Integer> currentCount = counts.get(insertDps);
                currentCount.add(amount + currentCount.get(currentCount.size() - 1));
            }
        }
        List<Integer> last = counts.get(counts.size() - 1);
        return last.get(last.size() - 1);
    }

    private int binarySearchDps(List<List<Integer>> dps, int num) {
        int i = 0, j = dps.size();
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            List<Integer> dp = dps.get(mid);
            if (dp.get(dp.size() - 1) < num) {
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return i;
    }

    private int binarySearchDp(List<Integer> dp, int num) {
        int i = 0, j = dp.size();
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            if (dp.get(mid) < num) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }

    // 复制粘贴需要复习。
    public int findNumberOfLIS2(int[] nums) {
        int n = nums.length, maxLen = 0, ans = 0;
        int[] dp = new int[n];
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            dp[i] = 1;
            cnt[i] = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        cnt[i] = cnt[j]; // 重置计数
                    } else if (dp[j] + 1 == dp[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                ans = cnt[i]; // 重置计数
            } else if (dp[i] == maxLen) {
                ans += cnt[i];
            }
        }
        return ans;
    }
}
