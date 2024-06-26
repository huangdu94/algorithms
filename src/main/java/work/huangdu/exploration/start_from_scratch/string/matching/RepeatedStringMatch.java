package work.huangdu.exploration.start_from_scratch.string.matching;

/**
 * 686. 重复叠加字符串匹配
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 * 示例 1：
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 * 示例 2：
 * 输入：a = "a", b = "aa"
 * 输出：2
 * 示例 3：
 * 输入：a = "a", b = "a"
 * 输出：1
 * 示例 4：
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 * 提示：
 * 1 <= a.length <= 10^4
 * 1 <= b.length <= 10^4
 * a 和 b 由小写英文字母组成
 *
 * @author huangdu
 * @version 2020/10/4 19:33
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        int na = a.length(), nb = b.length();
        int count = nb / na;
        if (nb % na != 0) {
            count++;
        }
        StringBuilder sb = new StringBuilder((count + 1) * na);
        for (int k = 0; k < count; k++) {
            sb.append(a);
        }
        if (sb.lastIndexOf(b) != -1) {
            return count;
        }
        sb.append(a);
        if (sb.lastIndexOf(b) != -1) {
            return count + 1;
        }
        return -1;
    }

    public int repeatedStringMatch2(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        if (aLen >= bLen && a.contains(b)) {return 1;}
        int n = 0, index = -aLen, nextIndex;
        while ((nextIndex = b.indexOf(a, index + aLen)) != -1) {
            n++;
            if (n > 1 && nextIndex != index + aLen) { return -1; }
            index = nextIndex;
        }
        b = b.replaceAll(a, "");
        if (b.length() == 0) {return n;}
        if (a.startsWith(b) || a.endsWith(b)) {return n + 1;}
        for (int i = 1, len = b.length(); i < len; i++) {
            if (a.endsWith(b.substring(0, i)) && a.startsWith(b.substring(i, len))) {
                return n + 2;
            }
        }
        return -1;
    }
}
