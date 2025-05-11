package work.huangdu.question_bank.difficult;

/**
 * 1012. 至少有 1 位重复的数字
 * 给定正整数 n，返回在 [1, n] 范围内具有 至少 1 位 重复数字的正整数的个数。
 * 示例 1：
 * 输入：n = 20
 * 输出：1
 * 解释：具有至少 1 位重复数字的正数（<= 20）只有 11 。
 * 示例 2：
 * 输入：n = 100
 * 输出：10
 * 解释：具有至少 1 位重复数字的正数（<= 100）有 11，22，33，44，55，66，77，88，99 和 100 。
 * 示例 3：
 * 输入：n = 1000
 * 输出：262
 * 提示：
 * 1 <= n <= 10^9
 *
 * @author huangdu
 * @version 2023/3/25
 */
public class NumDupDigitsAtMostN {
    private String nStr;
    private int[][] memo;

    public int numDupDigitsAtMostN(int n) {
        this.nStr = Integer.toString(n);
        this.memo = new int[1 << 10][nStr.length()];
        int ans = n;
        for (int len = 1; len < nStr.length(); len++) {
            ans -= compute(len);
        }
        return ans - f(0, 0, true);
    }

    private int f(int mask, int index, boolean same) {
        if (index == nStr.length()) {return 1;}
        if (!same && memo[mask][index] != 0) {return memo[mask][index];}
        int limit = same ? nStr.charAt(index) - '0' : 9, count = 0;
        for (int i = 0; i <= limit; i++) {
            if (mask == 0 && i == 0 || (mask & 1 << i) != 0) {continue;}
            count += f(mask | 1 << i, index + 1, same && i == limit);
        }
        if (!same) {
            memo[mask][index] = count;
        }
        return count;
    }

    private int compute(int len) {
        int result = 9, num = 9;
        while (--len > 0) {result *= num--;}
        return result;
    }
}
