package work.huangdu.question_bank.difficult;

/**
 * 115. 不同的子序列
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 * 示例 1：
 * 输入：s = "rabbbit", t = "rabbit"
 * 输出：3
 * 解释：
 * 如下图所示, 有 3 种可以从 s 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2：
 * 输入：s = "babgbag", t = "bag"
 * 输出：5
 * 解释：
 * 如下图所示, 有 5 种可以从 s 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ^  ^^
 * babgbag
 * ^^^
 * 提示：
 * 0 <= s.length, t.length <= 1000
 * s 和 t 由英文字母组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/17
 */
public class NumDistinct {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        if (m < n) { return 0; }
        int[][] dp = new int[m + 1][n + 1];
        // 当 j=n 对任意 0≤i≤m，有 dp[i][n]=1如果比较难理解，那么我们可以给s、t后面补一个特殊的字符比如‘&’，那么对于任意一个s[i:]肯定有且仅有一个t[n]
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            char sChar = s.charAt(i);
            for (int j = n - 1; j >= 0; j--) {
                char tChar = t.charAt(j);
                if (sChar == tChar) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 回溯超时
     */
    private int count;
    private int sLen;
    private char[] s;
    private int tLen;
    private char[] t;

    public int numDistinct2(String S, String T) {
        count = 0;
        sLen = S.length();
        s = S.toCharArray();
        tLen = T.length();
        t = T.toCharArray();
        backtrack(0, 0);
        return count;
    }

    private boolean backtrack(int indexS, int indexT) {
        if (indexT == tLen) {
            count++;
            return true;
        }
        if (sLen - indexS < tLen - indexT) { return false; }
        for (int i = indexS; i < sLen; i++) {
            if (s[i] == t[indexT]) {
                if (!backtrack(i + 1, indexT + 1)) {
                    break;
                }
            }
        }
        return true;
    }
}
