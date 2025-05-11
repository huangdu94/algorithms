package work.huangdu.question_bank.easy;

/**
 * 2485. 找出中枢整数
 * 给你一个正整数 n ，找出满足下述条件的 中枢整数 x ：
 * 1 和 x 之间的所有元素之和等于 x 和 n 之间所有元素之和。
 * 返回中枢整数 x 。如果不存在中枢整数，则返回 -1 。题目保证对于给定的输入，至多存在一个中枢整数。
 * 示例 1：
 * 输入：n = 8
 * 输出：6
 * 解释：6 是中枢整数，因为 1 + 2 + 3 + 4 + 5 + 6 = 6 + 7 + 8 = 21 。
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 * 解释：1 是中枢整数，因为 1 = 1 。
 * 示例 3：
 * 输入：n = 4
 * 输出：-1
 * 解释：可以证明不存在满足题目要求的整数。
 * 提示：
 * 1 <= n <= 1000
 *
 * @author huangdu
 * @version 2023/6/26
 */
public class PivotInteger {
    // (1 + i) * i / 2 == (i + n) * (n - i + 1) / 2
    public int pivotInteger(int n) {
        int x = (n * n + n) >> 1;
        double s1 = x / 2.0, s2 = (s1 + x / s1) / 2.0;
        while (Math.abs(s2 - s1) > 1e-6) {
            s1 = s2;
            s2 = (s1 + x / s1) / 2.0;
        }
        return Math.abs(s2 - Math.round(s2)) < 1e-6 ? (int)s2 : -1;
    }

    public static void main(String[] args) {
        PivotInteger pi = new PivotInteger();
        System.out.println(pi.pivotInteger(1));
    }
}
