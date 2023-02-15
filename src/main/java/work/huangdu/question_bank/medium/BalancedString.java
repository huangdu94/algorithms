package work.huangdu.question_bank.medium;

/**
 * 1234. 替换子串得到平衡字符串
 * 有一个只含有 'Q', 'W', 'E', 'R' 四种字符，且长度为 n 的字符串。
 * 假如在该字符串中，这四个字符都恰好出现 n/4 次，那么它就是一个「平衡字符串」。
 * 给你一个这样的字符串 s，请通过「替换一个子串」的方式，使原字符串 s 变成一个「平衡字符串」。
 * 你可以用和「待替换子串」长度相同的 任何 其他字符串来完成替换。
 * 请返回待替换子串的最小可能长度。
 * 如果原字符串自身就是一个平衡字符串，则返回 0。
 * 示例 1：
 * 输入：s = "QWER"
 * 输出：0
 * 解释：s 已经是平衡的了。
 * 示例 2：
 * 输入：s = "QQWE"
 * 输出：1
 * 解释：我们需要把一个 'Q' 替换成 'R'，这样得到的 "RQWE" (或 "QRWE") 是平衡的。
 * 示例 3：
 * 输入：s = "QQQW"
 * 输出：2
 * 解释：我们可以把前面的 "QQ" 替换成 "ER"。
 * 示例 4：
 * 输入：s = "QQQQ"
 * 输出：3
 * 解释：我们可以替换后 3 个 'Q'，使 s = "QWER"。
 * 提示：
 * 1 <= s.length <= 10^5
 * s.length 是 4 的倍数
 * s 中只含有 'Q', 'W', 'E', 'R' 四种字符
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/2/13
 */
public class BalancedString {
    public int balancedString(String s) {
        int n = s.length(), target = n / 4;
        int[] count = new int[4];
        for (int i = 0; i < n; i++) {count[map(s.charAt(i))]++;}
        if (count[0] == count[1] && count[1] == count[2] && count[2] == count[3] && count[3] == target) {return 0;}
        int res = 0;
        for (int right = 0, left = 0; right < n; right++) {
            count[map(s.charAt(right))]--;
            while (count[0] <= target && count[1] <= target && count[2] <= target && count[3] <= target) {
                res = Math.min(res, right - left + 1);
                count[map(s.charAt(left++))]++;
            }
        }
        return res;
    }

    private int map(char ch) {
        if (ch == 'Q') {return 0;}
        if (ch == 'W') {return 1;}
        if (ch == 'E') {return 2;}
        return 3;
    }
}
