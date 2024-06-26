package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 1032. 字符流
 * 设计一个算法：接收一个字符流，并检查这些字符的后缀是否是字符串数组 words 中的一个字符串。
 * 例如，words = ["abc", "xyz"] 且字符流中逐个依次加入 4 个字符 'a'、'x'、'y' 和 'z' ，你所设计的算法应当可以检测到 "axyz" 的后缀 "xyz" 与 words 中的字符串 "xyz" 匹配。
 * 按下述要求实现 StreamChecker 类：
 * StreamChecker(String[] words) ：构造函数，用字符串数组 words 初始化数据结构。
 * boolean query(char letter)：从字符流中接收一个新字符，如果字符流中的任一非空后缀能匹配 words 中的某一字符串，返回 true ；否则，返回 false。
 * 示例：
 * 输入：
 * ["StreamChecker", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query", "query"]
 * [[["cd", "f", "kl"]], ["a"], ["b"], ["c"], ["d"], ["e"], ["f"], ["g"], ["h"], ["i"], ["j"], ["k"], ["l"]]
 * 输出：
 * [null, false, false, false, true, false, true, false, false, false, false, false, true]
 * 解释：
 * StreamChecker streamChecker = new StreamChecker(["cd", "f", "kl"]);
 * streamChecker.query("a"); // 返回 False
 * streamChecker.query("b"); // 返回 False
 * streamChecker.query("c"); // 返回n False
 * streamChecker.query("d"); // 返回 True ，因为 'cd' 在 words 中
 * streamChecker.query("e"); // 返回 False
 * streamChecker.query("f"); // 返回 True ，因为 'f' 在 words 中
 * streamChecker.query("g"); // 返回 False
 * streamChecker.query("h"); // 返回 False
 * streamChecker.query("i"); // 返回 False
 * streamChecker.query("j"); // 返回 False
 * streamChecker.query("k"); // 返回 False
 * streamChecker.query("l"); // 返回 True ，因为 'kl' 在 words 中
 * 提示：
 * 1 <= words.length <= 2000
 * 1 <= words[i].length <= 200
 * words[i] 由小写英文字母组成
 * letter 是一个小写英文字母
 * 最多调用查询 4 * 10^4 次
 *
 * @author huangdu
 * @version 2023/3/24
 */
public class StreamChecker {
    // AC自动机
    private TrieNode cur;

    public StreamChecker(String[] words) {
        TrieNode root = new TrieNode();
        this.cur = root;
        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0, n = word.length(); i < n; i++) {
                int ch = word.charAt(i) - 'a';
                if (cur.child[ch] == null) {
                    cur.child[ch] = new TrieNode();
                }
                cur = cur.child[ch];
            }
            cur.end = true;
        }
        root.fail = root;
        Queue<TrieNode> queue = new ArrayDeque<>();
        for (int i = 0; i < 26; i++) {
            if (root.child[i] == null) {
                root.child[i] = root;
            } else {
                root.child[i].fail = root;
                queue.offer(root.child[i]);
            }
        }
        while (!queue.isEmpty()) {
            for (int k = 0, size = queue.size(); k < size; k++) {
                TrieNode node = queue.remove();
                node.end |= node.fail.end;
                for (int i = 0; i < 26; i++) {
                    if (node.child[i] == null) {
                        node.child[i] = node.fail.child[i];
                    } else {
                        node.child[i].fail = node.fail.child[i];
                        queue.offer(node.child[i]);
                    }
                }
            }
        }
    }

    public boolean query(char letter) {
        cur = cur.child[letter - 'a'];
        return cur.end;
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end = false;
        TrieNode fail;
    }
}

class StreamChecker2 {
    private final TrieNode root;
    private final Queue<TrieNode> queue;

    public StreamChecker2(String[] words) {
        this.root = new TrieNode();
        this.queue = new ArrayDeque<>();
        for (String word : words) {insert(word);}
    }

    public boolean query(char letter) {
        boolean result = false;
        queue.offer(root);
        int target = letter - 'a', size = queue.size();
        for (int i = 0; i < size; i++) {
            TrieNode cur = queue.remove();
            if (cur.child[target] != null) {
                queue.offer(cur.child[target]);
                if (!result && cur.child[target].end) {
                    result = true;
                }
            }
        }
        return result;
    }

    private void insert(String word) {
        int n = word.length();
        TrieNode cur = root;
        for (int i = 0; i < n; i++) {
            char ch = word.charAt(i);
            if (cur.child[ch - 'a'] == null) {
                cur.child[ch - 'a'] = new TrieNode();
            }
            cur = cur.child[ch - 'a'];
        }
        cur.end = true;
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end = false;
    }
}