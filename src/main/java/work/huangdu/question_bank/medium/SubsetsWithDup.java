package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 * 示例 1：
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/31
 */
public class SubsetsWithDup {
    private int[] nums;
    private int n;
    private List<List<Integer>> subsets;
    private List<Integer> subset;

    public List<List<Integer>> subsetsWithDup(int[] _nums) {
        nums = _nums;
        n = nums.length;
        subsets = new ArrayList<>(1 << n);
        subset = new ArrayList<>(n);
        Arrays.sort(nums);
        backtrack(0);
        return subsets;
    }

    private void backtrack(int start) {
        if (start == n) {
            subsets.add(new ArrayList<>(subset));
            return;
        }
        backtrack(start + 1);
        int end = start;
        while (end < n && nums[end] == nums[start]) {
            subset.add(nums[end++]);
        }
        backtrack(end);
        while (end-- != start) {
            subset.remove(subset.size() - 1);
        }
    }

    public static void main(String[] args) {
        SubsetsWithDup swd = new SubsetsWithDup();
        int[] nums = {1, 2, 2};
        System.out.println(swd.subsetsWithDup(nums));
    }
}
