package work.huangdu.question_bank.medium;

/**
 * 926. 将字符串翻转到单调递增
 * 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
 * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
 * 返回使 s 单调递增的最小翻转次数。
 * 示例 1：
 * 输入：s = "00110"
 * 输出：1
 * 解释：翻转最后一位得到 00111.
 * 示例 2：
 * 输入：s = "010110"
 * 输出：2
 * 解释：翻转得到 011111，或者是 000111。
 * 示例 3：
 * 输入：s = "00011000"
 * 输出：2
 * 解释：翻转得到 00000000。
 * 提示：
 * 1 <= s.length <= 10^5
 * s[i] 为 '0' 或 '1'
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2022/6/11
 */
public class MinFlipsMonoIncr {
    public int minFlipsMonoIncr(String s) {
        int n = s.length(), zeroCount = 0, ans = n, curOneCount = 0;
        // 1. 首先数一下字符串总共有多少个0
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '0') {
                zeroCount++;
            }
        }
        // 2. 在遍历一次，遍历到的地方前面的1都要改成0，0都要改成1，计最小次数
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                curOneCount++;
            }
            int change = zeroCount - (i + 1 - curOneCount) + curOneCount;
            ans = Math.min(ans, change);
        }
        // 3. 以上漏了一种，就是把所有的0都改成1
        return Math.min(ans, zeroCount);
    }

    public static void main(String[] args) {
        String s = "11011";
        MinFlipsMonoIncr mfmi = new MinFlipsMonoIncr();
        System.out.println(mfmi.minFlipsMonoIncr(s));
    }
}
