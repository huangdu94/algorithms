package work.huangdu.question_bank.difficult;

/**
 * 1163. 按字典序排在最后的子串
 * 给你一个字符串 s ，找出它的所有子串并按字典序排列，返回排在最后的那个子串。
 * 示例 1：
 * 输入：s = "abab"
 * 输出："bab"
 * 解释：我们可以找出 7 个子串 ["a", "ab", "aba", "abab", "b", "ba", "bab"]。按字典序排在最后的子串是 "bab"。
 * 示例 2：
 * 输入：s = "leetcode"
 * 输出："tcode"
 * 提示：
 * 1 <= s.length <= 4 * 10^5
 * s 仅含有小写英文字符。
 *
 * @author huangdu
 * @version 2023/4/26
 */
public class LastSubstring {
    public String lastSubstring(String s) {
        int n = s.length(), i = 0, j = 1, k = 0;
        while (j + k < n) {
            while (j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            }
            if (j + k < n) {
                if (s.charAt(i + k) < s.charAt(j + k)) {
                    i = i + k + 1;
                    if (i >= j) {j = i + 1;}
                } else {
                    j = j + k + 1;
                }
                k = 0;
            }
        }
        return s.substring(i);
    }
}