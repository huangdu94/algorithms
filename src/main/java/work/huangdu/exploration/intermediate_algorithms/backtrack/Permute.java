package work.huangdu.exploration.intermediate_algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 46. 全排列
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 * @author huangdu
 * @version 2020/7/9 0:21
 */
public class Permute {
    static class Solution {
        private int n;
        private List<List<Integer>> permutations;
        private List<Integer> permutation;

        public List<List<Integer>> permute(int[] nums) {
            this.n = nums.length;
            this.permutations = new ArrayList<>();
            this.permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            backtrack(0);
            return this.permutations;
        }

        private void backtrack(int i) {
            if (i == n) {
                permutations.add(new ArrayList<>(permutation));
                return;
            }
            backtrack(i + 1);
            for (int j = i + 1; j < n; j++) {
                swap(i, j);
                backtrack(i + 1);
                swap(i, j);
            }
        }

        private void swap(int i, int j) {
            int temp = permutation.get(i);
            permutation.set(i, permutation.get(j));
            permutation.set(j, temp);
        }

        public static void main(String[] args) {
            int[] nums = {1, 2, 3};
            Solution permute = new Solution();
            System.out.println(permute.permute(nums));
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1) { return result; }
        this.backtrack(nums, 0, result, new ArrayList<>());
        //this.backtrack1(nums, new boolean[nums.length], result, new ArrayList<>());
        //this.backtrack2(nums, new boolean[nums.length], result, new ArrayList<>());
        //List<Integer> numList = new ArrayList<>();
        //for (int n : nums) numList.add(n);
        //this.backtrack3(numList, result, new ArrayList<>());
        return result;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> resultList, List<Integer> permute) {
        if (index == nums.length) {
            resultList.add(new ArrayList<>(permute));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            permute.add(nums[i]);
            swap(nums, index, i);
            backtrack(nums, index + 1, resultList, permute);
            permute.remove(index);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    /**
     * 回溯算法实现
     *
     * @param nums       数字字符数组
     * @param used       是否使用过
     * @param resultList 结果list
     * @param permute    一个排序list
     */
    private void backtrack1(int[] nums, boolean[] used, List<List<Integer>> resultList, List<Integer> permute) {
        boolean flag = false;
        for (boolean b : used) {
            if (!b) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                boolean[] used_copy = Arrays.copyOf(used, used.length);
                used_copy[i] = true;
                List<Integer> permute_copy = new ArrayList<>(permute);
                permute_copy.add(nums[i]);
                this.backtrack1(nums, used_copy, resultList, permute_copy);
            }
        } else {
            resultList.add(permute);
        }
    }

    /**
     * 回溯算法实现
     *
     * @param nums       数字字符数组
     * @param used       是否使用过
     * @param resultList 结果list
     * @param permute    一个排序list
     */
    private void backtrack2(int[] nums, boolean[] used, List<List<Integer>> resultList, List<Integer> permute) {
        if (permute.size() == nums.length) {
            resultList.add(new ArrayList<>(permute));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            permute.add(nums[i]);
            this.backtrack2(nums, used, resultList, permute);
            used[i] = false;
            permute.remove(permute.size() - 1);
        }
    }

    /**
     * 回溯算法实现
     *
     * @param numList    数字List
     * @param resultList 结果list
     * @param permute    一个排序list
     */
    private void backtrack3(List<Integer> numList, List<List<Integer>> resultList, List<Integer> permute) {
        if (numList.size() == 0) {
            resultList.add(new ArrayList<>(permute));
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            permute.add(numList.get(i));
            int element = numList.remove(i);
            this.backtrack3(numList, resultList, permute);
            permute.remove(permute.size() - 1);
            numList.add(i, element);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        long start = System.currentTimeMillis();
        List<List<Integer>> result = null;
        result = new Permute().permute(nums);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
