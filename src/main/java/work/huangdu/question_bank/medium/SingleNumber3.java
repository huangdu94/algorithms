package work.huangdu.question_bank.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 260. 只出现一次的数字 III
 * 给定一个整数数组nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按任意顺序返回答案。
 * 进阶：你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？
 * 示例 1：
 * 输入：nums = [1,2,1,3,2,5]
 * 输出：[3,5]
 * 解释：[5, 3] 也是有效的答案。
 * 示例 2：
 * 输入：nums = [-1,0]
 * 输出：[-1,0]
 * 示例 3：
 * 输入：nums = [0,1]
 * 输出：[1,0]
 * 提示：
 * 2 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 除两个只出现一次的整数外，nums 中的其他数字都出现两次
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/2
 * @see work.huangdu.exploration.primary_algorithms.array.SingleNumber
 * @see work.huangdu.question_bank.medium.SingleNumber2
 */
public class SingleNumber3 {
    // TODO 不使用HashSet的写法
    public int[] singleNumber(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        int result = 0;
        for (int num : nums) {
            set.add(num);
            result ^= num;
        }
        for (int num : set) {
            if (set.contains(result ^ num)) {
                return new int[] {num, result ^ num};
            }
        }
        return null;
    }
}
