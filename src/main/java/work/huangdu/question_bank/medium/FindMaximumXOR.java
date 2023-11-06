package work.huangdu.question_bank.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 421. 数组中两个数的最大异或值
 * 给你一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
 * 进阶：你可以在 O(n) 的时间解决这个问题吗？
 * 示例 1：
 * 输入：nums = [3,10,5,25,2,8]
 * 输出：28
 * 解释：最大运算结果是 5 XOR 25 = 28.
 * 示例 2：
 * 输入：nums = [0]
 * 输出：0
 * 示例 3：
 * 输入：nums = [2,4]
 * 输出：6
 * 示例 4：
 * 输入：nums = [8,10,2]
 * 输出：10
 * 示例 5：
 * 输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
 * 输出：127
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * 0 <= nums[i] <= 2^31 - 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/16
 */
public class FindMaximumXOR {
    public int findMaximumXOR(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        int n = 0;
        while (max != 0) {
            max >>= 1;
            n++;
        }
        int ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = n - 1, mask = 0, prefix = 0; i >= 0; i--) {
            mask |= 1 << i;
            prefix |= 1 << i;
            set.clear();
            for (int num : nums) {
                num = num & mask;
                if (set.contains(prefix ^ num)) {
                    ans |= 1 << i;
                    break;
                }
                set.add(num);
            }
            prefix = ans;
        }
        return ans;
    }

    // 超时
    public int findMaximumXOR2(int[] nums) {
        int max = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                max = Math.max(max, nums[i] ^ nums[j]);
            }
        }
        return max;
    }
}
