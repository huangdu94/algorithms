package work.huangdu.question_bank.easy;

/**
 * 1763. 最长的美好子字符串
 * 当一个字符串 s 包含的每一种字母的大写和小写形式 同时 出现在 s 中，就称这个字符串 s 是 美好 字符串。比方说，"abABB" 是美好字符串，因为 'A' 和 'a' 同时出现了，且 'B' 和 'b' 也同时出现了。然而，"abA" 不是美好字符串因为 'b' 出现了，而 'B' 没有出现。
 * 给你一个字符串 s ，请你返回 s 最长的 美好子字符串 。如果有多个答案，请你返回 最早 出现的一个。如果不存在美好子字符串，请你返回一个空字符串。
 * 示例 1：
 * 输入：s = "YazaAay"
 * 输出："aAa"
 * 解释："aAa" 是一个美好字符串，因为这个子串中仅含一种字母，其小写形式 'a' 和大写形式 'A' 也同时出现了。
 * "aAa" 是最长的美好子字符串。
 * 示例 2：
 * 输入：s = "Bb"
 * 输出："Bb"
 * 解释："Bb" 是美好字符串，因为 'B' 和 'b' 都出现了。整个字符串也是原字符串的子字符串。
 * 示例 3：
 * 输入：s = "c"
 * 输出：""
 * 解释：没有美好子字符串。
 * 示例 4：
 * 输入：s = "dDzeE"
 * 输出："dD"
 * 解释："dD" 和 "eE" 都是最长美好子字符串。
 * 由于有多个美好子字符串，返回 "dD" ，因为它出现得最早。
 * 提示：
 * 1 <= s.length <= 100
 * s 只包含大写和小写英文字母。
 *
 * @author huangdu
 * @version 2022/2/1
 */
public class LongestNiceSubstring {
    public String longestNiceSubstring(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int len = n; len > 1; len--) {
            for (int i = 0; i + len <= n; i++) {
                if (check(chars, i, i + len)) {
                    return new String(chars, i, len);
                }
            }
        }
        return "";
    }

    private boolean check(char[] chars, int start, int end) {
        int lowerCase = 0, upperCase = 0;
        for (int i = start; i < end; i++) {
            char c = chars[i];
            if (c < 'a') {
                lowerCase |= 1 << (c - 'A');
            } else {
                upperCase |= 1 << (c - 'a');
            }
        }
        return lowerCase == upperCase;
    }

    private boolean check2(char[] chars, int start, int end) {
        boolean[] exist = new boolean[128];
        for (int i = start; i < end; i++) {
            char c = chars[i];
            exist[c] = true;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (exist[i] && !exist[i + 32] || !exist[i] && exist[i + 32]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LongestNiceSubstring lns = new LongestNiceSubstring();
        System.out.println(lns.longestNiceSubstring("YazaAay"));
    }
}
