package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 2342. 数位和相等数对的最大和
 * 给你一个下标从 0 开始的数组 nums ，数组中的元素都是 正 整数。请你选出两个下标 i 和 j（i != j），且 nums[i] 的数位和 与  nums[j] 的数位和相等。
 * 请你找出所有满足条件的下标 i 和 j ，找出并返回 nums[i] + nums[j] 可以得到的 最大值 。
 * 示例 1：
 * 输入：nums = [18,43,36,13,7]
 * 输出：54
 * 解释：满足条件的数对 (i, j) 为：
 * - (0, 2) ，两个数字的数位和都是 9 ，相加得到 18 + 36 = 54 。
 * - (1, 4) ，两个数字的数位和都是 7 ，相加得到 43 + 7 = 50 。
 * 所以可以获得的最大和是 54 。
 * 示例 2：
 * 输入：nums = [10,12,19,14]
 * 输出：-1
 * 解释：不存在满足条件的数对，返回 -1 。
 * 提示：
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 *
 * @author huangdu
 */
public class MaximumSum2 {
    public int maximumSum(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int num : nums) {
            int sum = sum(num);
            int[] value = map.computeIfAbsent(sum, k -> new int[] {-1, -1});
            if (value[0] <= num) {
                value[1] = value[0];
                value[0] = num;
            } else if (value[1] < num) {
                value[1] = num;
            }
        }
        int ans = -1;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            if (entry.getValue()[1] == -1) {continue;}
            ans = Math.max(ans, entry.getValue()[0] + entry.getValue()[1]);
        }
        return ans;
    }

    private int sum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
}
