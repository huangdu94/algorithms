package work.huangdu.question_bank.medium;

/**
 * 2131. 连接两字母单词得到的最长回文串
 * 给你一个字符串数组 words 。words 中每个元素都是一个包含 两个 小写英文字母的单词。
 * 请你从 words 中选择一些元素并按 任意顺序 连接它们，并得到一个 尽可能长的回文串 。每个元素 至多 只能使用一次。
 * 请你返回你能得到的最长回文串的 长度 。如果没办法得到任何一个回文串，请你返回 0 。
 * 回文串 指的是从前往后和从后往前读一样的字符串。
 * 示例 1：
 * 输入：words = ["lc","cl","gg"]
 * 输出：6
 * 解释：一个最长的回文串为 "lc" + "gg" + "cl" = "lcggcl" ，长度为 6 。
 * "clgglc" 是另一个可以得到的最长回文串。
 * 示例 2：
 * 输入：words = ["ab","ty","yt","lc","cl","ab"]
 * 输出：8
 * 解释：最长回文串是 "ty" + "lc" + "cl" + "yt" = "tylcclyt" ，长度为 8 。
 * "lcyttycl" 是另一个可以得到的最长回文串。
 * 示例 3：
 * 输入：words = ["cc","ll","xx"]
 * 输出：2
 * 解释：最长回文串是 "cc" ，长度为 2 。
 * "ll" 是另一个可以得到的最长回文串。"xx" 也是。
 * 提示：
 * 1 <= words.length <= 105
 * words[i].length == 2
 * words[i] 仅包含小写英文字母。
 *
 * @author huangdu
 * @version 2025/5/25
 */
public class LongestPalindrome {
    public int longestPalindrome2(String[] words) {
        int pair = 0, remainSame = 0;
        int[][] freq = new int[26][26];
        for (String word : words) {
            freq[word.charAt(0) - 'a'][word.charAt(1) - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            pair += freq[i][i] / 2;
            remainSame |= freq[i][i] & 1;
            for (int j = i + 1; j < 26; j++) {
                pair += Math.min(freq[i][j], freq[j][i]);
            }

        }
        return pair * 4 + remainSame * 2;
    }

    public int longestPalindrome(String[] words) {
        int pair = 0;
        int[] freq = new int[26 * 26];
        for (String word : words) {
            freq[(word.charAt(0) - 'a') * 26 + (word.charAt(1) - 'a')]++;
        }
        boolean flag = false;
        for (int i = 0; i < 26; i++) {
            pair += freq[i * 26 + i] / 2;
            if (!flag && (freq[i * 26 + i] & 1) != 0) {
                flag = true;
            }
            for (int j = i + 1; j < 26; j++) {
                pair += Math.min(freq[i * 26 + j], freq[j * 26 + i]);
            }

        }
        return pair * 4 + (flag ? 2 : 0);
    }

    public int longestPalindrome3(String[] words) {
        int pair = 0;
        int[] freq = new int[26 * 26];
        for (String word : words) {
            char ch0 = word.charAt(0), ch1 = word.charAt(1);
            int reverseHash = hash(ch1, ch0);
            if (freq[reverseHash] > 0) {
                pair += 1;
                freq[reverseHash]--;
            } else {
                freq[hash(ch0, ch1)]++;
            }
        }
        for (int i = 0; i < 26; i++) {
            int hash = i * 26 + i;
            if (freq[hash] != 0) {
                return pair * 4 + 2;
            }
        }
        return pair * 4;
    }

    private int hash(char ch0, char ch1) {
        return (ch0 - 'a') * 26 + (ch1 - 'a');
    }
}
