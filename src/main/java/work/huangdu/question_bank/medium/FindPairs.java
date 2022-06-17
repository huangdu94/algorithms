package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 532. 数组中的 k-diff 数对
 * 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 * 示例 1：
 * 输入：nums = [3, 1, 4, 1, 5], k = 2
 * 输出：2
 * 解释：数组中有两个 2-diff 数对, (1, 3) 和 (3, 5)。
 * 尽管数组中有两个 1 ，但我们只应返回不同的数对的数量。
 * 示例 2：
 * 输入：nums = [1, 2, 3, 4, 5], k = 1
 * 输出：4
 * 解释：数组中有四个 1-diff 数对, (1, 2), (2, 3), (3, 4) 和 (4, 5) 。
 * 示例 3：
 * 输入：nums = [1, 3, 1, 5, 4], k = 0
 * 输出：1
 * 解释：数组中只有一个 0-diff 数对，(1, 1) 。
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^7 <= nums[i] <= 10^7
 * 0 <= k <= 10^7
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2022/6/16
 */
public class FindPairs {
    public int findPairs(int[] nums, int k) {
        if (k == 0) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int num : nums) {
                countMap.merge(num, 1, Integer::sum);
            }
            int ans = 0;
            for (int count : countMap.values()) {
                if (count >= 2) {
                    ans++;
                }
            }
            return ans;
        }
        Set<Integer> numSet = new HashSet<>(nums.length);
        for (int num : nums) {
            numSet.add(num);
        }
        int ans = 0;
        for (Integer num : numSet) {
            if (numSet.contains(num + k)) {
                ans++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        FindPairs fp = new FindPairs();
        int[] nums = {3, 1, 4, 1, 5};
        int k = 2;
        System.out.println(fp.findPairs(nums, k));
    }
}
