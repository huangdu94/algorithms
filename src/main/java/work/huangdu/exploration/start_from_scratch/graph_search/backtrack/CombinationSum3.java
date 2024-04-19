package work.huangdu.exploration.start_from_scratch.graph_search.backtrack;

import java.util.ArrayList;
import java.util.List;

import work.huangdu.question_bank.medium.CombinationSum4;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author huangdu
 * @version 2020/9/11 9:19
 * @see CombinationSum
 * @see CombinationSum2
 * @see CombinationSum4
 */
public class CombinationSum3 {
    class Solution {
        public List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            backtrack(ans, 1, 0, k, 0, n, new ArrayList<>(k));
            return ans;
        }

        private void backtrack(List<List<Integer>> ans, int num, int i, int k, int sum, int n, List<Integer> combination) {
            if (i == k) {
                if (sum == n) {ans.add(new ArrayList<>(combination));}
                return;
            }
            if (num > 9 || sum + (k - i) * 9 < sum || sum >= n) {return;}
            backtrack(ans, num + 1, i, k, sum, n, combination);
            combination.add(num);
            backtrack(ans, num + 1, i + 1, k, sum + num, n, combination);
            combination.remove(i);
        }
    }

    private int n;
    private int k;
    private List<List<Integer>> resList;
    private List<Integer> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.n = n;
        this.k = k;
        resList = new ArrayList<>();
        res = new ArrayList<>();
        helper(0, 1, 0);
        return resList;
    }

    private void helper(int count, int num, int sum) {
        if (count == k) {
            if (sum == n) {
                resList.add(new ArrayList<>(res));
            }
            return;
        }
        if (num > 9 || sum + num > n) {
            return;
        }
        helper(count, num + 1, sum);
        res.add(num);
        helper(count + 1, num + 1, sum + num);
        res.remove(count);
    }
}
