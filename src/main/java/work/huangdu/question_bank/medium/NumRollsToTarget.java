package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 1155. 掷骰子等于目标和的方法数
 * 这里有 n 个一样的骰子，每个骰子上都有 k 个面，分别标号为 1 到 k 。
 * 给定三个整数 n ,  k 和 target ，返回可能的方式(从总共 k^n 种方式中)滚动骰子的数量，使正面朝上的数字之和等于 target 。
 * 答案可能很大，你需要对 10^9 + 7 取模 。
 * 示例 1：
 * 输入：n = 1, k = 6, target = 3
 * 输出：1
 * 解释：你扔一个有 6 个面的骰子。
 * 得到 3 的和只有一种方法。
 * 示例 2：
 * 输入：n = 2, k = 6, target = 7
 * 输出：6
 * 解释：你扔两个骰子，每个骰子有 6 个面。
 * 得到 7 的和有 6 种方法：1+6 2+5 3+4 4+3 5+2 6+1。
 * 示例 3：
 * 输入：n = 30, k = 30, target = 500
 * 输出：222616187
 * 解释：返回的结果必须是对 10^9 + 7 取模。
 * 提示：
 * 1 <= n, k <= 30
 * 1 <= target <= 1000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class NumRollsToTarget {
    private int k;
    private int[][] memo;

    public int numRollsToTarget(int n, int k, int target) {
        if (n > target || n * k < target) {return 0;}
        this.k = k;
        this.memo = new int[n + 1][target + 1];
        for (int[] row : memo) {Arrays.fill(row, -1);}
        return dfs(n, target);
    }

    private int dfs(int i, int j) {
        if (i == 0) {return j == 0 ? 1 : 0;}
        if (memo[i][j] != -1) {return memo[i][j];}
        int ans = 0;
        for (int point = 1; point <= Math.min(k, j); point++) {
            ans = (ans + dfs(i - 1, j - point)) % 1000000007;
        }
        return memo[i][j] = ans;
    }

    public static void main(String[] args) {
        NumRollsToTarget nrtt = new NumRollsToTarget();
        System.out.println(nrtt.numRollsToTarget(30, 30, 500));
    }
}
