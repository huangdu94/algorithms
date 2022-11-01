package work.huangdu.question_bank.easy;

/**
 * 1662. 检查两个字符串数组是否相等
 * 给你两个字符串数组 word1 和 word2 。如果两个数组表示的字符串相同，返回 true ；否则，返回 false 。
 * 数组表示的字符串 是由数组中的所有元素 按顺序 连接形成的字符串。
 * 示例 1：
 * 输入：word1 = ["ab", "c"], word2 = ["a", "bc"]
 * 输出：true
 * 解释：
 * word1 表示的字符串为 "ab" + "c" -> "abc"
 * word2 表示的字符串为 "a" + "bc" -> "abc"
 * 两个字符串相同，返回 true
 * 示例 2：
 * 输入：word1 = ["a", "cb"], word2 = ["ab", "c"]
 * 输出：false
 * 示例 3：
 * 输入：word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
 * 输出：true
 * 提示：
 * 1 <= word1.length, word2.length <= 10^3
 * 1 <= word1[i].length, word2[i].length <= 10^3
 * 1 <= sum(word1[i].length), sum(word2[i].length) <= 10^3
 * word1[i] 和 word2[i] 由小写字母组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/11/1
 */
public class ArrayStringsAreEqual {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int n1 = word1.length, n2 = word2.length;
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        while (i1 < n1 && i2 < n2) {
            if (word1[i1].charAt(j1) != word2[i2].charAt(j2)) {return false;}
            if (++j1 == word1[i1].length()) {
                i1++;
                j1 = 0;
            }
            if (++j2 == word2[i2].length()) {
                i2++;
                j2 = 0;
            }
        }
        return i1 == n1 && i2 == n2;
    }

    public boolean arrayStringsAreEqual2(String[] word1, String[] word2) {
        int check = 0;
        for (String word : word1) {
            check += word.length();
        }
        for (String word : word2) {
            check -= word.length();
        }
        if (check != 0) {return false;}
        int n1 = word1.length;
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        while (i1 < n1) {
            if (word1[i1].charAt(j1) != word2[i2].charAt(j2)) {return false;}
            if (++j1 == word1[i1].length()) {
                i1++;
                j1 = 0;
            }
            if (++j2 == word2[i2].length()) {
                i2++;
                j2 = 0;
            }
        }
        return true;
    }
}
