package work.huangdu.question_bank.difficult;

/**
 * 878. 第 N 个神奇数字
 * 一个正整数如果能被 a 或 b 整除，那么它是神奇的。
 * 给定三个整数 n , a , b ，返回第 n 个神奇的数字。因为答案可能很大，所以返回答案 对 10^9 + 7 取模 后的值。
 * 示例 1：
 * 输入：n = 1, a = 2, b = 3
 * 输出：2
 * 示例 2：
 * 输入：n = 4, a = 2, b = 3
 * 输出：6
 * 提示：
 * 1 <= n <= 10^9
 * 2 <= a, b <= 4 * 10^4
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/25
 */
public class NthMagicalNumber {
    public int nthMagicalNumber(int n, int a, int b) {
        long left = Math.min(a, b), right = (long)Math.max(a, b) * n;
        int c = lcm(a, b);
        while (left < right) {
            long mid = left + (right - left >> 1),
                value = mid / a + mid / b - mid / c;
            if (value < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return (int)(left % (1E9 + 7));
    }

    private int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    private int gcd(int p1, int p2) {
        if (p2 == 0) {return p1;}
        int r = p1 % p2;
        return gcd(p2, r);
    }
}
