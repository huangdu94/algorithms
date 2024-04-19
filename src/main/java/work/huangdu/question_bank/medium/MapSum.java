package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 677. 键值映射
 * 实现一个 MapSum 类，支持两个方法，insert 和 sum：
 * MapSum() 初始化 MapSum 对象
 * void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val 。如果键 key 已经存在，那么原来的键值对将被替代成新的键值对。
 * int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和。
 * 示例：
 * 输入：
 * ["MapSum", "insert", "sum", "insert", "sum"]
 * [[], ["apple", 3], ["ap"], ["app", 2], ["ap"]]
 * 输出：
 * [null, null, 3, null, 5]
 * 解释：
 * MapSum mapSum = new MapSum();
 * mapSum.insert("apple", 3);
 * mapSum.sum("ap");           // return 3 (apple = 3)
 * mapSum.insert("app", 2);
 * mapSum.sum("ap");           // return 5 (apple + app = 3 + 2 = 5)
 * 提示：
 * 1 <= key.length, prefix.length <= 50
 * key 和 prefix 仅由小写英文字母组成
 * 1 <= val <= 1000
 * 最多调用 50 次 insert 和 sum
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 *
 * @author huangdu
 * @version 2021/11/14
 */
public class MapSum {
    private final TrieNode root;

    public MapSum() {
        root = new TrieNode();
    }

    public void insert(String key, int val) {
        int n = key.length();
        TrieNode cur = root;
        List<TrieNode> path = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            int index = key.charAt(i) - 'a';
            TrieNode next = cur.child[index];
            if (next == null) {
                next = new TrieNode();
                cur.child[index] = next;
            }
            path.add(next);
            cur = next;
        }
        cur.end = true;
        int change = val - cur.val;
        cur.val = val;
        for (TrieNode node : path) {
            node.sum += change;
        }
    }

    public int sum(String prefix) {
        int n = prefix.length();
        TrieNode cur = root;
        for (int i = 0; i < n; i++) {
            int index = prefix.charAt(i) - 'a';
            TrieNode next = cur.child[index];
            if (next == null) {
                return 0;
            }
            cur = next;
        }
        return cur.sum;
    }

    static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int sum = 0;
        int val = 0;
        boolean end = false;
    }
}
