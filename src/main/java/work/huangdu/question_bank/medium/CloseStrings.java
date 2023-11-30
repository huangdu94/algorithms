package work.huangdu.question_bank.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 1657. 确定两个字符串是否接近
 * 如果可以使用以下操作从一个字符串得到另一个字符串，则认为两个字符串 接近 ：
 * 操作 1：交换任意两个 现有 字符。
 * 例如，abcde -> aecdb
 * 操作 2：将一个 现有 字符的每次出现转换为另一个 现有 字符，并对另一个字符执行相同的操作。
 * 例如，aacabb -> bbcbaa（所有 a 转化为 b ，而所有的 b 转换为 a ）
 * 你可以根据需要对任意一个字符串多次使用这两种操作。
 * 给你两个字符串，word1 和 word2 。如果 word1 和 word2 接近 ，就返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入：word1 = "abc", word2 = "bca"
 * 输出：true
 * 解释：2 次操作从 word1 获得 word2 。
 * 执行操作 1："abc" -> "acb"
 * 执行操作 1："acb" -> "bca"
 * 示例 2：
 * 输入：word1 = "a", word2 = "aa"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * 示例 3：
 * 输入：word1 = "cabbba", word2 = "abbccc"
 * 输出：true
 * 解释：3 次操作从 word1 获得 word2 。
 * 执行操作 1："cabbba" -> "caabbb"
 * 执行操作 2："caabbb" -> "baaccc"
 * 执行操作 2："baaccc" -> "abbccc"
 * 示例 4：
 * 输入：word1 = "cabbba", word2 = "aabbss"
 * 输出：false
 * 解释：不管执行多少次操作，都无法从 word1 得到 word2 ，反之亦然。
 * 提示：
 * 1 <= word1.length, word2.length <= 10^5
 * word1 和 word2 仅包含小写英文字母
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class CloseStrings {
    public boolean closeStrings(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] count1 = new int[26], count2 = new int[26];
        for (int i = 0; i < m; i++) {
            count1[word1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n; i++) {
            count2[word2.charAt(i) - 'a']++;
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            int cnt1 = count1[i], cnt2 = count2[i];
            if (cnt1 == 0 && cnt2 != 0 || cnt1 != 0 && cnt2 == 0) {return false;}
            if (cnt1 > 0 && freq.merge(cnt1, 1, Integer::sum) == 0) {freq.remove(cnt1);}
            if (cnt2 > 0 && freq.merge(cnt2, -1, Integer::sum) == 0) {freq.remove(cnt2);}
        }
        return freq.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new CloseStrings().closeStrings("a", "aa"));
    }
}
