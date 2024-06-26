package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 *
 * @author huangdu
 * @version 2021/9/6
 */
public class Search2 {
    public int search2(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        return index < 0 ? -1 : index;
    }

    public int search(int[] nums, int target) {
        int n = nums.length, i = 0, j = n - 1;
        while (i < j) {
            int mid = i + ((j - i) >> 1);
            if (target == nums[mid]) {
                return mid;
            } else if (target > nums[mid]) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return nums[i] == target ? i : -1;
    }
}
