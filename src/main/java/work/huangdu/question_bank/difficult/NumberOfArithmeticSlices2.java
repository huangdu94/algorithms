package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.Map;

/**
 * 446. 等差数列划分 II - 子序列
 * 给你一个整数数组 nums ，返回 nums 中所有 等差子序列 的数目。
 * 如果一个序列中 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该序列为等差序列。
 * 例如，[1, 3, 5, 7, 9]、[7, 7, 7, 7] 和 [3, -1, -5, -9] 都是等差序列。
 * 再例如，[1, 1, 2, 5, 7] 不是等差序列。
 * 数组中的子序列是从数组中删除一些元素（也可能不删除）得到的一个序列。
 * 例如，[2,5,10] 是 [1,2,1,2,4,1,5,10] 的一个子序列。
 * 题目数据保证答案是一个 32-bit 整数。
 * 示例 1：
 * 输入：nums = [2,4,6,8,10]
 * 输出：7
 * 解释：所有的等差子序列为：
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 * 示例 2：
 * 输入：nums = [7,7,7,7,7]
 * 输出：16
 * 解释：数组中的任意子序列都是等差子序列。
 * 提示：
 * 1  <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/8/13
 */
public class NumberOfArithmeticSlices2 {
    // TODO 需要复习
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length, result = 0;
        Map<Long, Integer>[] maps = new HashMap[n];
        for (int i = 0; i < n; i++) {
            maps[i] = new HashMap<>();
        }
        for (int end = 0; end < n; end++) {
            for (int pre = 0; pre < end; pre++) {
                long d = (long)nums[end] - nums[pre];
                int count = maps[pre].getOrDefault(d, 0);
                result += count;
                maps[end].merge(d, count + 1, Integer::sum);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {7,7,7,7,7};
        NumberOfArithmeticSlices2 noas = new NumberOfArithmeticSlices2();
        System.out.println(noas.numberOfArithmeticSlices(nums));
    }
}
