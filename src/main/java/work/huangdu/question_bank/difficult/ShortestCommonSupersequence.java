package work.huangdu.question_bank.difficult;

/**
 * 1092. 最短公共超序列
 * 给出两个字符串 str1 和 str2，返回同时以 str1 和 str2 作为子序列的最短字符串。如果答案不止一个，则可以返回满足条件的任意一个答案。
 * （如果从字符串 T 中删除一些字符（也可能不删除，并且选出的这些字符可以位于 T 中的 任意位置），可以得到字符串 S，那么 S 就是 T 的子序列）
 * 示例：
 * 输入：str1 = "abac", str2 = "cab"
 * 输出："cabac"
 * 解释：
 * str1 = "abac" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 的第一个 "c"得到 "abac"。
 * str2 = "cab" 是 "cabac" 的一个子串，因为我们可以删去 "cabac" 末尾的 "ac" 得到 "cab"。
 * 最终我们给出的答案是满足上述属性的最短字符串。
 * 提示：
 * 1 <= str1.length, str2.length <= 1000
 * str1 和 str2 都由小写英文字母组成。
 *
 * @author huangdu
 * @version 2023/3/29
 */
public class ShortestCommonSupersequence {
    private String s;
    private String t;
    private int[][] dp;

    public String shortestCommonSupersequence(String str1, String str2) {
        this.s = str1;
        this.t = str2;
        this.dp = new int[s.length()][t.length()];
        return shortestCommonSupersequence(s.length() - 1, t.length() - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0) {return j + 1;}
        if (j < 0) {return i + 1;}
        if (dp[i][j] > 0) {return dp[i][j];}
        char sCh = s.charAt(i), tCh = t.charAt(j);
        if (sCh == tCh) {return dp[i][j] = dfs(i - 1, j - 1) + 1;}
        return dp[i][j] = Math.min(dfs(i - 1, j), dfs(i, j - 1)) + 1;
    }

    public String shortestCommonSupersequence(int i, int j) {
        if (i < 0) {return t.substring(0, j + 1);}
        if (j < 0) {return s.substring(0, i + 1);}
        if (s.charAt(i) == t.charAt(j)) {
            return shortestCommonSupersequence(i - 1, j - 1) + s.charAt(i);
        }
        // dfs(i, j) == dfs(i - 1, j) + 1
        if (dfs(i - 1, j) < dfs(i, j - 1)) {
            return shortestCommonSupersequence(i - 1, j) + s.charAt(i);
        }
        return shortestCommonSupersequence(i, j - 1) + t.charAt(j);
    }
}
