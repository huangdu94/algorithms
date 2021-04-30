package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 137. 只出现一次的数字 II
 * 给你一个整数数组 nums ，除某个元素仅出现一次外，其余每个元素都恰出现三次。请你找出并返回那个只出现了一次的元素。
 * 示例 1：
 * 输入：nums = [2,2,3,2]
 * 输出：3
 * 示例 2：
 * 输入：nums = [0,1,0,1,0,1,99]
 * 输出：99
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * nums中，除某个元素仅出现一次外，其余每个元素都恰出现三次
 * 进阶：你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/4/30
 */
public class SingleNumber {
    // TODO
    // 方法二：依次确定每一个二进制位
    // 方法三：数字电路设计
    // 方法四：数字电路设计优化
    public int singleNumber(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            count.merge(num, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }
}
