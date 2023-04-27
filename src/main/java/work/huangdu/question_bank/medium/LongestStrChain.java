package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1048. 最长字符串链
 * 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 * 示例 1：
 * 输入：words = ["a","b","ba","bca","bda","bdca"]
 * 输出：4
 * 解释：最长单词链之一为 ["a","ba","bda","bdca"]
 * 示例 2:
 * 输入：words = ["xbc","pcxbcf","xb","cxbc","pcxbc"]
 * 输出：5
 * 解释：所有的单词都可以放入单词链 ["xb", "xbc", "cxbc", "pcxbc", "pcxbcf"].
 * 示例 3:
 * 输入：words = ["abcd","dbqca"]
 * 输出：1
 * 解释：字链["abcd"]是最长的字链之一。
 * ["abcd"，"dbqca"]不是一个有效的单词链，因为字母的顺序被改变了。
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 16
 * words[i] 仅由小写英文字母组成。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2023/4/27
 */
public class LongestStrChain {
    public int longestStrChain(String[] words) {
        int n = words.length, ans = 1;
        int[] f = new int[n];
        Arrays.fill(f, 1);
        Arrays.sort(words, Comparator.comparingInt(String::length));
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (check(words[j], words[i])) {
                    f[i] = Math.max(f[i], f[j] + 1);
                    ans = Math.max(ans, f[i]);
                }
            }
        }
        return ans;
    }

    private boolean check(String worda, String wordb) {
        if (worda.length() + 1 != wordb.length()) {return false;}
        int n = wordb.length(), i = 0, j = 0;
        boolean diff = false;
        while (i < n - 1 && j < n) {
            char cha = worda.charAt(i), chb = wordb.charAt(j);
            if (cha == chb) {
                i++;
            } else {
                if (diff) {return false;}
                diff = true;
            }
            j++;
        }
        return i == n - 1;
    }

    public static void main(String[] args) {
        String[] words = {"abcd", "dbqca"};
        LongestStrChain lsc = new LongestStrChain();
        System.out.println(lsc.longestStrChain(words));
    }
}
