package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1590. 使数组和能被 P 整除
 * 给你一个正整数数组 nums，请你移除 最短 子数组（可以为 空），使得剩余元素的 和 能被 p 整除。 不允许 将整个数组都移除。
 * 请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1 。
 * 子数组 定义为原数组中连续的一组元素。
 * 示例 1：
 * 输入：nums = [3,1,4,2], p = 6
 * 输出：1
 * 解释：nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
 * 示例 2：
 * 输入：nums = [6,3,5,2], p = 9
 * 输出：2
 * 解释：我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
 * 示例 3：
 * 输入：nums = [1,2,3], p = 3
 * 输出：0
 * 解释：和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
 * 示例  4：
 * 输入：nums = [1,2,3], p = 7
 * 输出：-1
 * 解释：没有任何方案使得移除子数组后剩余元素的和被 7 整除。
 * 示例 5：
 * 输入：nums = [1000000000,1000000000,1000000000], p = 3
 * 输出：0
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= p <= 10^9
 *
 * @author huangdu
 * @version 2023/3/11
 */
public class MinSubarray {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long sum = 0;
        for (int num : nums) {sum += num;}
        if (sum < p) {return -1;}
        long remainder = sum % p;
        if (remainder == 0) {return 0;}
        int ans = n;
        long curSum = 0;
        // key：前缀和%p value：index（后面的覆盖前面的）
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        for (int i = 0; i < n; i++) {
            curSum += nums[i];
            long key = curSum % p - remainder;
            key += key < 0 ? p : 0;
            if (map.containsKey(key)) {
                ans = Math.min(ans, i - map.get(key));
            }
            map.put(curSum % p, i);
        }
        return ans == n ? -1 : ans;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 4, 2};
        int p = 6;
        MinSubarray ms = new MinSubarray();
        System.out.println(ms.minSubarray(nums, p));
    }
}
