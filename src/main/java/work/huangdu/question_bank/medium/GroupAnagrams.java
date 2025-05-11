package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面试题 10.02. 变位词组
 * 编写一种方法，对字符串数组进行排序，将所有变位词组合在一起。变位词是指字母相同，但排列不同的字符串。
 * 注意：本题相对原题稍作修改
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2021/7/18
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> groupMap = new HashMap<>();
        for (String str : strs) {
            int key = getKey(str);
            List<String> group = groupMap.computeIfAbsent(key, k -> new ArrayList<>());
            group.add(str);
        }
        return new ArrayList<>(groupMap.values());
    }

    private int getKey(String str) {
        int[] count = new int[26];
        for (int i = 0, n = str.length(); i < n; i++) {
            count[str.charAt(i) - 'a']++;
        }
        return Arrays.hashCode(count);
    }
}
