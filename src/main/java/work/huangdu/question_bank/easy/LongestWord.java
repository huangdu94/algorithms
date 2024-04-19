package work.huangdu.question_bank.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * 720. 词典中最长的单词
 * 给出一个字符串数组 words 组成的一本英语词典。返回 words 中最长的一个单词，该单词是由 words 词典中其他单词逐步添加一个字母组成。
 * 若其中有多个可行的答案，则返回答案中字典序最小的单词。若无答案，则返回空字符串。
 * 示例 1：
 * 输入：words = ["w","wo","wor","worl", "world"]
 * 输出："world"
 * 解释： 单词"world"可由"w", "wo", "wor", 和 "worl"逐步添加一个字母组成。
 * 示例 2：
 * 输入：words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * 输出："apple"
 * 解释："apply" 和 "apple" 都能由词典中的单词组成。但是 "apple" 的字典序小于 "apply"
 * 提示：
 * 1 <= words.length <= 1000
 * 1 <= words[i].length <= 30
 * 所有输入的字符串 words[i] 都只包含小写字母。
 *
 * @author huangdu
 * @version 2022/3/17
 */
public class LongestWord {
    public String longestWord(String[] words) {
        List<String> initList = new ArrayList<>();
        Set<String> wordSet = new HashSet<>();
        for (String word : words) {
            if (word.length() == 1) {
                initList.add(word);
            } else {
                wordSet.add(word);
            }
        }
        initList.sort(String::compareTo);
        Queue<String> queue = new ArrayDeque<>(initList);
        String ans = "";
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String cur = queue.poll();
                if (i == 0) { ans = cur; }
                for (char next = 'a'; next <= 'z'; next++) {
                    if (wordSet.contains(cur + next)) {
                        queue.offer(cur + next);
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        LongestWord lw = new LongestWord();
        System.out.println(lw.longestWord(new String[] {"m", "mo", "moc", "moch", "mocha", "l", "la", "lat", "latt", "latte", "c", "ca", "cat"}));
    }
}
