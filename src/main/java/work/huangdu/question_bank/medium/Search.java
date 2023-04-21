package work.huangdu.question_bank.medium;

/**
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为
 * [4,5,6,6,7,0,1,2,4,4] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * 示例 1：
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 * 进阶：
 * 这是   搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/4/7
 */
public class Search {
    // 二分
    public boolean search2(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] == target) {return true;}
            if (nums[i] == nums[mid] && nums[mid] == nums[j]) {
                i++;
                j--;
                continue;
            }
            if (nums[mid] <= nums[j]) {
                if (nums[mid] < target && target <= nums[j]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            } else {
                if (nums[i] <= target && target < nums[mid]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            }
        }
        return nums[i] == target;
    }

    // 暴力
    public boolean search3(int[] nums, int target) {
        for (int num : nums) {
            if (num == target) {
                return true;
            }
        }
        return false;
    }

    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(nums, target, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left == nums.length || nums[left] != target ? -1 : left;
    }

    private boolean check(int[] nums, int target, int idx) {
        if (nums[idx] < nums[0] == target < nums[0]) {
            return nums[idx] < target;
        }
        return nums[idx] >= nums[0] && target < nums[0];
    }

    public static void main(String[] args) {
        Search search = new Search();
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1};
        int target = 2;
        System.out.println(search.search(nums, target));
    }
}
