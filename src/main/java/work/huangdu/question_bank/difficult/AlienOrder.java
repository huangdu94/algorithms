package work.huangdu.question_bank.difficult;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 剑指 Offer II 114. 外星文字典
 * 现有一种使用英语字母的外星文语言，这门语言的字母顺序与英语顺序不同。
 * 给定一个字符串列表 words ，作为这门语言的词典，words 中的字符串已经 按这门新语言的字母顺序进行了排序 。
 * 请你根据该词典还原出此语言中已知的字母顺序，并 按字母递增顺序 排列。若不存在合法字母顺序，返回 "" 。若存在多种可能的合法字母顺序，返回其中 任意一种 顺序即可。
 * 字符串 s 字典顺序小于 字符串 t 有两种情况：
 * 在第一个不同字母处，如果 s 中的字母在这门外星语言的字母顺序中位于 t 中字母之前，那么 s 的字典顺序小于 t 。
 * 如果前面 min(s.length, t.length) 字母都相同，那么 s.length < t.length 时，s 的字典顺序也小于 t 。
 * 示例 1：
 * 输入：words = ["wrt","wrf","er","ett","rftt"]
 * 输出："wertf"
 * 示例 2：
 * 输入：words = ["z","x"]
 * 输出："zx"
 * 示例 3：
 * 输入：words = ["z","x","z"]
 * 输出：""
 * 解释：不存在合法字母顺序，因此返回 "" 。
 * 提示：
 * 1 <= words.length <= 100
 * 1 <= words[i].length <= 100
 * words[i] 仅由小写英文字母组成
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/5/31
 */
public class AlienOrder {
    public String alienOrder(String[] words) {
        int n = words.length;
        Map<Character, List<Character>> edges = new HashMap<>(26);
        for (int i = 1; i < n; i++) {
            String pre = words[i - 1], cur = words[i];
            boolean flag = false;
            for (int k = 0, len = Math.min(pre.length(), cur.length()); k < len; k++) {
                char pc = pre.charAt(k), cc = cur.charAt(k);
                if (pc != cc) {
                    List<Character> nextList = edges.computeIfAbsent(pc, k1 -> new ArrayList<>());
                    nextList.add(cur.charAt(k));
                    flag = true;
                    break;
                }
            }
            if (!flag && pre.length() > cur.length()) {return "";}
        }
        Map<Character, Integer> degrees = new HashMap<>(26);
        for (String word : words) {
            for (int i = 0, len = word.length(); i < len; i++) {
                degrees.put(word.charAt(i), 0);
            }
        }
        for (List<Character> nextList : edges.values()) {
            for (Character next : nextList) {
                degrees.merge(next, 1, Integer::sum);
            }
        }
        int idx = 0;
        char[] ans = new char[degrees.size()];
        Queue<Character> degreeZeroQueue = new ArrayDeque<>();
        degrees.forEach((k, v) -> {
            if (v == 0) {degreeZeroQueue.offer(k);}
        });
        while (!degreeZeroQueue.isEmpty()) {
            Character c = degreeZeroQueue.poll();
            ans[idx++] = c;
            if (edges.containsKey(c)) {
                for (Character next : edges.get(c)) {
                    if (degrees.merge(next, -1, Integer::sum) == 0) {
                        degreeZeroQueue.offer(next);
                    }
                }
            }
        }
        return idx == ans.length ? new String(ans) : "";
    }
}
