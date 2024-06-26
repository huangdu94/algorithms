package work.huangdu.exploration.intermediate_algorithms.sort_search;

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @author huangdu
 * @version 2020/7/12 15:49
 */
public class SearchRange {
    public int[] searchRange(int[] nums, int target) {
        int left = lowerBound(nums, target);
        if (left == nums.length || nums[left] != target) {return new int[] {-1, -1};}
        int right = lowerBound(nums, target + 1) - 1;
        return new int[] {left, right};
    }

    private int lowerBound(int[] nums, int target) {
        int left = -1, right = nums.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }

    public int[] searchRange3(int[] nums, int target) {
        if (nums == null || nums.length < 1) {return new int[] {-1, -1};}
        int l = search(nums, 0, nums.length, target, true);
        if (l == nums.length || nums[l] != target) {return new int[] {-1, -1};}
        int r = search(nums, 0, nums.length, target, false);
        return new int[] {l, r - 1};
    }

    private int search(int[] nums, int l, int r, int target, boolean left) {
        if (l == r) {return l;}
        int mid = (l + r) / 2;
        if (target < nums[mid] || left && target == nums[mid]) {return search(nums, l, mid, target, left);} else {return search(nums, mid + 1, r, target, left);}
    }

    public int[] searchRange2(int[] nums, int target) {
        if (nums == null || nums.length < 1) {return new int[] {-1, -1};}
        int l = 0;
        int r = nums.length - 1;
        int min = nums[l];
        int max = nums[r];
        if (target < min || target > max) {
            return new int[] {-1, -1};
        } else if (target == min && target == max) {
            return new int[] {l, r};
        } else if (target == min) {
            return new int[] {l, searchRight(nums, l, r, target)};
        } else if (target == max) {
            return new int[] {searchLeft(nums, l, r, target), r};
        } else {
            int start = searchLeft(nums, l, r, target);
            int end = start == -1 ? -1 : searchRight(nums, l, r, target);
            return new int[] {start, end};
        }
    }

    private int searchLeft(int[] nums, int start, int end, int target) {
        if (end - start == 1 && nums[start] < target) {if (nums[end] == target) {return end;} else if (nums[end] > target) {return -1;}}
        int mid = (start + end) / 2;
        if (nums[mid] >= target) {return searchLeft(nums, start, mid, target);} else {return searchLeft(nums, mid, end, target);}
    }

    private int searchRight(int[] nums, int start, int end, int target) {
        if (end - start == 1 && nums[end] > target) {if (nums[start] == target) {return start;} else if (nums[start] < target) {return -1;}}
        int mid = (start + end) / 2;
        if (nums[mid] > target) {return searchRight(nums, start, mid, target);} else {return searchRight(nums, mid, end, target);}
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 11;
        int[] result = new SearchRange().searchRange4(nums, target);
        System.out.println(Arrays.toString(result));
    }

    public int[] searchRange4(int[] nums, int target) {
        if (nums.length == 0) {return new int[] {-1, -1};}
        int left = binarySearch(nums, target, true);
        if (nums[left] != target) {return new int[] {-1, -1};}
        int right = binarySearch(nums, target, false);
        return new int[] {left, nums[right] == target ? right : right - 1};
    }

    /**
     * 二分查找法，找出target在nums中的位置，根据flag情况来区分查找的位置时开始还是结束位置
     *
     * @param nums   目标数组
     * @param target 目标值
     * @param flag   标识用于区分处理mid等于flag的行为
     */
    private int binarySearch(int[] nums, int target, boolean flag) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (nums[mid] > target || flag && nums[mid] == target) {
                j = mid;
            } else {
                i = mid + 1;
            }
        }
        return i;
    }
}
