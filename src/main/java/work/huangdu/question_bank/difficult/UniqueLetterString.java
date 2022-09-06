package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 828. 统计子串中的唯一字符
 * 我们定义了一个函数 countUniqueChars(s) 来统计字符串 s 中的唯一字符，并返回唯一字符的个数。
 * 例如：s = "LEETCODE" ，则其中 "L", "T","C","O","D" 都是唯一字符，因为它们只出现一次，所以 countUniqueChars(s) = 5 。
 * 本题将会给你一个字符串 s ，我们需要返回 countUniqueChars(t) 的总和，其中 t 是 s 的子字符串。输入用例保证返回值为 32 位整数。
 * 注意，某些子字符串可能是重复的，但你统计时也必须算上这些重复的子字符串（也就是说，你必须统计 s 的所有子字符串中的唯一字符）。
 * 示例 1：
 * 输入: s = "ABC"
 * 输出: 10
 * 解释: 所有可能的子串为："A","B","C","AB","BC" 和 "ABC"。
 * 其中，每一个子串都由独特字符构成。
 * 所以其长度总和为：1 + 1 + 1 + 2 + 2 + 3 = 10
 * 示例 2：
 * 输入: s = "ABA"
 * 输出: 8
 * 解释: 除了 countUniqueChars("ABA") = 1 之外，其余与示例 1 相同。
 * 示例 3：
 * 输入：s = "LEETCODE"
 * 输出：92
 * 提示：
 * 1 <= s.length <= 10^5
 * s 只包含大写英文字符
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2022/9/6
 */
public class UniqueLetterString {
    public int uniqueLetterString(String s) {
        Map<Character, List<Integer>> charIndexMap = new HashMap<>(26);
        int n = s.length();
        for (int i = 0; i < n; i++) {
            List<Integer> indexList = charIndexMap.computeIfAbsent(s.charAt(i), k -> new ArrayList<>());
            indexList.add(i);
        }
        int ans = 0;
        for (List<Integer> indexList : charIndexMap.values()) {
            for (int i = 0, size = indexList.size(); i < size; i++) {
                int pre = i == 0 ? -1 : indexList.get(i - 1);
                int cur = indexList.get(i);
                int next = i == size - 1 ? n : indexList.get(i + 1);
                ans += (next - cur) * (cur - pre);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        UniqueLetterString uls = new UniqueLetterString();
        System.out.println(uls.uniqueLetterString("ABC"));
    }
}
