package work.huangdu.question_bank.easy;

/**
 * 1784. 检查二进制字符串字段
 * 给你一个二进制字符串 s ，该字符串不含前导零。
 * 如果 s 包含 零个或一个由连续的 '1' 组成的字段 ，返回 true。否则，返回 false 。
 * 如果 s 中 由连续若干个 '1' 组成的字段 数量不超过 1，返回 true。否则，返回 false 。
 * 示例 1：
 * 输入：s = "1001"
 * 输出：false
 * 解释：由连续若干个 '1' 组成的字段数量为 2，返回 false
 * 示例 2：
 * 输入：s = "110"
 * 输出：true
 * 提示：
 * 1 <= s.length <= 100
 * s[i] 为 '0' 或 '1'
 * s[0] 为 '1'
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/10/3
 */
public class CheckOnesSegment {
    public boolean checkOnesSegment(String s) {
        int n = s.length(), i = 0;
        while (i < n && s.charAt(i) == '1') {i++;}
        if (i == n) {return true;}
        while (i < n && s.charAt(i) == '0') {i++;}
        return i == n;
    }
}
