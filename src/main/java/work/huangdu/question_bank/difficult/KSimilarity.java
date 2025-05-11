package work.huangdu.question_bank.difficult;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 854. 相似度为 K 的字符串
 * 对于某些非负整数 k ，如果交换 s1 中两个字母的位置恰好 k 次，能够使结果字符串等于 s2 ，则认为字符串 s1 和 s2 的 相似度为 k 。
 * 给你两个字母异位词 s1 和 s2 ，返回 s1 和 s2 的相似度 k 的最小值。
 * 示例 1：
 * 输入：s1 = "ab", s2 = "ba"
 * 输出：1
 * 示例 2：
 * 输入：s1 = "abc", s2 = "bca"
 * 输出：2
 * 提示：
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 和 s2  只包含集合 {'a', 'b', 'c', 'd', 'e', 'f'} 中的小写字母
 * s2 是 s1 的一个字母异位词
 *
 * @author huangdu
 * @version 2022/9/27
 */
public class KSimilarity {
    private int n;
    private char[] target;
    private Map<Integer, Integer> memo;

    public int kSimilarity(String s1, String s2) {
        this.n = s1.length();
        this.target = s2.toCharArray();
        this.memo = new HashMap<>();
        return dfs(s1.toCharArray(), 0);
    }

    private int dfs(char[] cur, int curIdx) {
        int hash = Arrays.hashCode(cur);
        if (memo.containsKey(hash)) {return memo.get(hash);}
        while (curIdx < n && cur[curIdx] == target[curIdx]) {curIdx++;}
        if (curIdx == n) {return 0;}
        char targetChar = target[curIdx];
        int min = Integer.MAX_VALUE;
        for (int nextIdx = curIdx + 1; nextIdx < n; nextIdx++) {
            if (cur[nextIdx] != targetChar) {continue;}
            swap(cur, curIdx, nextIdx);
            min = Math.min(min, dfs(cur, curIdx + 1));
            swap(cur, curIdx, nextIdx);
        }
        memo.put(hash, min + 1);
        return min + 1;
    }

    private void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
