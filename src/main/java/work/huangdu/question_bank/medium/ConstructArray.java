package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 667. 优美的排列 II
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 * 示例 1：
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
 * 示例 2：
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
 * 提示：
 * 1 <= k < n <= 10^4
 *
 * @author huangdu
 * @version 2022/9/8
 */
public class ConstructArray {
    // 脑筋急转弯
    public int[] constructArray(int n, int k) {
        int[] answer = new int[n];
        int idx = 0;
        for (int i = 1; i < n - k; ++i) {
            answer[idx++] = i;
        }
        for (int i = n - k, j = n; i <= j; ++i, --j) {
            answer[idx++] = i;
            if (i != j) {
                answer[idx++] = j;
            }
        }
        return answer;
    }

    // 回溯超时
    public int[] constructArray2(int n, int k) {
        int[] ans = new int[n];
        Set<Integer> used = new HashSet<>(n), diffSet = new HashSet<>(n), memo = new HashSet<>();
        backtrack(n, k, used, diffSet, ans, 0, memo);
        return ans;
    }

    private boolean backtrack(int n, int k, Set<Integer> used, Set<Integer> diffSet, int[] ans, int idx, Set<Integer> memo) {
        if (used.size() == n) {return diffSet.size() == k;}
        int hash = Arrays.hashCode(ans);
        if (memo.contains(hash)) {return false;}
        if (diffSet.size() <= k && diffSet.size() + n - used.size() >= k) {
            for (int i = 1; i <= n; i++) {
                if (used.contains(i)) {continue;}
                used.add(i);
                if (idx > 0) {diffSet.add(Math.abs(i - ans[idx - 1]));}
                ans[idx] = i;
                if (backtrack(n, k, used, diffSet, ans, idx + 1, memo)) {return true;}
                used.remove(i);
                if (idx > 0) {diffSet.remove(Math.abs(i - ans[idx - 1]));}
                ans[idx] = 0;
            }
        }
        memo.add(hash);
        return false;
    }

    public static void main(String[] args) {
        ConstructArray ca = new ConstructArray();
        System.out.println(Arrays.toString(ca.constructArray(7872, 3377)));
    }
}
