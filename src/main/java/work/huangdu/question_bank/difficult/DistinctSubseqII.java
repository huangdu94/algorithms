package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 940. 不同的子序列 II
 * 给定一个字符串 s，计算 s 的 不同非空子序列 的个数。因为结果可能很大，所以返回答案需要对 10^9 + 7 取余 。
 * 字符串的 子序列 是经由原字符串删除一些（也可能不删除）字符但不改变剩余字符相对位置的一个新字符串。
 * 例如，"ace" 是 "abcde" 的一个子序列，但 "aec" 不是。
 * 示例 1：
 * 输入：s = "abc"
 * 输出：7
 * 解释：7 个不同的子序列分别是 "a", "b", "c", "ab", "ac", "bc", 以及 "abc"。
 * 示例 2：
 * 输入：s = "aba"
 * 输出：6
 * 解释：6 个不同的子序列分别是 "a", "b", "ab", "ba", "aa" 以及 "aba"。
 * 示例 3：
 * 输入：s = "aaa"
 * 输出：3
 * 解释：3 个不同的子序列分别是 "a", "aa" 以及 "aaa"。
 * 提示：
 * 1 <= s.length <= 2000
 * s 仅由小写英文字母组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/24
 */
public class DistinctSubseqII {
    private static final int MOD = (int)1e9 + 7;

    public int distinctSubseqII(String s) {
        int n = s.length();
        int[] dp = new int[128];
        Arrays.fill(dp, -1);
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int c = 'a'; c <= 'z'; c++) {
                if (dp[c] != -1) {
                    count = (count + dp[c]) % MOD;
                }
            }
            dp[s.charAt(i)] = count;
        }
        int sum = 0;
        for (int c = 'a'; c <= 'z'; c++) {
            if (dp[c] != -1) {
                sum = (sum + dp[c]) % MOD;
            }
        }
        return sum;
    }

    public int distinctSubseqII2(String s) {
        int n = s.length();
        int[] dp = new int[n], last = new int[128];
        Arrays.fill(last, -1);
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int c = 'a'; c <= 'z'; c++) {
                if (last[c] != -1) {
                    dp[i] = (dp[i] + dp[last[c]]) % MOD;
                }
            }
            last[s.charAt(i)] = i;
        }
        int sum = 0;
        for (int c = 'a'; c <= 'z'; c++) {
            if (last[c] != -1) {
                sum = (sum + dp[last[c]]) % MOD;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        DistinctSubseqII ds2 = new DistinctSubseqII();
        System.out.println(ds2.distinctSubseqII("abc"));
    }
}
