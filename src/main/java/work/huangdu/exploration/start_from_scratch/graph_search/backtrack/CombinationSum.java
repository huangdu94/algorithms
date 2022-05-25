package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import work.huangdu.question_bank.medium.CombinationSum3;
import work.huangdu.question_bank.medium.CombinationSum4;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/9 12:46
 * @see CombinationSum2
 * @see CombinationSum3
 * @see CombinationSum4
 */
public class CombinationSum {
    private int len;
    private int[] candidates;
    private int target;
    private List<List<Integer>> resList;
    private List<Integer> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.len = candidates.length;
        this.candidates = candidates;
        this.target = target;
        this.resList = new ArrayList<>();
        this.res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(0, 0);
        return resList;
    }

    private void helper(int sum, int index) {
        if (sum == target) {
            resList.add(new ArrayList<>(res));
            return;
        }
        for (int i = index; i < len; i++) {
            if (sum + candidates[i] > target) {
                break;
            }
            res.add(candidates[i]);
            helper(sum + candidates[i], i);
            res.remove(res.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum sum = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(sum.combinationSum(candidates, target));
    }

    static class Solution {
        private int n;
        private int[] candidates;
        private List<List<Integer>> combinations;
        private List<Integer> combination;

        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            Arrays.sort(candidates);
            this.n = candidates.length;
            this.candidates = candidates;
            this.combinations = new ArrayList<>();
            this.combination = new ArrayList<>();
            backtrack(0, target);
            return combinations;
        }

        private void backtrack(int index, int target) {
            if (target == 0) {
                combinations.add(new ArrayList<>(combination));
                return;
            }
            if (index == n) { return; }
            int candidate = candidates[index], max = target / candidate;
            for (int count = 1; count <= max; count++) {
                combination.add(candidate);
                backtrack(index + 1, target - candidate * count);
            }
            for (int i = 0; i < max; i++) {
                combination.remove(combination.size() - 1);
            }
            backtrack(index + 1, target);
        }
    }
}
