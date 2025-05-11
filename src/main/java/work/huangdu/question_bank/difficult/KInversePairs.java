package work.huangdu.question_bank.difficult;

/**
 * 629. K个逆序对数组
 * 给出两个整数 n 和 k，找出所有包含从 1 到 n 的数字，且恰好拥有 k 个逆序对的不同的数组的个数。
 * 逆序对的定义如下：对于数组的第i个和第 j个元素，如果满i < j且 a[i] > a[j]，则其为一个逆序对；否则不是。
 * 由于答案可能很大，只需要返回 答案 mod 10^9 + 7 的值。
 * 示例 1:
 * 输入: n = 3, k = 0
 * 输出: 1
 * 解释:
 * 只有数组 [1,2,3] 包含了从1到3的整数并且正好拥有 0 个逆序对。
 * 示例 2:
 * 输入: n = 3, k = 1
 * 输出: 2
 * 解释:
 * 数组 [1,3,2] 和 [2,1,3] 都有 1 个逆序对。
 * 说明:
 * n 的范围是 [1, 1000] 并且 k 的范围是 [0, 1000]。
 *
 * @author huangdu
 * @version 2021/11/18
 */
public class KInversePairs {
    /**
     * TODO 动态规划复习
     * 定义动态规划数组dp[i][j]表示所有包含从1到i的数字，且恰好拥有j个逆序对的不同的数组的个数
     * 对于1到i我们选取一个数k将其放在数组最后，逆序对的个数由两部分组成：
     * 1. 数字k，和前i-1个数的逆序对个数，即为i-k个（比k大的数字数量）
     * 2. 前i-1个数本身的逆序对个数
     * 第一部分数量确定，两部分和确定，所以第二部分的值我们是确定的，即为j-(i-k)，可推导出动态规划状态转移方程
     * dp[i][j] = ∑(k=1,k<=i)dp[i-1][j-(i-k)] = ∑(k=0,k<=i-1)dp[i-1][j-k]
     * dp[i][j-1] = ∑(k=1,k<=i)dp[i-1][j-k]
     * ->
     * dp[i][j] = dp[i][j-1] + dp[i-1][j] - dp[i-1][j-i];
     * 边界
     * dp[*][0] = 1;K
     * dp[0][*(*!=0)] = 0;
     * dp[*][负数] = 0;
     */
    public int kInversePairs2(int n, int k) {
        final int mod = (int)1e9 + 7;
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {dp[i][0] = 1;}
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = (dp[i][j - 1] + (dp[i - 1][j] - (j - i >= 0 ? dp[i - 1][j - i] : 0))) % mod;
                if (dp[i][j] < 0) {dp[i][j] += mod;}
            }
        }
        return dp[n][k];
    }

    public int kInversePairs(int n, int k) {
        final int mod = (int)1e9 + 7;
        int[] dp = new int[k + 1], dp2 = new int[k + 1];
        dp[0] = 1;
        dp2[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp2[j] = (dp2[j - 1] + (dp[j] - (j - i >= 0 ? dp[j - i] : 0))) % mod;
                if (dp2[j] < 0) {dp2[j] += mod;}
            }
            int[] temp = dp;
            dp = dp2;
            dp2 = temp;
        }
        return dp[k];
    }

    public static void main(String[] args) {
        KInversePairs kip = new KInversePairs();
        System.out.println(kip.kInversePairs(3, 1));
    }
}
