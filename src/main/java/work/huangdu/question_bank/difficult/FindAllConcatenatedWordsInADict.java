package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

/**
 * 472. 连接词
 * 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 * 示例 1：
 * 输入：words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * 输出：["catsdogcats","dogcatsdog","ratcatdogcat"]
 * 解释：
 * "catsdogcats" 由 "cats", "dog" 和 "cats" 组成;
 * "dogcatsdog" 由 "dog", "cats" 和 "dog" 组成;
 * "ratcatdogcat" 由 "rat", "cat", "dog" 和 "cat" 组成。
 * 示例 2：
 * 输入：words = ["cat","dog","catdog"]
 * 输出：["catdog"]
 * 提示：
 * 1 <= words.length <= 10^4
 * 0 <= words[i].length <= 1000
 * words[i] 仅由小写字母组成
 * 0 <= sum(words[i].length) <= 10^5
 *
 * @author huangdu
 * @version 2021/12/28
 */
public class FindAllConcatenatedWordsInADict {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        TreeNode root = new TreeNode();
        for (String word : words) {
            int n = word.length();
            if (n == 0) {continue;}
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(0);
            boolean[] visited = new boolean[n + 1];
            while (!queue.isEmpty()) {
                TreeNode cur = root;
                boolean flag = true;
                for (int i = queue.poll(); i < n; i++) {
                    int c = word.charAt(i) - 'a';
                    if (cur.children[c] == null) {
                        flag = false;
                        break;
                    }
                    cur = cur.children[c];
                    if (cur.isEnd && !visited[i + 1]) {
                        visited[i + 1] = true;
                        queue.offer(i + 1);
                    }
                }
                if (flag && cur.isEnd) {
                    ans.add(word);
                    break;
                }
            }
            TreeNode node = root;
            for (int i = 0; i < n; i++) {
                int c = word.charAt(i) - 'a';
                if (node.children[c] == null) {
                    node.children[c] = new TreeNode();
                }
                node = node.children[c];
            }
            node.isEnd = true;
        }
        return ans;
    }

    static class TreeNode {
        TreeNode[] children = new TreeNode[26];
        boolean isEnd = false;
    }

    static class Solution {
        // 连接词没必要加入字典树， 很容易想通为什么。
        public List<String> findAllConcatenatedWordsInADict(String[] words) {
            List<String> ans = new ArrayList<>();
            Arrays.sort(words, Comparator.comparingInt(String::length));
            TreeNode root = new TreeNode();
            for (String word : words) {
                int n = word.length();
                if (n == 0) {continue;}
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(0);
                boolean[] visited = new boolean[n + 1];
                boolean isConcatenated = false;
                while (!queue.isEmpty()) {
                    TreeNode cur = root;
                    boolean endFlag = true;
                    for (int i = queue.poll(); i < n; i++) {
                        int c = word.charAt(i) - 'a';
                        if (cur.children[c] == null) {
                            endFlag = false;
                            break;
                        }
                        cur = cur.children[c];
                        if (cur.isEnd && !visited[i + 1]) {
                            visited[i + 1] = true;
                            queue.offer(i + 1);
                        }
                    }
                    if (endFlag && cur.isEnd) {
                        isConcatenated = true;
                        ans.add(word);
                        break;
                    }
                }
                if (!isConcatenated) {
                    TreeNode node = root;
                    for (int i = 0; i < n; i++) {
                        int c = word.charAt(i) - 'a';
                        if (node.children[c] == null) {
                            node.children[c] = new TreeNode();
                        }
                        node = node.children[c];
                    }
                    node.isEnd = true;
                }
            }
            return ans;
        }

        static class TreeNode {
            TreeNode[] children = new TreeNode[26];
            boolean isEnd = false;
        }
    }
}
