package work.huangdu.question_bank.easy;

/**
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * @author huangdu
 * @version 2021/7/16
 * @see work.huangdu.exploration.intermediate_algorithms.sort_search.SearchRange
 */
public class Search {
    public int search2(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            }
            if (num > target) {
                break;
            }
        }
        return count;
    }
}
