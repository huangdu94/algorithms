package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;

import work.huangdu.data_structure.Trie;
import work.huangdu.data_structure.TrieNode;

/**
 * 2707. 字符串中的额外字符
 * 给你一个下标从 0 开始的字符串 s 和一个单词字典 dictionary 。你需要将 s 分割成若干个 互不重叠 的子字符串，每个子字符串都在 dictionary 中出现过。s 中可能会有一些 额外的字符 不在任何子字符串中。
 * 请你采取最优策略分割 s ，使剩下的字符 最少 。
 * 示例 1：
 * 输入：s = "leetscode", dictionary = ["leet","code","leetcode"]
 * 输出：1
 * 解释：将 s 分成两个子字符串：下标从 0 到 3 的 "leet" 和下标从 5 到 8 的 "code" 。只有 1 个字符没有使用（下标为 4），所以我们返回 1 。
 * 示例 2：
 * 输入：s = "sayhelloworld", dictionary = ["hello","world"]
 * 输出：3
 * 解释：将 s 分成两个子字符串：下标从 3 到 7 的 "hello" 和下标从 8 到 12 的 "world" 。下标为 0 ，1 和 2 的字符没有使用，所以我们返回 3 。
 * 提示：
 * 1 <= s.length <= 50
 * 1 <= dictionary.length <= 50
 * 1 <= dictionary[i].length <= 50
 * dictionary[i] 和 s 只包含小写英文字母。
 * dictionary 中的单词互不相同。
 *
 * @author huangdu
 */
public class MinExtraChar {
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        // dp[i] 表示以i为开头的字符串前最少剩几个字符 dp[0] = 0
        int[] dp = new int[n + 1];
        Trie trie = new Trie();
        for (String word : dictionary) {trie.insert(word);}
        Queue<TrieNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> lenQueue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (trie.getRoot().containsKey(ch)) {
                nodeQueue.offer(trie.getRoot());
                lenQueue.offer(0);
            }
            dp[i + 1] = dp[i] + 1;
            for (int k = 0, size = nodeQueue.size(); k < size; k++) {
                TrieNode prev = nodeQueue.remove(), cur = prev.get(ch);
                int len = lenQueue.remove();
                if (cur == null) {continue;}
                if (cur.isEnd()) {
                    dp[i + 1] = Math.min(dp[i + 1], dp[i - len]);
                }
                nodeQueue.offer(cur);
                lenQueue.offer(len + 1);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        MinExtraChar mec = new MinExtraChar();
        String s = "kevlplxozaizdhxoimmraiakbak";
        String[] dictionary = {
            "yv", "bmab", "hv", "bnsll", "mra",
            "jjqf", "g", "aiyzi", "ip", "pfctr",
            "flr", "ybbcl", "biu", "ke", "lpl",
            "iak", "pirua", "ilhqd", "zdhx", "fux",
            "xaw", "pdfvt", "xf", "t", "wq",
            "r", "cgmud", "aokas", "xv", "jf",
            "cyys", "wcaz", "rvegf", "ysg", "xo",
            "uwb", "lw", "okgk", "vbmi", "v",
            "mvo", "fxyx", "ad", "e"};
        System.out.println(mec.minExtraChar(s, dictionary));
    }

}
