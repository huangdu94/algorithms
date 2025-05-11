package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * 实现词典类 WordDictionary ：
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与 word 匹配，则返回 true ；否则，返回  false 。word 中可能包含一些 '.' ，每个 . 都可以表示任何一个字母。
 * 示例：
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 * 提示：
 * 1 <= word.length <= 500
 * addWord 中的 word 由小写英文字母组成
 * search 中的 word 由 '.' 或小写英文字母组成
 * 最多调用 50000 次 addWord 和 search
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 *
 * @author huangdu
 * @version 2021/10/19
 */
public class WordDictionary {
    private final TrieNode root = new TrieNode();

    public WordDictionary() {}

    public void addWord(String word) {
        int n = word.length();
        TrieNode cur = root;
        for (int i = 0; i < n; i++) {
            int index = TrieNode.getIndex(word.charAt(i));
            TrieNode next = cur.nodes[index];
            if (next == null) {
                next = new TrieNode();
                cur.nodes[index] = next;
            }
            cur = next;
        }
        cur.end = true;
    }

    public boolean search(String word) {
        int n = word.length();
        Queue<TrieNode> queue = new ArrayDeque<>();
        addNode(word.charAt(0), queue, root);
        for (int i = 1; i < n; i++) {
            int size = queue.size();
            if (size == 0) {return false;}
            char c = word.charAt(i);
            for (int k = 0; k < size; k++) {
                addNode(c, queue, queue.poll());
            }
        }
        if (queue.isEmpty()) {return false;}
        for (TrieNode node : queue) {
            if (node.end) {return true;}
        }
        return false;
    }

    private void addNode(char cur, Queue<TrieNode> queue, TrieNode parent) {
        if (cur == '.') {
            for (TrieNode node : parent.nodes) {
                if (node != null) {
                    queue.add(node);
                }
            }
        } else {
            TrieNode start = parent.nodes[TrieNode.getIndex(cur)];
            if (start != null) {queue.add(start);}
        }
    }

    private static class TrieNode {
        TrieNode[] nodes = new TrieNode[26];
        boolean end = false;

        static int getIndex(char c) {
            return c - 'a';
        }
    }
}
