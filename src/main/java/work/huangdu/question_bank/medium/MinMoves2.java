package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 462. 最少移动次数使数组元素相等 II
 * 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：2
 * 解释：
 * 只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
 * [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 * 示例 2：
 * 输入：nums = [1,10,2,9]
 * 输出：16
 * 提示：
 * n == nums.length
 * 1 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2022/5/19
 */
public class MinMoves2 {
    public int minMoves2(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        List<Integer> numList = new ArrayList<>(numSet);
        numList.sort(Integer::compare);
        int left = 0, right = numList.size() - 1;
        while (left < right) {
            int mid = left + ((right - left) >> 1), num1 = numList.get(mid), num2 = numList.get(mid + 1);
            long cnt1 = count(nums, num1), cnt2 = count(nums, num2);
            if (cnt1 < cnt2) {
                right = mid;
            } else if (cnt1 > cnt2) {
                left = mid + 1;
            } else {
                left = mid;
                break;
            }
        }
        return (int)count(nums, numList.get(left));
    }

    private long count(int[] nums, int target) {
        long cnt = 0;
        for (int num : nums) {
            cnt += Math.abs(num - target);
        }
        return cnt;
    }
}
