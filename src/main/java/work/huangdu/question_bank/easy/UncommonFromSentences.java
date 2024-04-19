package work.huangdu.question_bank.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 884. 两句话中的不常见单词
 * 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 * 示例 1：
 * 输入：s1 = "this apple is sweet", s2 = "this apple is sour"
 * 输出：["sweet","sour"]
 * 示例 2：
 * 输入：s1 = "apple apple", s2 = "banana"
 * 输出：["banana"]
 * 提示：
 * 1 <= s1.length, s2.length <= 200
 * s1 和 s2 由小写英文字母和空格组成
 * s1 和 s2 都不含前导或尾随空格
 * s1 和 s2 中的所有单词间均由单个空格分隔
 *
 * @author huangdu
 * @version 2022/1/30
 */
public class UncommonFromSentences {
    public String[] uncommonFromSentences(String s1, String s2) {
        Map<String, Integer> wordCountMap1 = new HashMap<>(), wordCountMap2 = new HashMap<>();
        for (String word : s1.split(" ")) {
            wordCountMap1.merge(word, 1, Integer::sum);
        }
        for (String word : s2.split(" ")) {
            wordCountMap2.merge(word, 1, Integer::sum);
        }
        Set<String> wordSet = new HashSet<>(wordCountMap1.keySet()), result = new HashSet<>();
        wordSet.addAll(wordCountMap2.keySet());
        wordSet.forEach((word) -> {
            int count1 = wordCountMap1.getOrDefault(word, 0),
                count2 = wordCountMap2.getOrDefault(word, 0);
            if (count1 == 0 && count2 == 1 || count1 == 1 && count2 == 0) {
                result.add(word);
            }
        });
        return result.toArray(new String[0]);
    }
}
