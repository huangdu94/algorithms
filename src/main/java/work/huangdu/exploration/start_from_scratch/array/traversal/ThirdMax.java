package work.huangdu.exploration.start_from_scratch.array.traversal;

/**
 * 414. 第三大的数
 * 414. 第三大的数
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 * 提示：
 * 1 <= nums.length <= 10^4
 * -2^31 <= nums[i] <= 2^31 - 1
 * 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
 *
 * @author huangdu
 * @version 2020/9/13 14:32
 */
public class ThirdMax {
    public int thirdMax(int[] nums) {
        Integer first = null, second = null, third = null;
        for (int num : nums) {
            if (first == null || first < num) {
                third = second;
                second = first;
                first = num;
            } else if (first == num) {
            } else if (second == null || second < num) {
                third = second;
                second = num;
            } else if (second == num) {
            } else if (third == null || third < num) {
                third = num;
            }
        }
        return third == null ? first : third;
    }

    public int thirdMax2(int[] nums) {
        long max = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (max < num) {
                third = second;
                second = max;
                max = num;
            } else if (max == num) {
            } else if (second < num) {
                third = second;
                second = num;
            } else if (second == num) {
            } else if (third < num) {
                third = num;
            }
        }
        return (int)(third == Long.MIN_VALUE ? max : third);
    }
}
