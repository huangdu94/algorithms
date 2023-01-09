package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1658. 将 x 减到 0 的最小操作数
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 * 如果可以将 x 恰好 减到 0 ，返回 最小操作数 ；否则，返回 -1 。
 * 示例 1：
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * 示例 2：
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 * 示例 3：
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^4
 * 1 <= x <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/1/9
 */
public class Solution1658 {
    public int minOperations(int[] nums, int x) {
        Map<Integer, Integer> prefixSumMap = new HashMap<>();
        int n = nums.length, sum = 0, count = 0;
        for (int num : nums) {
            prefixSumMap.put(sum, count);
            sum += num;
            if (sum > x) {break;}
            count++;
        }
        int ans = prefixSumMap.getOrDefault(x, -1);
        sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            int num = nums[i];
            sum += num;
            if (sum > x) {break;}
            if (prefixSumMap.containsKey(x - sum)) {
                int total = prefixSumMap.get(x - sum) + n - i;
                if (total > n) {break;}
                if (ans == -1) {
                    ans = total;
                } else {
                    ans = Math.min(ans, total);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution1658 solution = new Solution1658();
        int[] nums = {3, 2, 20, 1, 1, 3};
        int x = 10;
        System.out.println(solution.minOperations(nums, x));
    }
}
