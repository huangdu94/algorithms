package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 890. 查找和替换模式
 * 你有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
 * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 * 返回 words 中与给定模式匹配的单词列表。
 * 你可以按任何顺序返回答案。
 * 示例：
 * 输入：words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
 * 输出：["mee","aqq"]
 * 解释：
 * "mee" 与模式匹配，因为存在排列 {a -> m, b -> e, ...}。
 * "ccc" 与模式不匹配，因为 {a -> c, b -> c, ...} 不是排列。
 * 因为 a 和 b 映射到同一个字母。
 * 提示：
 * 1 <= words.length <= 50
 * 1 <= pattern.length = words[i].length <= 20
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2022/6/12
 */
public class FindAndReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        int n = words.length;
        List<String> ans = new ArrayList<>(n);
        for (String word : words) {
            if (match(word, pattern)) {
                ans.add(word);
            }
        }
        return ans;
    }

    private boolean match(String word, String pattern) {
        int n = word.length();
        Map<Character, Character> map = new HashMap<>();
        Set<Character> visited = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char w = word.charAt(i);
            char p = pattern.charAt(i);
            if (!map.containsKey(p)) {
                if (visited.contains(w)) {
                    return false;
                }
                map.put(p, w);
                visited.add(w);
            } else {
                char value = map.get(p);
                if (w != value) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String word = "ccc", pattern = "abb";
        FindAndReplacePattern farp = new FindAndReplacePattern();
        System.out.println(farp.match(word, pattern));
    }
}
