package work.huangdu.question_bank.easy;

/**
 * 1446. 连续字符
 * 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * 请你返回字符串的能量。
 * 示例 1：
 * 输入：s = "leetcode"
 * 输出：2
 * 解释：子字符串 "ee" 长度为 2 ，只包含字符 'e' 。
 * 示例 2：
 * 输入：s = "abbcccddddeeeeedcba"
 * 输出：5
 * 解释：子字符串 "eeeee" 长度为 5 ，只包含字符 'e' 。
 * 示例 3：
 * 输入：s = "triplepillooooow"
 * 输出：5
 * 示例 4：
 * 输入：s = "hooraaaaaaaaaaay"
 * 输出：11
 * 示例 5：
 * 输入：s = "tourist"
 * 输出：1
 * 提示：
 * 1 <= s.length <= 500
 * s 只包含小写英文字母。
 *
 * @author huangdu
 * @version 2021/12/1
 */
public class MaxPower {
    public int maxPower(String s) {
        int n = s.length(), max = 1, len = 1;
        char pre = s.charAt(0);
        for (int i = 1; i < n; i++) {
            char cur = s.charAt(i);
            if (cur == pre) {
                len++;
            } else {
                max = Math.max(max, len);
                len = 1;
                pre = cur;
            }
        }
        return Math.max(max, len);
    }
}
